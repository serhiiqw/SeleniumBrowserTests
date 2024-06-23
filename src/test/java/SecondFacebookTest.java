import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

//Verify all the errors messages for empty fields
//Test Months droplist
//Test Years DropList (no need to test all, years 1905, 1950 and 2020 are enough)
//Find and test Radiobuttons using siblings in XPath.
//Test Terms and DataPolicy links, verify that the new pages are opened after pressing it*


public class SecondFacebookTest {

    private static final String HomePageFacebook = "https://www.facebook.com/";
    private static WebDriver browser;

    @BeforeAll
    public static void classSetup() {
        browser = SharedDriver.getWebDriver();
        browser.get(HomePageFacebook);
    }

    @AfterAll
    public static void classClose() {
        SharedDriver.closeWebDriver();
    }

    @AfterEach
    public void uploadPage() {
        browser.get(HomePageFacebook);
    }

    @Test
    public void openBrowser() {
        String actualUrl = browser.getCurrentUrl();
        assertEquals(HomePageFacebook, actualUrl, "Check URL");
    }

    @Test
    public void createAccTest() throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);
    }

    @Test
    public void monthTest() throws InterruptedException {
        //Open browser and click "Create new account" button
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        //Choose month
        browser.findElement(By.name("birthday_month")).click();
        browser.findElement(By.xpath("//*[text() = 'Oct']")).click();
        String monthValue = browser.findElement(By.name("birthday_month")).getAttribute("value");
        assertEquals("10", monthValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "5", "12"})
    public void monthTest(String monthInput) throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        browser.findElement(By.xpath("//*[@title = 'Month']")).click();
        String monthValue = browser.findElement(By.xpath("//*[text() = '" + monthInput + "']")).getAttribute("value");
        assertEquals(monthInput, monthValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "20", "28", "29", "30", "31"})
    public void dayTest(String dayInput) throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        browser.findElement(By.xpath("//*[@title = 'Day']")).click();
        String dayValue = browser.findElement(By.xpath("//*[text() = '" + dayInput + "']")).getAttribute("value");
        assertEquals(dayInput, dayValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2024"})
    public void yearTest(String yearInput) throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        browser.findElement(By.xpath("//*[@title = 'Year']")).click();
        String yearValue = browser.findElement(By.xpath("//*[text() = '" + yearInput + "']")).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }

    @Test
    public void femaleTest() throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        WebElement femaleButton = browser.findElement(By.xpath("//*[text() = 'Female']//following-sibling::*[@type = 'radio']"));
        assertNotNull(femaleButton);
        femaleButton.click();

    }

    @Test
    public void maleTest() throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        WebElement maleButton = browser.findElement(By.xpath("//*[text() = 'Male']//following-sibling::*[@type = 'radio']"));
        assertNotNull(maleButton);
        maleButton.click();
    }

    @Test
    public void customTest() throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        WebElement customButton = browser.findElement(By.xpath("//*[text() = 'Custom']//following-sibling::*[@type = 'radio']"));
        assertNotNull(customButton);
        customButton.click();
    }

    @Test
    public void errorFirstName() throws InterruptedException {
        WebElement createButton = browser.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createButton);
        createButton.click();
        Thread.sleep(2000);

        browser.findElement(By.xpath("//*[@name='websubmit']")).click();
        browser.findElement(By.xpath("//*[@name='firstname']")).click();

        WebElement error = browser.findElement(By.xpath("//*[contains(text(), 'your name')]"));
        assertNotNull(error);
    }

    @Test
    public void policyTest() throws InterruptedException {
        WebElement policyButton = browser.findElement(By.xpath("//*[@title='Review our terms and policies.']"));
        assertNotNull(policyButton);
        policyButton.click();
        Thread.sleep(3000);
    }

}