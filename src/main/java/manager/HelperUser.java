package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends  HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        //wd.findElement(By.cssSelector("a[href ='/login']"));
        wd.findElement(By.xpath("//a[text()='LOGIN']"));


    }
}
