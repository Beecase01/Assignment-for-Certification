package testBase;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.LoginPage;

import java.time.Duration;


public class BaseTest {
    public WebDriver dr;
    public WebDriverWait wait;
    public Logger logger; //Log 4j


    @BeforeMethod
    public void setup(){
        logger = LogManager.getLogger(this.getClass());

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito","--headless");

        dr=  new EdgeDriver(options);
        dr.manage().window().maximize();
        dr.get("https://www.saucedemo.com/v1/");
        wait = new WebDriverWait(dr,Duration.ofSeconds(10));
    }

    @BeforeMethod(dependsOnMethods = "setup")
    public void doLogin(ITestResult result){
        LoginPage lp = new LoginPage(dr);
        if(result.getMethod().getMethodName().equals("invalidLoginTest")){
            lp.login("locked_out_user", "secret_sauce");
        }
        else {
            lp.login("standard_user", "secret_sauce");
        }
    }

    @AfterMethod
    public void tearDown(){dr.quit();}
}
