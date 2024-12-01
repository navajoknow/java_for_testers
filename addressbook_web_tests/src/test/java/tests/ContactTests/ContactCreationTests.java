package tests.ContactTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var first_name : List.of("", "first name")) {
//                for (var last_name : List.of("", "last name")) {
//                    result.add(new ContactData()
//                            .withFirstName(first_name)
//                            .withLastName(last_name)
//                            .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
//                }
//            }
//        }
          // код для десериализации JSON c использованием библиотеки Jackson:
//        var mapper = new ObjectMapper();
//        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
          // код для десериализации XML c использованием библиотеки Jackson:
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContactProvider() {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {

        // при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        // метод для сбора с помощью JDBC
        var oldContacts = app.jdbc().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size()-1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);

        // костыль из 3 строк: обеспечиваем соответствие по полю photo
        var testPhoto = expectedList.get(expectedList.size()-1).photo();
        var newContactWithSamePhoto = newContacts.get(expectedList.size()-1).withPhoto(testPhoto);
        newContacts.set(newContacts.size()-1, newContactWithSamePhoto);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
    public void canCreateContact(ContactData contact) {

        // сравниваем новый список контактов, собраный из БД, с ожидаемым списком из БД

        // метод для сбора с помощью JDBC
//         var oldContacts = app.jdbc().getContactList();
//         app.contacts().createContact(contact);
//         var newContacts = app.jdbc().getContactList();

        // метод для сбора с помощью Hibernate
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();

        // сортировка по возрастанию
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size()-1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);

        // костыль из 3 строк: обеспечиваем соответствие по полю photo
        var testPhoto = expectedList.get(expectedList.size()-1).photo();
        var newContactWithSamePhoto = newContacts.get(expectedList.size()-1).withPhoto(testPhoto);
        newContacts.set(newContacts.size()-1, newContactWithSamePhoto);
        Assertions.assertEquals(expectedList, newContacts);

        // также сравниваем новый список контактов, собраный через UI, с ожидаемым списком из БД
        var uiContacts = app.contacts().getList();
        uiContacts.sort(compareById);
        // костыль из цикла: обеспечиваем соответствие по полю photo
        for (int i = 0; i < uiContacts.size(); i++) {
            testPhoto = expectedList.get(i).photo();
            uiContacts.set(i, uiContacts.get(i).withPhoto(testPhoto));
        }
        Assertions.assertEquals(expectedList, uiContacts);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("","name", "header","footer"));
        }

        var group = app.hbm().getGroupList().get(0);

        var previousContactListInGroup = app.hbm().getContactsInGroup(group);
        app.contacts().createContactInGroup(contact, group);
        var newContactListInGroup = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContactListInGroup.sort(compareById);
        var expectedList = new ArrayList<>(previousContactListInGroup);
        expectedList.add(contact.withId(newContactListInGroup.get(newContactListInGroup.size()-1).id()));
        expectedList.sort(compareById);

        var testPhoto = expectedList.get(expectedList.size()-1).photo();
        var newContactWithSamePhoto = newContactListInGroup.get(expectedList.size()-1).withPhoto(testPhoto);
        newContactListInGroup.set(newContactListInGroup.size()-1, newContactWithSamePhoto);
        Assertions.assertEquals(expectedList, newContactListInGroup);
    }

}
