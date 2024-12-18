package testOne.testOne;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.List;

public class LoginTest {
	
	Dotenv dotenv = Dotenv.load();
	
    private WebDriver driver;
    private final String baseUrl = dotenv.get("URL");

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        
        // Clear cookies
        driver.manage().deleteAllCookies();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        List<String[]> data = CSVUtils.readCSV("/Users/atulsingh/Downloads/testOne.csv");
        Object[][] testData = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            testData[i] = data.get(i);
        }
        return testData;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Add assertions to verify login success
        String actualResult = loginPage.getLoginResult(); // Assume this method exists
        Assert.assertEquals(actualResult, "Let’s do a quick security check");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
