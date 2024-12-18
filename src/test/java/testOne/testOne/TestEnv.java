package testOne.testOne;

import io.github.cdimascio.dotenv.Dotenv;

public class TestEnv {
	
	public static void main(String[] args) {
        // Load environment variables from the .env file
        Dotenv dotenv = Dotenv.load();
        
        // Retrieve environment variables
        String apiUrl = dotenv.get("URL");

        // Print the values to verify
        System.out.println("URL: " + apiUrl);
    }
}
