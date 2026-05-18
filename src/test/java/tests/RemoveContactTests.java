package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("flower@gmail.com").setPassword("Flower123!"));

        app.getHelperContact().provideContacts();


    }



    @Test
    public void removeFirstContact(){
        //Assert size contact list less by 1
    }

    @Test
    public void removeAllContacts(){
        //No contacts here is present


    }


}
