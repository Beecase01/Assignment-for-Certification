package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Base {
    protected WebDriver dr;
    protected WebDriverWait wait;

    @BeforeMethod //this is added so that browser can navigate to the website with full screen & wait duration of 10 seconds for each test
    public void setUp(){
        dr = new EdgeDriver();
        dr.manage().window().maximize();
        wait = new WebDriverWait(dr,Duration.ofSeconds(10));
        dr.get("https://www.saucedemo.com/v1/");
    }

    @AfterMethod //this is added so that browser is automatically closed after the execution of all test codes
    public void tearDown(){
        dr.quit();
    }
}



