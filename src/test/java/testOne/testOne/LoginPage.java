package testOne.testOne;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {	
    private WebDriver driver;
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    //private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.className("btn__primary--large");
    private By resultMessage = By.tagName("h1"); // Assume this element displays the result

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        
    	usernameField.sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getLoginResult() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Wait for a specific element to be present on the last page
        WebElement lastPageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        
        lastPageElement.isDisplayed();
        
        return driver.findElement(resultMessage).getText();
    }
}

