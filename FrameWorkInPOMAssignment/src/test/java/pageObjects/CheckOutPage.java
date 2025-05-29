package pageObjects;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends CartPage {

    public CheckOutPage(WebDriver dr){
        super(dr);
    }

    @FindBy(xpath ="//a[@class='btn_action checkout_button']" )    WebElement checkOutButton;
    @FindBy(xpath ="//input[@id='first-name']" )    WebElement firstName;
    @FindBy(xpath ="//input[@id='last-name']" )    WebElement lastName;
    @FindBy(xpath ="//input[@id='postal-code']" )    WebElement postalCode;
    @FindBy(xpath = "//input[@class='btn_primary cart_button']")    WebElement continueButton;
    @FindBy(xpath ="//a[@class='btn_action cart_button']" )    WebElement finishButton;
    @FindBy(xpath = "//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]")   WebElement confirmation;

    public void checOut(String first,String last, String post){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutButton)).click();
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(first);
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(last);
        wait.until(ExpectedConditions.visibilityOf(postalCode)).sendKeys(post);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String message(){
        return String.valueOf(wait.until(ExpectedConditions.visibilityOf(confirmation)));
    }
}
