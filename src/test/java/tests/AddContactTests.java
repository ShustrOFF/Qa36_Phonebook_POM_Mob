package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;


public class AddContactTests extends AppiumConfig {

    @BeforeMethod
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("johhnytherebel@gmail.com").password("Vovka1234$").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }


    @Test
    public void addNewContactSuccess() {
        //if contacts> 6 ---> delete all
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Max")
                .lastName("Payne")
                .email("max" + i + "@gmail.com")
                .phone("08667885" + i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .isContactsLessThanSix()
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByNameLastName(contact.getName(), contact.getLastName())
                .isContactAddedByPhone(contact.getPhone())
                .logout();
    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Payne")
                .email("max@gmail.com")
                .phone("086678855656")
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isMessageContainsText("name=must not be blank")
                .returnToContactList()
                .logout();

    }

    @Test
    public void addNewContactEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Max")
                .email("max@gmail.com")
                .phone("086678855677")
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isMessageContainsText("lastName=must not be blank")
                .returnToContactList()
                .logout();
    }


}
