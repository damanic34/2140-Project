import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;



class User {
    private String name;
    private String email;
    //private String address;
    private String TRN;
    private String password;
    private String accrl;
    private Date accDateCreation;    

    // Constructor to initialize user details
    public User(String name, String email, /*String address,*/ String TRN, String password, String accrl) {
        this.name = name;
        this.email = email;
        //this.address = address;
        //this.TRN = TRN;
        this.password = password;
        this.accrl = accrl;
        this.accDateCreation = new Date();  // Set current date and time as the account creation date    
    }

    // Getters for user details
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    /* 
    public String getAddress() {
        return address;
    }
    */
    public String getTRN() {
        return TRN;
    }

    public String getPassword() {
        return password;
    }

    public String getAccRole() {
        return accrl;
    }

    public Date getAccountCreationDate() {
        return accDateCreation;
    }

    public String getFormattedCreationDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    return sdf.format(accDateCreation);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /* 
    public void setAddress(String address) {
        this.address = address;
    }
    */
    public void setPhoneNo(String TRN) {
        this.TRN = TRN;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccrole(String accrl) {
        this.accrl = accrl;
    }

    public void setAccDateCreation(Date accDateCreation) {
        this.accDateCreation = accDateCreation;
    }

    // Method to print user details
    public void printUserDetails() {
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        //System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getTRN());
        System.out.println("Account Level: " + getAccRole());
        System.out.println("Account Creation Date: " + getFormattedCreationDate());
    }

    // Method to change account level with admin approval
    public boolean changeAccRole(String newRole, Admin admin) {
        if (admin == null) {
            System.out.println("Admin approval required to change account level.");
            return false;
        }
        System.out.println("Request to change account level to: " + newRole);
        return admin.approveRoleChange(this, newRole);
    }

}

// Admin class that can approve account level changes
class Admin extends User {

    public Admin(String name, String email, /*String homeAddress,*/ String trn, String password, String Admin) {
        super(name, email, /*homeAddress,*/ trn, password, Admin);
        setAccrole(Admin);
    }

    // Method to approve or deny account level change
    public boolean approveRoleChange(User user, String newRole) {
        // Admin can approve changes
        System.out.println("Admin approves the change of account level.");
        // Update user's account level (change to newRole)
        return true; // Admin approves, the account level can be updated
    }

    // Admin has access to all features
    public void accessAdminFeatures() {
        System.out.println("Admin has full access to all features.");
    }

    public void configureApiConnection(APIConnectionManager apiManager, Scanner scanner) {
        System.out.println("Enter the API URL: ");
        String apiUrl = scanner.nextLine();

        System.out.println("Enter the API Key: ");
        String apiKey = scanner.nextLine();

        System.out.println("Select the API type (1: REST, 2: SOAP, 3: GraphQL): ");
        int apiTypeChoice = scanner.nextInt();
        ApiType apiType = ApiType.REST;
        switch (apiTypeChoice) {
            case 1:
                apiType = ApiType.REST;
                break;
            case 2:
                apiType = ApiType.SOAP;
                break;
            case 3:
                apiType = ApiType.GRAPHQL;
                break;
            default:
                System.out.println("Invalid choice, defaulting to REST.");
        }

        apiManager.configureApiConnection(apiUrl, apiKey, apiType);
    }

    public void interactWithApi(APIConnectionManager apiManager, Scanner scanner) throws IOException, InterruptedException {
        System.out.println("Select API action: ");
        System.out.println("1. Send GET Request");
        System.out.println("2. Send POST Request");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (action == 1) {
            String response = apiManager.sendGetRequest();
            System.out.println("Response from GET request: " + response);
        } else if (action == 2) {
            System.out.println("Enter the POST data (JSON format): ");
            String data = scanner.nextLine();
            String response = apiManager.sendPostRequest(data);
            System.out.println("Response from POST request: " + response);
        } else {
            System.out.println("Invalid action.");
        }
    }

}

// Employee class, has some access but less than admin
class Employee extends User {

    public Employee(String name, String email, /*String homeAddress,*/ String trn, String password, String Employee) {
        super(name, email, /*homeAddress,*/ trn, password, Employee);
    }

    // Employee has access to some features
    public void accessEmployeeFeatures() {
        System.out.println("Employee has access to certain internal features.");
    }

    // Employee tries to access admin features (not allowed)
    public void tryAccessAdminFeatures() {
        System.out.println("Employee does not have access to Admin features.");
    }
}

// Customer class, has the most limited access
class Customer extends User {

    public Customer(String name, String email, /*String homeAddress,*/ String trn, String password, String Customer) {
        super(name, email, /*homeAddress,*/ trn, password, Customer);
    }

    // Customer has access to basic features
    public void accessCustomerFeatures() {
        System.out.println("Customer can access basic features only.");
    }

    // Customer tries to access employee/admin features (not allowed)
    public void tryAccessEmployeeFeatures() {
        System.out.println("Customer does not have access to Employee features.");
    }

    public void tryAccessAdminFeatures() {
        System.out.println("Customer does not have access to Admin features.");
    }
}

/*public class User {
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
*/
