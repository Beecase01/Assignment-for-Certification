package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import testBase.BaseTest;

import java.util.Collections;
import java.util.List;

public class T_003_002SortByPrice extends BaseTest {

    @Test
    public void sortByPriceLtoH(){
        logger.info("*** Starting T_003_002SortByPrice ***");
        InventoryPage ip = new InventoryPage(dr);

        logger.info("*** Collecting Item prices and sorting them manually ***");
        List<Double> beforeSort = ip.getPriceList();
        Collections.sort(beforeSort);

        logger.info("*** Using sort functionality & receiving the list again ***");
        ip.sortProducts("Price (low to high)");
        List<Double> afterSort= ip.getPriceList();

        logger.info("*** Comparing two list ***");
        Assert.assertEquals(beforeSort,afterSort,"List not Sorted. Expected: "+beforeSort+" Actual: "+afterSort);
    }
}
