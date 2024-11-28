import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Enum for API types (for extensibility)
enum ApiType {
    REST, SOAP, GRAPHQL
}

// API Connection Manager class that handles API interactions
class APIConnectionManager {
    private static final Logger logger = Logger.getLogger(APIConnectionManager.class.getName());
    private HttpClient httpClient;
    private String apiUrl;
    private String apiKey;
    private ApiType apiType;

    public APIConnectionManager() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public void configureApiConnection(String apiUrl, String apiKey, ApiType apiType) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.apiType = apiType;
    }

    // Log API requests and responses
    private void logRequestAndResponse(String request, String response) {
        logger.info("API Request: " + request);
        logger.info("API Response: " + response);
    }

    // Send GET request to the API
    public String sendGetRequest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logRequestAndResponse(request.toString(), response.body());

        if (response.statusCode() != 200) {
            throw new IOException("Received non-OK response: " + response.statusCode());
        }

        return response.body();
    }

    // Send POST request to the API (For REST API)
    public String sendPostRequest(String jsonPayload) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logRequestAndResponse(request.toString(), response.body());

        if (response.statusCode() != 200) {
            throw new IOException("Received non-OK response: " + response.statusCode());
        }

        return response.body();
    }

    // Method to handle SOAP requests (example)
    public String sendSoapRequest(String xmlPayload) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "text/xml")
                .POST(HttpRequest.BodyPublishers.ofString(xmlPayload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logRequestAndResponse(request.toString(), response.body());

        if (response.statusCode() != 200) {
            throw new IOException("Received non-OK response: " + response.statusCode());
        }

        return response.body();
    }

    // Method to handle GraphQL requests (example)
    public String sendGraphqlRequest(String graphqlQuery) throws IOException, InterruptedException {
        String jsonPayload = "{\"query\": \"" + graphqlQuery + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logRequestAndResponse(request.toString(), response.body());

        if (response.statusCode() != 200) {
            throw new IOException("Received non-OK response: " + response.statusCode());
        }

        return response.body();
    }
}

public class apiSYS {

    private static final Logger logger = Logger.getLogger(apiSYS.class.getName());

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Configure logger to log API requests and responses to a file
            FileHandler fileHandler = new FileHandler("api_requests.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            Scanner scanner = new Scanner(System.in);

            // Create Admin accU
            Admin admin = new Admin("Admin User", "admin@company.com", "123-456-7890", "adminpass","Admin");
            APIConnectionManager acm = new APIConnectionManager();
            admin.configureApiConnection(acm, scanner);

            // Admin interacts with the API
            admin.interactWithApi(acm, scanner);

            scanner.close();

        } catch (IOException | InterruptedException e) {
            logger.severe("Error occurred: " + e.getMessage());
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}