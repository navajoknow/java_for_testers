package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        int contactCount = app.contacts().getCount();
        app.contacts().deleteContact();
        int newGroupCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newGroupCount);
    }

    @Test
    public  void canDeleteAllContacts() {
        if (app.contacts().getCount() < 2) {
            app.contacts().createContact(new ContactData());
            app.contacts().createContact(new ContactData());
        }
        app.contacts().deleteMultipleContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
