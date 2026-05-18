package tests;

import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


//    @Test
//    public void test() {
//
//    }

    @BeforeMethod
    public void preCondition() {

        //If button signe out present ---> logout

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }

    }

    @Test
    public void loginSuccess1() {

        logger.info("Start test with name 'loginSuccess1'");
        logger.info("Test data --->  email: 'flower@gmail.com' & password: 'Flower123!'");

        User user = new User().setEmail("flower@gmail.com").setPassword("Flower123!");


        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check is element button 'Signe out' present");

    }


    //@Test
//    //public void loginSuccess() {
//
//        logger.info("Start");
//
//        app.getHelperUser().openLoginRegistrationForm();
//        app.getHelperUser().fillLoginRegistrationForm("flower@gmail.com", "Flower1234!");
//        app.getHelperUser().submitLogin();
//
//        //Assert
////        Assert.assertEquals();
////        Assert.assertNotEquals();
////        Assert.assertTrue();
////        Assert.assertFalse();
//
//        Assert.assertTrue(app.getHelperUser().isLogged());
//
//        logger.info("End");
//
//    }


//    @Test
//    public void loginSuccessModel() {
//        app.getHelperUser().openLoginRegistrationForm();
//        app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123456$");
//        app.getHelperUser().submitLogin();
//
//        //Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();
//
//        Assert.assertTrue(app.getHelperUser().isLogged());
//
//    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test data --->  email: 'flowergmail.com' & password: 'Flower123!'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("flowergmail.com", "Flower123!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test data --->  email: 'flower@gmail.com' & password: 'Flower12'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("flower@gmail.com", "Flower12");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }

    @Test
    public void loginUregisteredUser() {
        logger.info("Test data --->  email: 'flower@gmail.com' & password: 'Flower12'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("flower_1@gmail.com", "Flower124!!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }


}
