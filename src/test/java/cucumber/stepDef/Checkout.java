package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Checkout {
    WebDriver driver;

    @Given("I have items in the shopping cart")
    public void iHaveItemsInTheShoppingCart() {
        // Login feature
        loginFeature("standard_user","secret_sauce");

        // Assertion when login success
        String header_title = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(header_title, "Products");

        // Add 2 item to cart and show cart
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        // click the checkout button after add item in cart
        driver.findElement(By.xpath("//*[@id='checkout']")).click();

        // input information checkout (first name, last name, postal code)
        driver.findElement(By.id("first-name")).sendKeys("Dummy First Name");
        driver.findElement(By.id("last-name")).sendKeys("Dummy Last Name");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        // Event click continue button after fill input
        driver.findElement(By.xpath("//*[@id='continue']")).click();
    }

    @And("I provide shipping and payment information")
    public void iProvideShippingAndPaymentInformation() {
        // Assertion after checkout continue button clicked
        // Check assertion for "Payment Information", "Shipping Information", "Price Total"
        String payment_information = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[1]")).getText();
        String shipping_information = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[3]")).getText();
        String price_total = driver.findElement(By.xpath("//*[@id='checkout_summary_container']/div/div[2]/div[5]")).getText();

        Assert.assertEquals(payment_information, "Payment Information");
        Assert.assertEquals(shipping_information, "Shipping Information");
        Assert.assertEquals(price_total, "Price Total");

        // Click Finish button
        driver.findElement(By.xpath("//*[@id='finish']")).click();
    }
    @Then("the checkout process should be successful")
    public void theCheckoutProcessShouldBeSuccessful() {

        // Assertion For checking text after finishing
        String header_text = driver.findElement(By.xpath("//*[@id='checkout_complete_container']/h2")).getText();
        Assert.assertEquals(header_text, "Thank you for your order!");

        // Click Back Home Button
        driver.findElement(By.xpath("//*[@id='back-to-products']")).click();

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
