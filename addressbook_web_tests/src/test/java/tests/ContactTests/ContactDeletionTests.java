package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withPhoto("src/test/resources/images/avatar.png"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public  void canDeleteAllContacts() {
        if (app.contacts().getCount() < 2) {
            app.contacts().createContact(new ContactData().withPhoto("src/test/resources/images/avatar.png"));
            app.contacts().createContact(new ContactData().withPhoto("src/test/resources/images/avatar.png"));
        }
        app.contacts().deleteMultipleContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
