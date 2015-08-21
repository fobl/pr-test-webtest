package no.photographer.webtest;


import static org.fest.assertions.api.Assertions.assertThat;
import no.photorunner.customer.CustomerApplication;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class CustomerSimpleTest {

    @Before
    public void setUp() throws Exception {
        CustomerApplication appl = new CustomerApplication();
        appl.run("server", getClass().getClassLoader().getResource("customer.yml").getFile());
    }

    @Test
    public void testName() throws Exception {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://127.0.0.1:8080/");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("frode@gmail.com");

        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("test");

        WebElement submit = driver.findElement(By.name("login"));
        submit.submit();

        WebElement h3 = driver.findElement(By.tagName("h3"));
        assertThat(h3.getText()).isEqualToIgnoringCase("No allImages added to customer");

        WebElement about = driver.findElement(By.id("about"));
        about.click();
    }
}
