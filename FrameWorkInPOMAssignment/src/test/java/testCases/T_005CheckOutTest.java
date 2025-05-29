package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckOutPage;
import testBase.BaseTest;

public class T_005CheckOutTest extends BaseTest {
    @Test
    public void checkOutTest(){
        logger.info("*** Starting T_005CheckOutTest ***");
        CheckOutPage cop = new CheckOutPage(dr);

        logger.info("*** Adding items to check using CheckOut page ***");
        cop.addToCart(3);

        logger.info("*** Directing to Cart Page ***");
        cop.getCartItems();

        logger.info("*** Sending details for Checkout ***");
        cop.checOut("Bikesh","Shrestha","44600");

        logger.info("*** Receiving the final message ***");
        String checkTest = cop.message();

        logger.info("*** Checking if final message contains verification ***");
        Assert.assertTrue(checkTest.contains("THANK YOU"),"Checkout Not Performed");

    }
}
