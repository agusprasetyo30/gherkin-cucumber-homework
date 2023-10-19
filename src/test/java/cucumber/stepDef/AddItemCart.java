package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddItemCart {
    WebDriver driver;

    @Given("user logged in to SauceDemo")
    public void userLoggedInToSauceDemo() {
      loginFeature("standard_user","secret_sauce");
    }

    @When("user browse the product catalog")
    public void userBrowseTheProductCatalog() {
        String header_title = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();

        Assert.assertEquals(header_title, "Products");
    }

    @And("user add an item to the shopping cart")
    public void userAddAnItemToTheShoppingCart() {
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
    }

    @Then("the item should appear in the shopping cart")
    public void theItemShouldAppearInTheShoppingCart() {
        driver.close();
    }

    // Function use for login
    public void loginFeature(String username, String password)
    {
        WebDriverManager.chromedriver().setup();

        // Driver Requirement
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(VariableRequirement.BASE_URL);

        // Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        // input username & password
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        // click button submit
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
}
