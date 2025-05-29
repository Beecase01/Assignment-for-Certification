package pageObjects;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends Base {

    public InventoryPage(WebDriver dr){
        super(dr);
    }

    @FindBy(xpath = "//select[@class='product_sort_container']") WebElement filterButton;
    @FindBy(xpath = "//select[@class='product_sort_container']/option") List<WebElement> filterOptions;
    By itemList = By.xpath("//div[@class='inventory_item_name']");
    By priceList = By.xpath("//div[@class='inventory_item_price']");


    public void sortProducts(String filterCriteria){
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(filterOptions));
        for(WebElement e: filterOptions){
            if(e.getText().contains(filterCriteria)){
                e.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemList));
    }

    public List<String> getproductlist(){
        List<WebElement> elementList =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemList));
        List<String> stringList = new ArrayList<>();
        for(WebElement el:elementList){
            stringList.add(el.getText());
        }
        return  stringList;
    }

    public List<Double> getPriceList(){
        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceList));
        List<Double> doubleList = new ArrayList<>();
        for(WebElement el:prices){
            doubleList.add(Double.parseDouble(el.getText().replace("$","")));
        }
        return doubleList;
    }
}
