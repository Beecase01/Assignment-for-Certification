package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseTest;


public class T_001_InvalidLoginTest extends BaseTest {

    @Test
    public void invalidLoginTest(){
        logger.info("*** Starting T_001_InvalidLoginTest ***");
        LoginPage lp = new LoginPage(dr);

        logger.info("*** Receiving error message ***");
        String error = lp.getConfirmationMsg();

        logger.info("*** Checking fail for Locked user ***");
        Assert.assertTrue(error.contains("Sorry, this user has been locked out."),"Failed for Locked User");

    }
}
