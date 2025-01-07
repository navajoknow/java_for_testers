package ru.must.addressbook.tests.ContactTests;

import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    @Test
    void testContactData() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        // сравниваем телефоны
        var phonesOnMainPage = app.contacts().getPhonesOnMainPage(contact);
        var phonesOnEditPage = app.contacts().getPhonesOnEditPage(contact);
        Assertions.assertEquals(phonesOnMainPage, phonesOnEditPage);
        // адреса электронной почты
        var emailsOnMainPage = app.contacts().getEmailsOnMainPage(contact);
        var emailsOnEditPage = app.contacts().getEmailsOnEditPage(contact);
        Assertions.assertEquals(emailsOnMainPage, emailsOnEditPage);
        // почтовые адреса
        var addressOnMainPage = app.contacts().getAddressOnMainPage(contact);
        var addressOnEditPage = app.contacts().getAddressOnEditPage(contact);
        Assertions.assertEquals(addressOnMainPage, addressOnEditPage);
    }

}
