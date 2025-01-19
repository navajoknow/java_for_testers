package ru.must.addressbook.tests.ContactTests;
import ru.must.addressbook.common.CommonFunctions;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (!app.contacts().getList().isEmpty())
            app.contacts().deleteMultipleContacts();
        for (int i = 0; i < 2; i++) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10)));
//                  .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        var oldContacts = app.hbm().getContactList();

        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testData);

        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
//      сортировка по возрастанию идентификаторов с использованием comparator: функция сортировки передается
//      как параметр, что свойственно функциональным языкам программирования
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        expectedList.sort(compareById);
        newContacts.sort(compareById);
//      костыль из 2 строк: обеспечиваем соответствие по полю photo
//      var contactWithSamePhoto = newContacts.get(newContacts.size()-1).withPhoto("");
//      newContacts.set(newContacts.size()-1, contactWithSamePhoto);
        Assertions.assertEquals(expectedList, newContacts);
    }
}
