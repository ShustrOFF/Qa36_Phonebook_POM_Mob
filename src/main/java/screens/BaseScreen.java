package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen {
    AppiumDriver<MobileElement> driver;

    public BaseScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void type(MobileElement element, String text) {
        if(text!=null){
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void type1(MobileElement element, String text) {
        element.click();
        element.clear();
        if(text!=null){
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }
    public void should(MobileElement element, int time) {
        new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isShouldHave(MobileElement element, String text, int time){
        return new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public void pause(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
