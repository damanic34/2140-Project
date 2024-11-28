import java.util.Scanner;

public class accAuth {

    // Method to execute program calls based on account level
    public static void executeProgramCalls(User user) {
        switch (user.getAccRole()) {
            case "Admin":
                Admin admin = (Admin) user;
                admin.accessAdminFeatures();
                break;
            case "Employee":
                Employee employee = (Employee) user;
                employee.accessEmployeeFeatures();
                employee.tryAccessAdminFeatures();  // Employee can't access admin features
                break;
            case "Customer":
                Customer customer = (Customer) user;
                customer.accessCustomerFeatures();
                customer.tryAccessEmployeeFeatures();  // Customer can't access employee features
                customer.tryAccessAdminFeatures();    // Customer can't access admin features
                break;
            default:
                System.out.println("Invalid account level.");
                break;
        }
    }

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Create Admin
    Admin admin = new Admin("Admin User", "admin@company.com", "123-456-7890", "adminpass","Admin");

    // Create Employee and Customer users
    Employee employee = new Employee("Employee User", "employee@company.com", "987-654-3210", "employeepass","Employee");
    Customer customer = new Customer("Customer User", "customer@company.com", "555-555-5555", "customerpass", "Customer");

    // Print user details and try accessing features based on account level
    System.out.println("Admin Access:");
    executeProgramCalls(admin);

    System.out.println("\nEmployee Access:");
    executeProgramCalls(employee);

    System.out.println("\nCustomer Access:");
    executeProgramCalls(customer);

    // Now, let's try to change the account type for the customer (Requires admin approval)
    System.out.println("\nRequest to change Customer account to Employee account...");
    boolean changeSuccess = customer.changeAccRole("employee", admin);

     
    if (changeSuccess) {
        System.out.println("Account level changed successfully.");
    } else {
        System.out.println("Account level change failed.");
    }
    

    // Try again to access admin features after changing account type
    System.out.println("\nAccess Features After Account Change Request:");
    executeProgramCalls(customer);

    scanner.close();
}
}



