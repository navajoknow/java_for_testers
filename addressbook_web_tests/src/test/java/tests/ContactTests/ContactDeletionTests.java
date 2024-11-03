package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteContact();
    }

}
