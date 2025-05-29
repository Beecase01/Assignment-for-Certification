package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base{
    protected WebDriver dr;
    protected WebDriverWait wait;

    public Base(WebDriver dr){
        this.dr=dr;
        this.wait = new WebDriverWait(dr, Duration.ofSeconds(10));
        PageFactory.initElements(dr,this);
    }
}
