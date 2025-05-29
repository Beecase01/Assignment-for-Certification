package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.InventoryPage;
import pageObjects.LoginPage;
import testBase.BaseTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T_003_001SortByName extends BaseTest {



    @Test
    public void sortByNameDes(){
        logger.info("*** Starting T_003_001SortByName ***");
        InventoryPage ip = new InventoryPage(dr);

        logger.info("*** Collecting Product List & sortiing them ***");
        List<String> beforeSort = ip.getproductlist();
        Collections.sort(beforeSort,Collections.reverseOrder());

        logger.info("*** Using sort button on page & collecting the list again ***");
        ip.sortProducts("Name (Z to A)");
        List<String> afterSort = ip.getproductlist();

        logger.info("*** Comparing sorted list with result after using sort function ***");
        Assert.assertEquals(beforeSort,afterSort,"Sorting Failed. Expected: "+beforeSort+"Actual: "+afterSort);
    }
}
