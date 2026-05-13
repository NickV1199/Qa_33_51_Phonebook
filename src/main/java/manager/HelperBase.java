package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);

            element.click();
            element.clear();
        if(text!= null){
            element.sendKeys(text);
        }

    }

    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator){
        List<WebElement>list = wd.findElements(locator);
        return list.size()>0;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void clearNew(WebElement element){
        String os = System.getProperty("os.name");
        System.out.println(os);


        element.sendKeys(" ");
//        element.sendKeys(Keys.BACK_SPACE);


        if(os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL, "a");
        }else {
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);

    }

}
