import java.time.LocalDate;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class Report {
    private String title;
    private String content;
    private String format; 

    public Report(String title, String content, String format) {
        this.title = title;
        this.content = content;
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getFormat() {
        return format;
    }
}





public class ShipmentReport {
    private ShipmentHistory history;

    public ShipmentReport(ShipmentHistory history) {
        this.history = history;
    }

    // this generates the summary report//
    public class SummaryReport(LocalDate startDate, LocalDate endDate) {
        List<ShipmentHistory> records = historyService.filterByDateRange(startDate, endDate);
        String content = new String("Summary Report:\n");
        content.append("Total Shipments: ").append(records.size()).append("\n");

        for (ShipmentHistory record : records) {
            content.append("- ").append(record.getTrackingNumber())
                   .append("Status: ").append(record.getStatus()).append("\n");
        }

        return new ShipmentReport("Shipment Summary Report", content.toString(), "Summary");
    }

  



public class ShipmentReportExport {

// this would turn the information to a table//
    public void exportToCSV(ShipmentReport report, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Title,Format,Content\n");
            writer.write("\"" + report.getTitle() + "\",\"" + report.getFormat() + "\",\"" + report.getContent() );
        }
    }

// this would allow the report to be exported into a pdf//
    public void exportToPDF(ShipmentReport report, String filePath) {
        System.out.println("Exporting PDF to: " + filePath);
        System.out.println("Title: " + report.getTitle());
        System.out.println("Content: " + report.getContent());
        
    }
}
