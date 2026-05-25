package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {

    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddForm() {
        click(By.xpath("//a[normalize-space()='ADD']"));
    }


    public void fillContactForm(Contact contact) {
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());

    }

    public void submitAddContactForm() {
        click(By.xpath("//div[@class='add_form__2rsm2']//button"));
    }

    public boolean isContactsNotEmpty() {
        return isElementPresent(By.cssSelector(".contact-page_message__2qafk>h1"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement element : list) {
            if (element.getText().equals(name)) {
                return true;
            }

        }
        return false;
    }


    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement element : list) {
            if (element.getText().equals(phone)) {
                return true;
            }

        }
        return false;
    }

   public boolean isSaveDisplayed(){
       WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
       boolean res = wait.until(ExpectedConditions
               .textToBePresentInElement(wd.findElement(By.xpath("//div[@class='add_form__2rsm2']//button")), "Save"));
       return res;
   }


    public boolean isAddButtonActive() {
        return wd.findElement(By.xpath("//a[normalize-space()='ADD']"))
                .getAttribute("class")
                .contains("active");
    }


//    public int provideContacts() {
//        List<WebElement> elements = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        List<String> contacts = new ArrayList<>();
//
//        for (WebElement element : elements) {
//            contacts.add(element.getText());
//        }
//
//        return contacts.size();
//
//    }


    public void openContactsForm() {
        click(By.xpath("//a[normalize-space()='CONTACTS']"));
    }

    public void openContactField() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
    }

    public void clickRemoveButton(){
        click(By.xpath("//button[normalize-space()='Remove']"));
    }

//    public void removeAllContacts() {
//        while (provideContacts() > 0) {
//            openContactsForm();
//            openContactField();
//            clickRemoveButton();
//            pause(2000);
//        }
//    }

    public int removeOneContact(){
        int before = countOfContacts();
        logger.info("Number of Contacts before remove is -->" + before);
        removeContact();

        int after = countOfContacts();
        logger.info("Number of Contacts before remove is -->" + before);
        return before-after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(2000);
    }

    private int countOfContacts(){
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts(){
        while (countOfContacts() != 0){
            removeContact();
        }
    }

    public boolean isNoContactsHereDisplayed() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        boolean res = wait.until(ExpectedConditions
                .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1"))
                        ,"No Contacts here!"));
        return res;

    }

    public void provideContacts(){
        if(countOfContacts() < 3){
            for(int i = 0; i < 3; i++){
                addOneContact();

            }
        }
    }

    private void addOneContact(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Harry")
                .lastName("Potter")
                .email("harry" + i + "@gmail.com")
                .phone("55566777" +i)
                .address("Hogwarts")
                .description("the boy who lives")
                .build();

        openAddForm();
        fillContactForm(contact);
        submitAddContactForm();
        pause(500);
    }

}
