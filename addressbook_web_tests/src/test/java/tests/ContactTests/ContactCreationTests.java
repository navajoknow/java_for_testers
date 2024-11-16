package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var first_name : List.of("", "first name")) {
            for (var middle_name : List.of("", "middle name")) {
                for (var last_name : List.of("", "last name")) {
                    result.add(new ContactData(
                            first_name,
                            middle_name,
                            last_name,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

}
