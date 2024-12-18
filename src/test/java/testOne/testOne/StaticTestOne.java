package testOne.testOne;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StaticTestOne {
	
	
	private static final Logger logger = LoggerFactory.getLogger(StaticTestOne.class); 
	
	//Logger logger = LoggerFactory.getLogger('name of the file/class)
	
	
	public static void main(String[] args) {
		
		//WebDriverManager.chromedriver().browserVersion("119.0.6045.123").setup();

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        try {
            // Open Google
            driver.get("https://www.google.com");

            // Wait for the search input element to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

            // Type "www.youtube.com" in the search input
            searchBox.sendKeys("www.youtube.com");

            // Submit the form
            searchBox.submit();
            
            logger.info("Everything went well.");//Instead of using 'sysout', we will be using logger.
            
        } catch (NoSuchElementException | TimeoutException e) {
            // Log any exceptions that occur during the test
            logger.error("Element not found or Timeout: {}", e.getMessage());
        } catch (StaleElementReferenceException e) {
            // Log StaleElementReferenceException
            logger.error("Stale element reference: {}", e.getMessage());
        } catch (Exception e) {
            // Log any other exceptions that occur during the test
            logger.error("An error occurred: {}", e.getMessage());
        } finally {
            try {
            	// Clear cookies
            	 driver.manage().deleteAllCookies();
                 
                 // Clear cache
                 ((ChromeDriver) driver).executeScript("window.localStorage.clear();");
                 ((ChromeDriver) driver).executeScript("window.sessionStorage.clear();");
                 ((ChromeDriver) driver).executeScript("window.location.reload();");
                // Close the browser regardless of whether the test passed or failed
                 driver.quit();
                 
            } catch (Exception quitException) {
                // Log any exception that occurs during WebDriver quitting
                logger.error("An error occurred while quitting the WebDriver: {}", quitException.getMessage());
            }
        }}}
