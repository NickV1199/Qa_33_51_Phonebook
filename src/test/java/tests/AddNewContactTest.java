package tests;

import models.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void addNewContactSuccess1(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Bobert")
                .Phone("053678"+i)
                .address("Tel Aviv, Israel")
                .description("friend")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("flower@gmail.com", "Flower123!");
        app.getHelperUser().submitLogin();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertFalse(app.getHelperContact().isContactsNotEmpty());





    }

    @Test
    public void addNewContactSuccess2(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .Phone("011678"+i)
                .address("New York, UAS")
                .description("enemy")
                .build();

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("flower@gmail.com", "Flower123!");
        app.getHelperUser().submitLogin();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertFalse(app.getHelperContact().isContactsNotEmpty());



    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().logout();
    }

}
