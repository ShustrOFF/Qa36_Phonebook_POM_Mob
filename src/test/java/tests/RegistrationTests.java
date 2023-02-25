package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {


    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(100);
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("maxpayne" + i + "@gmail.com")
                .fillPassword("Maxx12345$")
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
    }

    @Test
    public void registrationSuccessModel() {
        Random random = new Random();
        int i = random.nextInt(100);
        Auth auth = Auth.builder().email("maxpayne" + i + "@gmail.com").password("Maxx12345$").build();
        boolean res = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
    }

    @Test(enabled = false)
    public void registrationWrongEmail() {
        boolean res = new AuthenticationScreen(driver)
                .fillEmail("maxpaynegmail.com")
                .fillPassword("Maxx12345$")
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
    }

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();
    }
}
