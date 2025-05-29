package pageObjects;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends Base {

    public CartPage(WebDriver dr){
        super(dr);
    }

    @FindBy(xpath = "//a[contains(@class,'shopping_cart')]") WebElement cartIcon;
    @FindBy(xpath = "//div[@class='inventory_item_label']/descendant::div[1]") List<WebElement> itemNames;
    @FindBy(xpath ="//div[@class='inventory_item_label']/following-sibling::div/child::button" )    List<WebElement> moveToCart;
    @FindBy(xpath = "//div[@class='inventory_item_name']")  List<WebElement> listInCart;

    public List<String> addToCart(int number){
        wait.until(ExpectedConditions.visibilityOfAllElements(moveToCart));
        int count = (number<moveToCart.size()?number:moveToCart.size()-2);
        List<String> selectedItems = new ArrayList<>();

        for(int i=0;i<=count;i++){
            moveToCart.get(i).click();
            selectedItems.add(itemNames.get(i).getText());
        }
        return selectedItems;
    }

    public List<String> getCartItems(){
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(listInCart));
        List<String> cartItems = new ArrayList<>();
        for(WebElement el : listInCart){
            cartItems.add(el.getText());
        }
        return cartItems;
    }
}
