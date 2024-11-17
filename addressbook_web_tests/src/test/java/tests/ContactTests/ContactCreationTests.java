package tests.ContactTests;

import models.ContactData;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var first_name : List.of("", "first name")) {
            for (var middle_name : List.of("", "middle name")) {
                for (var last_name : List.of("", "last name")) {
                    result.add(new ContactData().
                            withFirstName(first_name).
                            withMiddleName(middle_name).
                            withLastName(last_name));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().
                    withFirstName(randomString(i * 10)).
                    withMiddleName(randomString(i * 10)).
                    withLastName(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        // в т.ч. присваиваем в новом созданном контакте для поля middle_name пустую строку, чтобы при сравнениии контактов
        // тест не сообщал о несоответствии
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withMiddleName(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

}