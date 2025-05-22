package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class InventoryPage {

    WebDriver dr;
    WebDriverWait wait;

    //locators are created here to find the filter button and the available filter-options
    By filterButton = By.xpath("//select[@class='product_sort_container']");//this locates the filter icon
    By filterOptions = By.xpath("//select[@class='product_sort_container']/option");//this locates the filter options

    public InventoryPage(WebDriver dr, WebDriverWait wait) {//constructor is created for InventoryPage so that instances of the page could be created during test process
        this.dr = dr;
        this.wait = wait;
    }
    public void filterProducts(String filterCriteria) {
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filterButton));//finds the filter icon
        filter.click();

        List<WebElement> filterlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filterOptions));//stores all filter options from the webpage

        for (WebElement el : filterlist) {
            if (el.getText().contains(filterCriteria)) {//searches correct type of filter from user input
                el.click(); //selects the correct filter option
                break;//once it finds, it will jump out of the loop
            }
        }
    }
}