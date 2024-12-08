package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhonesV1() {
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getAllContactsPhones();
        for (var contact: contacts) {
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }
    }

    // реализация этого же метода, но сравниваем уже два словаря целиком, а не по отдельным элементам
    @Test
    void testPhonesV2() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getAllContactsPhones();
        Assertions.assertEquals(expected, phones);
    }

}
