import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShipmentHistory {
    private List<ShipmentHistory> shipmentRecords = new ArrayList<>();

    // This adds a shipment record//
    public void addShipmentRecord(ShipmentHistory record) {
        shipmentRecords.add(record);
    }

    // this get shipment records for a customer over a six months period)
    public List<ShipmentHistory> getShipmentHistory(String packageId) {
        LocalDate sixMonths = LocalDate.now().minusMonths(6);
        return shipmentRecords.stream()
                .filter(record > record.getpackageId().equals(packageId) &&
                        (record.getLastUpdated().isAfter(sixMonths)) && 
                        record.getLastUpdated().isEqual(sixMonths).collect(Collectors.toList()));
    }

    // Get all shipment records for the admin//
    public List<ShipmentHistory> getAllShipmentRecords() {
        return new ArrayList<>(shipmentRecords);
    }

    // this filters shipments by date//
    public List<ShipmentHistory> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        return shipmentRecords.stream()
                .filter(record -> !record.getLastUpdated().isBefore(startDate) && !record.getLastUpdated().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // this filters shipments by  status//
    public List<ShipmentHistory> filterByStatus(String status) {
        return shipmentRecords.stream()
                .filter(record -> record.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
