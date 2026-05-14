package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTest extends TestBase {

    @BeforeMethod


    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("flower@gmail.com").setPassword("Flower123!"));

    }

    @Test
    public void addNewContactSuccessAllFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Bobert")
                .Phone("053678" + i)
                .email("bobert"+i+"@gmail.com")
                .address("Tel Aviv, Israel")
                .description("friend")
                .build();


        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        Assert.assertFalse(app.getHelperContact().isContactsNotEmpty());


    }

    @Test
    public void addNewContactSuccessRequaieredFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .Phone("011678" + i)
                .address("New York, UAS")
                .email("bobert"+i+"@gmail.com")
                .description("enemy")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        Assert.assertFalse(app.getHelperContact().isContactsNotEmpty());


    }



    @Test
    public void addNewContactWrongName(){

    }

    @Test
    public void


    //@AfterMethod
    //public void postCondition() {
    //    app.getHelperUser().logout();
    //}

}
