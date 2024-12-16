package ru.must.tests.ContactTests;

import ru.must.common.CommonFunctions;
import ru.must.models.ContactData;
import ru.must.models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.must.tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactDeletionTests extends TestBase {

    @Test
    public void canDeleteContact() {
        // при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
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
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test_group", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var newContact = new ContactData()
                .withFirstName("test_contact")
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));

        app.contacts().createContactInGroup(newContact, group);
        var contactListInGroup = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        contactListInGroup.sort(compareById);
        var newContactWithId = contactListInGroup.get(contactListInGroup.size()-1);

        app.contacts().removeContactFromGroup(newContactWithId, group);

        var newContactListInGroup = app.hbm().getContactsInGroup(group);
        newContactListInGroup.sort(compareById);
        var expectedList = new ArrayList<>(contactListInGroup);
        expectedList.remove(expectedList.size()-1);
        Assertions.assertEquals(expectedList, newContactListInGroup);
    }

    @Test
    public  void canDeleteAllContacts() {
        if (app.hbm().getContactCount() < 2) {
            for (int i = 0; i < 2; i++) {
                app.hbm().createContact(new ContactData().withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
            }
        }
        app.contacts().deleteMultipleContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

}
