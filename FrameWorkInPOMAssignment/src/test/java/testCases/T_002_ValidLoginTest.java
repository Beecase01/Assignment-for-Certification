package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseTest;

public class T_002_ValidLoginTest extends BaseTest {

    @Test
    public void validLoginTest(){
        logger.info("*** Starting T_002_ValidLoginTest ***");

        logger.info("*** Receiving current Url ***");
        String currentUrl = dr.getCurrentUrl();

        logger.info("*** Confirming redirection to Inventory Page ***");
        Assert.assertTrue(currentUrl.contains("inventory.html"),"Login Failed");
    }

}
