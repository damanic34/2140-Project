import java.time.LocalDateTime;

public class Package {
    private String trackingNumber;  // Unique identifier
    private String origin;          // Package origin
    private String destination;     // Package destination
    private String status;          // Current status (e.g., "In Transit")
    private LocalDateTime lastUpdated;  // Timestamp of the last status update
    private LocalDateTime estimatedDeliveryDate;  // Estimated delivery date
    private double weight;          // Weight of the package
    private double volume;          // Volume of the package

    // Constructor
    public Package(String trackingNumber, String origin, String destination, String status,
                   double weight, double volume, LocalDateTime estimatedDeliveryDate) {
        this.trackingNumber = trackingNumber;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.weight = weight;
        this.volume = volume;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.lastUpdated = LocalDateTime.now();  // Automatically sets the time of creation
    }

    // Getters and Setters
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.lastUpdated = LocalDateTime.now();  // Update timestamp
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public String getDetails() {
        return "Tracking Number: " + trackingNumber + "\n" +
               "Origin: " + origin + "\n" +
               "Destination: " + destination + "\n" +
               "Status: " + status + "\n" +
               "Weight: " + weight + " kg\n" +
               "Volume: " + volume + " cubic meters\n" +
               "Estimated Delivery Date: " + estimatedDeliveryDate + "\n" +
               "Last Updated: " + lastUpdated;
    }
}
