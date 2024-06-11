import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {

    private static final String HomePage = "https://www.facebook.com/";
    private static WebDriver openDriver;

    @BeforeAll
    public static void classSetup(){
        openDriver = SharedDriver.getWebDriver();
        openDriver.get(HomePage);
    }

    @AfterAll
    public static void classClose(){
        SharedDriver.closeWebDriver();
    }

    @AfterEach
    public void testClose(){
        openDriver.get(HomePage);
    }

    @Test
    public void openBrowserTest(){
        String actualURL = openDriver.getCurrentUrl();
        assertEquals(HomePage, actualURL, "URLs don't not natch");
    }


    }
