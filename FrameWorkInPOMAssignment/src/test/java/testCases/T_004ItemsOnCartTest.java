package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import testBase.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class T_004ItemsOnCartTest extends BaseTest {
    @Test
    public void cartTest(){
        logger.info("*** Starting T_004ItemsOnCartTest ***");
        CartPage cp = new CartPage(dr);

        logger.info("*** Adding items in cart and receiving selected items from product list ***");
        List<String> selected = cp.addToCart(4);

        logger.info("*** Directing to Cart page and receiving the list of added items ***");
        List<String> available = cp.getCartItems();

        logger.info("*** Comparing if selected items are visible there ***");
        Assert.assertEquals(selected,available,"Cart not working. Expected: "+selected+" Actual: "+available);
    }
}
