package ru.must.addressbook.tests.ContactTests;

import ru.must.addressbook.common.CommonFunctions;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;
import ru.must.addressbook.tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
//      при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                     .withFirstName("canDeleteContact test"));
//                   .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public  void canDeleteAllContacts()  {
        if (app.hbm().getContactCount() < 2) {
            for (int i = 0; i < 2; i++) {
                app.hbm().createContact(new ContactData()
                        .withFirstName("canDeleteAllContacts test"));
//                      .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
            }
        }
        app.contacts().deleteMultipleContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

}
