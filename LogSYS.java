import java.io.*;
import java.util.*;

// LogSYS class that tracks user creation dates
class LogSYS {
    private List<User> userList;

    // Constructor to initialize the list of users
    public LogSYS() {
        this.userList = new ArrayList<>();
    }
     
    // Method to add a user to the log
    public void addUser() {
        BufferedReader reader = null;
        try {
            FileReader uf = new FileReader("user.txt");
            reader = new BufferedReader(uf);
            String userln;
            while ((userln = reader.readLine()) != null){
                userln = userln.trim();
                if (userln.isEmpty()) {break;}
                String[] userInf = userln.split(",");
                if (userInf.length == 5) {
                    String fN = userInf[0];
                    String eA = userInf[1];
                    String trno = userInf[2];
                    String pw = userInf[3];
                    String ur = userInf[4];
                    User u = new User(fN, eA, trno, pw, ur);
                    userList.add(u);
                }
            }
        }
        catch ( IOException ex) {}
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ey) {}
        }
    }        

    

    // Method to retrieve and print all users along with their creation dates
    public void printAllUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of all users with account creation dates:\n");        
            for (User user : userList) {
                System.out.println("User: " + user.getName() + ", Account Created: " + user.getFormattedCreationDate());
            }
        }
    }




    public static void main(String[] args) {
        // Create the logSYS instance to manage accounts
        LogSYS logLST = new LogSYS();

        // Add users to the log system
        //User u = new User();
        logLST.addUser();
    
        // Print the list of all users along with their creation dates
        logLST.printAllUsers();
    }
}