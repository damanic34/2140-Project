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


import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PackageTracker packageTracker = new PackageTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Package Tracker Menu ===");
            System.out.println("1. Add a new package");
            System.out.println("2. Update package status");
            System.out.println("3. View package details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new package
                    try {
                        System.out.print("Enter tracking number: ");
                        String trackingNumber = scanner.nextLine();

                        System.out.print("Enter origin: ");
                        String origin = scanner.nextLine();

                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();

                        System.out.print("Enter status: ");
                        String status = scanner.nextLine();

                        System.out.print("Enter weight (lb): ");
                        double weight = scanner.nextDouble();

                        System.out.print("Enter delivery days from now: ");
                        int daysToDelivery = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter package description: ");
                        String description = scanner.nextLine();

                        packageTracker.addPackage(
                            trackingNumber,
                            origin,
                            destination,
                            status,
                            weight,
                            volume,
                            LocalDateTime.now().plusDays(daysToDelivery),
                            description
                        );
                        System.out.println("Package added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // Update package status
                    try {
                        System.out.print("Enter tracking number: ");
                        String trackingNumber = scanner.nextLine();

                        System.out.print("Enter new status: ");
                        String newStatus = scanner.nextLine();

                        packageTracker.updateStatus(trackingNumber, newStatus);
                        System.out.println("Package status updated successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    // View package details
                    try {
                        System.out.print("Enter tracking number: ");
                        String trackingNumber = scanner.nextLine();

                        packageTracker.viewPackageDetails(trackingNumber);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
