package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("flower@gmail.com").setPassword("Flower123!"));

        logger.info("Before method finished logout");

        app.getHelperContact().provideContacts();


    }

//****************************HOMEWORK*******************************************

//    @Test
//    public void removeFirstContact(){
//
//        logger.info("Start test with name 'removeFirstContact'");
//
//        int before = app.getHelperContact().provideContacts();
//
//        logger.info("Contacts size before removing first contact: " + before);
//
//
//        app.getHelperContact().openContactsForm();
//        app.getHelperContact().openContactField();
//        app.getHelperContact().clickRemoveButton();
//
//        app.getHelperContact().pause(2000);
//
//
//        int after = app.getHelperContact().provideContacts();
//
//        logger.info("Contacts size after removing first contact: " + after);
//
//
//        Assert.assertEquals(after, before-1);
//
//        logger.info("Assert check: contacts size after removing first contact equals contact size before removing first contact - 1");
//
//
//    }
//
//    @Test
//    public void removeAllContacts(){
//        logger.info("Start test with name 'removeAllContacts'");
//
//        int before = app.getHelperContact().provideContacts();
//
//        logger.info("Contacts size before removing all contacts: " + before);
//
//
//        app.getHelperContact().openContactsForm();
//        app.getHelperContact().removeAllContacts();
//
//
//        int after = app.getHelperContact().provideContacts();
//
//        logger.info("Contacts size after removing all contacts: " + after);
//
//
//        Assert.assertEquals(app.getHelperContact().provideContacts(), 0);
//        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
//
//
//        logger.info("Assert checks: no contacts in the list, element 'No contacts here' is displayed");
//
//
//
//    }

    @Test(groups = {"smoke"})
    public void removeFirstContact(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }

    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperContact().isNoContactsHereDisplayed());

    }



}
