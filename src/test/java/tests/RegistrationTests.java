package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }
    }


    @Test
    public void registrationSuccess() {

        int z = (int) ((System.currentTimeMillis() / 1000) % 360);
        System.out.println(z);

        //короткая запись
        //int i = (int)((System.currentTimeMillis() / 1000) % 360);
        //User user = new User().setEmail("brown"+i+"@gmail.com).withPassword("Brown1234#");

        logger.info("Start test with name 'registrationSuccess'");
        logger.info("Test data --->  email: 'brown" + z + "@gmail.com' & password: 'Brown12345678!'");


        User user = new User()
                .setEmail("brown" + z + "@gmail.com")
                .setPassword("Brown12345678!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

        logger.info("Assert check: is element button 'Signe out' present and is element 'No Contacts Here' is displayed");

    }


    @Test
    public void registrationWrongEmail() {

        logger.info("Start test with name 'registrationWrongEmail'");
        logger.info("Test data --->  email: 'browngmail.com' & password: 'Brown12345678!'");

        User user = new User()
                .setEmail("browngmail.com")
                .setPassword("Brown12345678!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

        logger.info("Assert check: is alert signe 'Wrong email or password format' present");

    }

    @Test
    public void registrationWrongPassword() {

        logger.info("Start test with name 'registrationWrongPassword'");
        logger.info("Test data --->  email: 'brown@gmail.com' & password: 'Brown'");

        User user = new User()
                .setEmail("brown@gmail.com")
                .setPassword("Brown");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

        logger.info("Assert check: is alert signe 'Wrong email or password format' present");

    }

    @Test
    public void registrationExistingUser() {

        logger.info("Start test with name 'registrationExistingUser'");
        logger.info("Test data --->  email: 'flower@gmail.com' & password: 'Flower1234!'");

        User user = new User()
                .setEmail("flower@gmail.com")
                .setPassword("Flower1234!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

        logger.info("Assert check: is alert signe 'User already exist' present");

    }


}
