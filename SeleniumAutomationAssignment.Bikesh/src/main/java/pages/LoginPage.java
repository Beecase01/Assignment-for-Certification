package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginPage {
    WebDriver dr;
    WebDriverWait wait;

    //these are locators for login credentials and login button
    By userNameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    //constructor is created for LoginPage so that instances of the page could be created during test process
    public LoginPage(WebDriver dr, WebDriverWait wait) {
        this.dr = dr;
        this.wait = wait;
    }

    //these performs login action
    public void login(String user, String password){
        WebElement userNameE = wait.until(visibilityOfElementLocated(userNameField)); //User Name field is located
        userNameE.sendKeys(user); // User Name is inserted
        WebElement passwordE = wait.until(visibilityOfElementLocated(passwordField)); // Password field is located
        passwordE.sendKeys(password); //Password is inserted

        WebElement loginE = wait.until(visibilityOfElementLocated(loginButton)); //Login Button is located
        loginE.click(); //Login Button is clicked here.
    }
}
