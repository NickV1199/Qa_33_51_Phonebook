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
        logger.info("Before method finished logout");

    }

    @Test
    public void addNewContactSuccessAllFields() {

        logger.info("Start test with name 'addNewContactSuccessAllFields'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bob")
                .lastName("Bobert")
                .phone("053678" + i)
                .email("bobert"+i+"@gmail.com")
                .address("Tel Aviv, Israel")
                .description("friend")
                .build();

        logger.info("Test data --->  name: 'Bob'; lastName: 'Bobert', phone: '053678" + i + "' email: 'bobert" + i + "@gmail.com', address: 'Tel Aviv, Israel', description: 'friend'");


        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(1000);

        app.getHelperContact().getScreen("src/test/srceenshots/screen -"+i+".png");

        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        logger.info("Assert check: are name and phone number of the added contact equals test data");

            }

    @Test
    public void addNewContactSuccessRequaieredFields() {

        logger.info("Start test with name 'addNewContactSuccessRequaieredFields'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, USA")
                .email("billbert"+i+"@gmail.com")
                .build();

        logger.info("Test data --->  name: 'Bill'; lastName: 'Billbert', phone: '011678" + i + "' email: 'billbert" + i + "@gmail.com', address: 'New York, USA'");

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        logger.info("Assert check: are name and phone number of the added contact equals test data");
    }



    @Test
    public void addNewContactWrongName() {
        logger.info("Start test with name 'addNewContactWrongName'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, USA")
                .email("billbert"+i+"@gmail.com")
                .build();

        logger.info("Test data --->  name: ' '; lastName: 'Billbert', phone: '011678" + i + "' email: 'billbert" + i + "@gmail.com', address: 'New York, USA'");

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());

        logger.info("Assert check: is element 'Save' button displayed and is element 'Add' button is still active");


    }

    @Test
    public void addNewContactWrongLastName() {

        logger.info("Start test with name 'addNewContactWrongLastName'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("")
                .phone("011678" + i)
                .address("New York, USA")
                .email("billbert"+i+"@gmail.com")
                .build();

        logger.info("Test data --->  name: 'Bill'; lastName: ' ', phone: '011678" + i + "' email: 'billbert" + i + "@gmail.com', address: 'New York, USAl'");

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());

        logger.info("Assert check: is element 'Save' button displayed and is element 'Add' button is still active");

    }

    @Test
    public void addNewContactWrongEmail() {

        logger.info("Start test with name 'addNewContactWrongEmail'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("New York, USA")
                .email("billbert"+i+"gmail.com")
                .build();

        logger.info("Test data --->  name: 'Bill'; lastName: 'Billbert', phone: '011678" + i + "' email: 'billbert" + i + "gmail.com', address: 'New York, USAl'");

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);



        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: должно иметь формат адреса электронной почты"));
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());
        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());

        logger.info("Assert check: is element 'Save' button displayed and is element 'Add' button is still active, and is alert signe 'Email not valid: должно иметь формат адреса электронной почты' is present");

    }

    @Test
    public void addNewContactWrongPhone() {

        logger.info("Start test with name 'addNewContactWrongPhone'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("")
                .address("New York, USA")
                .email("bobert"+i+"@gmail.com")
                .build();

        logger.info("Test data --->  name: 'Bill'; lastName: 'Billbert', phone: ' ', email: 'billbert" + i + "@gmail.com', address: 'New York, USAl'");


        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();


        Assert.assertTrue(app.getHelperUser().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());
        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());


        logger.info("Assert check: is element 'Save' button displayed and is element 'Add' button is still active, and is alert signe 'Phone not valid: Phone number must contain only digits! And length min 10, max 15!' is present");
    }

    @Test
    public void addNewContactWrongAddress() {

        logger.info("Start test with name 'addNewContactWrongAddress'");

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bill")
                .lastName("Billbert")
                .phone("011678" + i)
                .address("")
                .email("billbert"+i+"@gmail.com")
                .build();

        logger.info("Test data --->  name: 'Bill'; lastName: 'Billbert', phone: '011678" + i + "' email: 'billbert" + i + "@gmail.com', address: ' '");

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1000);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isSaveDisplayed());
        Assert.assertTrue(app.getHelperContact().isAddButtonActive());



        logger.info("Assert check: is element 'Save' button displayed and is element 'Add' button is still active");
    }


//    @AfterMethod
//    public void postCondition() {
//        app.getHelperUser().logout();
//    }

}
