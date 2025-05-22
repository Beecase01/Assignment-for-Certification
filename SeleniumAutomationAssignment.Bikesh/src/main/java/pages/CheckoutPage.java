package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends CartPage {//child class is created because this requires some actions which are already done in Cart Page so the codes can be reused. It will e

    //these are locators to find checkout button, and details for shipment and other continuing buttons for final checkout request
    By checkOutButtonPath = By.xpath("//a[@class='btn_action checkout_button']");
    By firstNameField = By.xpath("//input[@id='first-name']");
    By lastNameField = By.xpath("//input[@id='last-name']");
    By postalCodeField = By.xpath("//input[@id='postal-code']");
    By continueField = By.xpath("//input[@class='btn_primary cart_button']");
    By finishField = By.xpath("//a[@class='btn_action cart_button']");

    public CheckoutPage(WebDriver dr, WebDriverWait wait){//constructor is created for LoginPage so that instances of the page could be created during test process
        super(dr, wait);//calling up the parent constructor as we are on child class and as most of required actions are common with parent class
    }

    public void checkout(String firstName, String lastName, String postalCode){
        WebElement checkOutButton = wait.until(ExpectedConditions.elementToBeClickable(checkOutButtonPath));//locates checkout button
        checkOutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);//locates first name field and inserts username
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);//locates last name field and inserts lastname
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(postalCode); //locates postal code field and inserst postal code
        wait.until(ExpectedConditions.elementToBeClickable(continueField)).click();//locates continue field and clicks it
        wait.until(ExpectedConditions.elementToBeClickable(finishField)).click();//locates finish field and clicks itt
    }
}
