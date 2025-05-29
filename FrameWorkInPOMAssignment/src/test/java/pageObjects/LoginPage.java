package pageObjects;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginPage extends Base {

    @FindBy(id="user-name") WebElement userNameField;
    @FindBy(id="password")   WebElement passwordField;
    @FindBy(id="login-button")  WebElement loginButton;
    @FindBy(xpath = "//h3[@data-test='error']") WebElement msgConfirmation;

    public LoginPage(WebDriver dr){
        super(dr);
    }

    public void login(String user, String password){
        try {
            wait.until(ExpectedConditions.visibilityOf(userNameField)).sendKeys(user);
            wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        }
        catch (Exception e){
            System.out.println("Login Failed: "+e.getMessage());
        }
    }

    public String getConfirmationMsg(){
        return wait.until(ExpectedConditions.visibilityOf(msgConfirmation)).getText();
    }
}
