package in.httprequest.gradle_test.test;

import org.apache.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sylvester Das
 * @since 4/22/2018.
 */
public class Initialize {
    private static final Logger log = LogManager.getRootLogger();
    private static InputStream log4jProperties =
            Initialize.class.getResourceAsStream("/property/log4j.properties");

    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure(log4jProperties);
            Appender appender = new FileAppender(new SimpleLayout(), "log4j.log", true);
            BasicConfigurator.configure(appender);

            WebDriver driver = new HtmlUnitDriver();
            driver.get("http://www.google.com");

            System.out.println("Title of the page is: " + driver.getTitle());
            log.info(driver.getTitle());

            WebElement search = driver.findElement(By.name("q"));
            search.sendKeys("Sylvester Das");
            search.submit();

            log.info(driver.getTitle());
            System.out.println("Title of the page now is: " + driver.getTitle());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
        }
    }
}
