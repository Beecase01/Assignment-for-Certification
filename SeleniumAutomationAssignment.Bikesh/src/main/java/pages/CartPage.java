package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver dr;
    WebDriverWait wait;

    //locators to locate Cart Icon, Products Names, Add Button on carts, and list of added items in carts after addition
    By cartIcon = By.xpath("//a[contains(@class,'shopping_cart')]");
    By itemNames = By.xpath("//div[@class='inventory_item_label']/descendant::div[1]");
    By addButtons = By.xpath("//div[@class='inventory_item_label']/following-sibling::div/child::button");
    By listInCart = By.xpath("//div[@class='inventory_item_name']");


    public CartPage(WebDriver dr, WebDriverWait wait){//constructor is created to instantiate
        this.dr=dr;
        this.wait=wait;
    }

    public List<String> addToCart(int number){//get number of items to add in cart and returns the name-list of items selected to add in the cart

        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));//stores all Product names
        List<String> addedItems = new ArrayList<>();//array is created to store name of Products selected as a string type for further sorting actions

        List<WebElement> moveToCart = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addButtons));//stores adding options for all products

        int selection = (number< moveToCart.size())?number:moveToCart.size()-2;//this avoids errors when user tries to add more items than available. In such case, it will select all items except last two

        for(int i=0;i<=selection;i++){
            moveToCart.get(i).click();//items are moving to cart serially
            addedItems.add(items.get(i).getText());//name of added items are being recorded simultaneously
        }

        return addedItems;//list of items selected to add in cart are returned for final assertion
    }
    public List<String> getCartItems(){//this return the items available in user's cart
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));//locates the cart icon
        cart.click();//navigates to cart page
        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listInCart));//makes a list of items available in user's cart page
        List<String> itemsOnCart = new ArrayList<>();//array is created to store name of Products in Cart page as a string type for further sorting actions
        for(WebElement el:cartItems){
            itemsOnCart.add(el.getText()); //name of items are added in the array
        }
        return itemsOnCart;//list of added items from the cart page is returned
    }
}
