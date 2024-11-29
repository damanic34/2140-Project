import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Package {
    private String trackingNumber;  // Unique identifier
    private String origin;          // Package origin
    private String destination;     // Package destination
    private String status;          // Current status (e.g., "In Transit")
    private LocalDateTime lastUpdated;  // Timestamp of the last status update
    private LocalDateTime estimatedDeliveryDate;  // Estimated delivery date
    private double weight;          // Weight of the package
    private double volume;          // Volume of the package
    private Category cargoType;     // type of cargo 

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

    public Category getCargoType() {
        return cargoType;
    }

    public void setCargoType(Category cargoType) {
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
               "Last Updated: " + lastUpdated;
               "cargoType: " + (cargoType != null cargoType.getName() : "Uncategorized") + "\n";
    }
}


class PackageService {
    private Map<String, Package> packageMap;  // Storage for all packages using trackingNumber as the key

    // Constructor
    public PackageService() {
        this.packageMap = new HashMap<>();
    }

    // Add a new package
    public void addPackage(String trackingNumber, String origin, String destination, String status,
                           double weight, double volume, LocalDateTime estimatedDeliveryDate) {
        if (packageMap.containsKey(trackingNumber)) {
            throw new IllegalArgumentException("Tracking number already exists!");
        }
        Package newPackage = new Package(trackingNumber, origin, destination, status, weight, volume, estimatedDeliveryDate);
        packageMap.put(trackingNumber, newPackage);
    }

    // Update the status of a package
    public void updatePackageStatus(String trackingNumber, String newStatus) {
        Package pkg = packageMap.get(trackingNumber);
        if (pkg == null) {
            throw new IllegalArgumentException("Package not found with tracking number: " + trackingNumber);
        }
        pkg.setStatus(newStatus);
    }

    // Get package details
    public String getPackageDetails(String trackingNumber) {
        Package pkg = packageMap.get(trackingNumber);
        if (pkg == null) {
            throw new IllegalArgumentException("Package not found with tracking number: " + trackingNumber);
        }
        return pkg.getDetails();
    }


class PackageTracker {
        private PackageService packageService;
    
        // Constructor
        public PackageTracker() {
            this.packageService = new PackageService();
        }
    
        // Add a new package
        public void addPackage(String trackingNumber, String origin, String destination, String status,
                               double weight, double volume, LocalDateTime estimatedDeliveryDate) {
            packageService.addPackage(trackingNumber, origin, destination, status, weight, volume, estimatedDeliveryDate);
            System.out.println("Package added successfully!");
        }
    
        // Update a package's status
        public void updateStatus(String trackingNumber, String newStatus) {
            try {
                packageService.updatePackageStatus(trackingNumber, newStatus);
                System.out.println("Package status updated successfully!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    
        // View package details
        public vString viewPackageDetails(String trackingNumber) {
            try {
                String details = packageService.getPackageDetails(trackingNumber);
                System.out.println(details);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    
        // Refresh all package statuses
        public void refreshStatuses() {
            packageService.refreshStatuses();
            System.out.println("Statuses refreshed successfully!");
        }
    }
    import java.time.LocalDateTime;

public class Main{
    public static void main(String[] args) {
        PackageTracker packageTracker = new PackageTracker();

        // Add packages
        try {
            packageTracker.addPackage(
                "TRACK123",
                "Kingston, Jamaica",
                "Montego Bay, Jamaica",
                "In Transit",
                5.5,
                0.03,
                LocalDateTime.now().plusDays(3),
                "Electronics"
            );

            packageTracker.addPackage(
                "TRACK456",
                "New York, USA",
                "Spanish Town, Jamaica",
                "At Sorting Facility",
                2.0,
                0.01,
                LocalDateTime.now().plusDays(7),
                "Barrel"
            );
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Update package status
        try {
            packageTracker.updateStatus("TRACK123", "Out for Delivery");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Retrieve and display package details
        try {
            packageTracker.viewPackageDetails("TRACK123");
            packageTracker.viewPackageDetails("TRACK456");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to retrieve a non-existent package
        try {
            packageTracker.viewPackageDetails("NONEXISTENT");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
}
