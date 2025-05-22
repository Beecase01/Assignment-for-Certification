package test;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginTest extends Base {

    //this is to avoid repetition of login codes for each test for valid logins
    @BeforeMethod
    public void setUpLogin(ITestResult result){
        LoginPage lp = new LoginPage(dr,wait);//instance of login page is created. Required for each test
        if(result.getMethod().getMethodName().equals("invalidLoginTest")){ //for invalid login test will provide user name as 'locked_out_user'
            lp.login("locked_out_user","secret_sauce");
        }
        else {
            lp.login("standard_user","secret_sauce");//for all other test, it will provide valid credentials from here
        }
    }



    //this is for Test Scenarios 1.
    @Test (priority = 1)//this test will start 1st
    public void invalidLoginTest(){
        WebElement errorMessage = wait.until(visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));//locates the element where error message is expected
        Assert.assertTrue(errorMessage.getText().contains("Sorry, this user has been locked out."),"Failed for Locked User test");//checks if required message is delivered
    }




    //this is for Test Scenarios 2.
   @Test (priority = 2)//this test will begin at 2nd
    public void validLoginTest(){
        String currentUrl= dr.getCurrentUrl();//this collects the url of the page.
        Assert.assertTrue(currentUrl.contains("inventory.html"),"Not directed to Inventory Page");//this checks if user is redirected to Product Page or not
    }



    //this is for Test Scenarios 3.

    //starting with filter products by Name Descending
    @Test (priority = 3) //this will commence at 3rd
    //finds list, stores & sorts. Then uses sort option, collects the list after sort and matches to verify
    public void filterByNameDescTest(){
        InventoryPage ip = new InventoryPage(dr,wait);

        List<WebElement> productsBeforeFilter = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_name']")));//collects name of all available products from the page
        List<String> unsortedlList = new ArrayList<>();//array is created here to store the name of items
        for(WebElement el:productsBeforeFilter){
            unsortedlList.add(el.getText()); //all items are stored in the array as string. This is unsorted array.
        }
        List<String> sortedList = new ArrayList<>(unsortedlList); //Unsorted array is duplicated here.
        Collections.sort(sortedList,Collections.reverseOrder());//our newly duplicated array memebers are sorted in descending order as required

        ip.filterProducts("Name (Z to A)"); //filter is applied in descending order from the webpage icon

        List<WebElement> productsAfterFilter = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_name']")));//collects the name list again after sorting
        List<String> finalList = new ArrayList<>();//array is created to store the data again after using sort option
        for(WebElement el:productsAfterFilter){
            finalList.add(el.getText()); //name of the items is being stored in array
        }
        Assert.assertEquals(finalList,sortedList,"Products are not sorted");//this is checking if the final list after using sort option and our manually sorted items are matching or not
    }

    //Test for products filter Price (Low to high)
    @Test (priority = 4) //this test will be carried out in 4th place
    //finds list, stores & sorts. Then uses sort option, collects the list after sort and matches to verify
    public void filterByPriceLtoHTest(){
        InventoryPage ip = new InventoryPage(dr,wait);
        List<WebElement> prices = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_price']")));//collects prices of all available products from the page
        List<Double> unsortedPriceList = new ArrayList<>();//array is created here to store the prices of items in double format

        for(WebElement el: prices){
            unsortedPriceList.add(Double.parseDouble(el.getText().replace("$","")));//all prices are stored in the array as double removing the $ symbol. This is unsorted array.
        }

        List<Double> sortedPriceList = new ArrayList<>(unsortedPriceList); //Unsorted array is duplicated here.
        Collections.sort(sortedPriceList);//our newly duplicated array memebers are sorted in low to high order as required.

        ip.filterProducts("Price (low to high");//filter is applied in descending order from the webpage icon

        List<WebElement> sortedPrices = wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_price']")));//collects the price list again after sorting
        List<Double> finalPriceList = new ArrayList<>(); //new array is created to store the prices in double format after using filter option
        for(WebElement el:sortedPrices){
            finalPriceList.add(Double.parseDouble(el.getText().replace("$","")));//stores the new list of prices in our array as double removinf the $ symbol after using filter option.
        }
        Assert.assertEquals(finalPriceList,sortedPriceList,"Products not sorted");//this is checking if the final list after using sort option and our manually sorted items are matching or not
    }




    //this is for Test Scenarios 4.
    @Test (priority = 5) //this test will be carried out on 5th place
    public void cartTest(){
        CartPage cp = new CartPage(dr,wait);
        List<String> addedItems=cp.addToCart(3); //sending 3 items to add in cart and getting the names of the selected items
        List<String> itemsOnCart=cp.getCartItems(); //this is to withdraw name of items available in our cart
        Assert.assertEquals(addedItems,itemsOnCart,"All items added are not visible"); //this is to check if all selected items are available in cart or not
    }





    //this is for Test Scenarios 5.
    @Test (priority = 6)//this test will be carried out in 6th place
    public void checkout(){
        CheckoutPage cop= new CheckoutPage(dr,wait);
        cop.addToCart(3);//we are using cartpage function to add 3 items in cart as these codes are already written for previous code and is reusable. we donot require name list for return,hence data are not stored
        cop.getCartItems();//we are calling this function so it will navigate us to cart pages. we donot require name now, hence return data are not stored
        cop.checkout("Bikesh","Shrestha","44600");//this will send required information for checkout process

        WebElement testMessage= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")));//locates the are where expected message will be delivered
        Assert.assertEquals("THANK YOU FOR YOUR ORDER",testMessage.getText(),"Checkout not Performed");//performs final test for checking if user has been redirected with assurance message of product order
    }
}
