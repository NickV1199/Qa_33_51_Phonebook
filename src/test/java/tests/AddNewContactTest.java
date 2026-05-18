package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
                .phone("053678" + i)
                .email("bobert"+i+"@gmail.com")
                .address("Tel Aviv, Israel")
                .description("friend")
                .build();


        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(1000);

        app.getHelperContact().getScreen("src/test/srceenshots/screen -"+i+".png");

        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

            }

    @Test
    public void addNewContactSuccessRequaieredFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, UAS")
                .email("bobert"+i+"@gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }



    @Test
    public void addNewContactWrongName() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, UAS")
                .email("bobert"+i+"@gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());


    }

    @Test
    public void addNewContactWrongLastName() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("")
                .phone("011678" + i)
                .address("New York, UAS")
                .email("bobert"+i+"@gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());

    }

    @Test
    public void addNewContactWrongEmail() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, UAS")
                .email("bobert"+i+"gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);



        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: должно иметь формат адреса электронной почты"));
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());
        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());

    }

    @Test
    public void addNewContactWrongPhone() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("")
                .address("New York, UAS")
                .email("bobert"+i+"@gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();


        Assert.assertTrue(app.getHelperUser().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());
        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
    }

    @Test
    public void addNewContactWrongAddress() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("")
                .email("bobert"+i+"@gmail.com")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());
        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
    }


//    @AfterMethod
//    public void postCondition() {
//        app.getHelperUser().logout();
//    }

}
