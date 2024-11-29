import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class Package {
    private String trackingNumber;
    private String origin;
    private String destination;
    private String status;
    private LocalDateTime lastUpdated;
    private LocalDateTime estimatedDeliveryDate;
    private double weight;
    private double volume;
    private String cargoType;

    public Package(String trackingNumber, String origin, String destination, String status,
    double weight, LocalDateTime estimatedDeliveryDate, String cargoType) {
        this.trackingNumber = trackingNumber;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
        this.weight = weight;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.cargoType = cargoType;
        this.lastUpdated = LocalDateTime.now();
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
        this.lastUpdated = LocalDateTime.now();
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

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getDetails() {
        return "Tracking Number: " + trackingNumber + "\n" +
               "Origin: " + origin + "\n" +
               "Destination: " + destination + "\n" +
               "Status: " + status + "\n" +
               "Weight: " + weight + " kg\n" +
               "Volume: " + volume + " cubic meters\n" +
               "Estimated Delivery Date: " + estimatedDeliveryDate + "\n" +
               "Last Updated: " + lastUpdated + "\n" +
               "Cargo Type: " + cargoType;
    }
}

class PackageService {
    private Map<String, Package> packageMap;

    public PackageService() {
        this.packageMap = new HashMap<>();
    }

    public void addPackage(String trackingNumber, String origin, String destination, String status,
                           double weight, double volume, LocalDateTime estimatedDeliveryDate, String cargoType) {
        if (packageMap.containsKey(trackingNumber)) {
            throw new IllegalArgumentException("Tracking number already exists!");
        }
        Package newPackage = new Package(trackingNumber, origin, destination, status, weight, volume, estimatedDeliveryDate, cargoType);
        packageMap.put(trackingNumber, newPackage);
    }

    public void updatePackageStatus(String trackingNumber, String newStatus) {
        Package pkg = packageMap.get(trackingNumber);
        if (pkg == null) {
            throw new IllegalArgumentException("Package not found with tracking number: " + trackingNumber);
        }
        pkg.setStatus(newStatus);
    }

    public String getPackageDetails(String trackingNumber) {
        Package pkg = packageMap.get(trackingNumber);
        if (pkg == null) {
            throw new IllegalArgumentException("Package not found with tracking number: " + trackingNumber);
        }
        return pkg.getDetails();
    }
}

class PackageTracker {
    private PackageService packageService;

    public PackageTracker() {
        this.packageService = new PackageService();
    }

    public void addPackage(String trackingNumber, String origin, String destination, String status,
                           double weight, double volume, LocalDateTime estimatedDeliveryDate, String cargoType) {
        packageService.addPackage(trackingNumber, origin, destination, status, weight, volume, estimatedDeliveryDate, cargoType);
        System.out.println("Package added successfully!");
    }

    public void updateStatus(String trackingNumber, String newStatus) {
        try {
            packageService.updatePackageStatus(trackingNumber, newStatus);
            System.out.println("Package status updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewPackageDetails(String trackingNumber) {
        try {
            String details = packageService.getPackageDetails(trackingNumber);
            System.out.println(details);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void refreshStatuses() {
        System.out.println("Statuses refreshed successfully!");
    }

}
