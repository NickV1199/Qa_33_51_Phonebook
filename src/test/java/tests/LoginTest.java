package tests;

import manager.DataProviderUser;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends TestBase {


//    @Test
//    public void test() {
//
//    }

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {

        //If button signe out present ---> logout

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }

    }



    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess1(String email, String password) {

        logger.info("Start test with name 'loginSuccess1'");
        //logger.info("Test data --->  email: 'flower@gmail.com' & password: 'Flower123!'");
        logger.info("Test data --->  email: "+ email + " password: " + password);

        User user = new User().setEmail("flower@gmail.com").setPassword("Flower123!");


        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check is element button 'Signe out' present");

    }




//    @Test
//    //public void loginSuccess() {
//
//        logger.info("Start");
//
//        app.getHelperUser().openLoginRegistrationForm();
//        app.getHelperUser().fillLoginRegistrationForm("flower@gmail.com", "Flower1234!");
//        app.getHelperUser().submitLogin();
////
////        //Assert
//////        Assert.assertEquals();
//////        Assert.assertNotEquals();
//////        Assert.assertTrue();
//////        Assert.assertFalse();
////
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

@Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
public void loginSuccessModelDPF(User user) {
    logger.info("Test data---> " + user.toString());
    app.getHelperUser().openLoginRegistrationForm();
    app.getHelperUser().fillLoginRegistrationForm(user);
    app.getHelperUser().submitLogin();
    Assert.assertTrue(app.getHelperUser().isLogged());
    logger.info("Assert check is element button 'Sign out' present");
}

    @DataProvider
    public Iterator<Object[]>loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"flower@gmail.com", "Flower123!"});
        list.add(new Object[]{"margo@gmail.com", "Mmar123456$"});
        list.add(new Object[]{"margo@gmail.com", "Mmar123456$"});


        return list.iterator();

    }





    @Test(groups = {"smoke"})
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
