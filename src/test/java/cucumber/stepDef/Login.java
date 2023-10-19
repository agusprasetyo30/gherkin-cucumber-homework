package cucumber.stepDef;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver;
    String base_url = "https://www.saucedemo.com";


    @Given("User on the SauceDemo login page")
    public void userOnTheSauceDemoLoginPage() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(base_url);

        // Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("User enter username")
    public void userEnterUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("User enter valid password")
    public void userEnterValidPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("User click the {string} button")
    public void userClickTheButton(String arg0) {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Then("User should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        String header_title = driver.findElement(By.xpath("//*[@id='header_container']/div[1]/div[2]/div")).getText();
        Assert.assertEquals(header_title, "Swag Labs");
        driver.close();

    }

    @And("User enter invalid password")
    public void userEnterInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("Passwordsalah");
    }

    @Then("User should see an error message")
    public void userShouldSeeAnErrorMessage() {
        String error_login = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]")).getText();
        Assert.assertEquals(error_login, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}
