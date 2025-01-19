package ru.must.addressbook.tests.ContactTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;
import ru.must.addressbook.tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactRemoveFromGroupTests extends TestBase {

    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test_group", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var newContact = new ContactData()
                .withFirstName("canRemoveContactFromGroup test");
//              .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));

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

}
