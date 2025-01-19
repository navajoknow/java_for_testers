package ru.must.addressbook.tests.ContactTests;

import ru.must.addressbook.common.CommonFunctions;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhonesV1() {
        for (int i = 0; i < 2; i++) {
                app.hbm().createContact(new ContactData()
                        .withHome(CommonFunctions.randomString(5))
                        .withMobile(CommonFunctions.randomString(5))
                        .withWork(CommonFunctions.randomString(5)));
            }
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getAllContactsPhones();
        for (var contact: contacts) {
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.fax())
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }
    }

    // реализация этого же метода, но сравниваем уже два словаря целиком, а не по отдельным элементам
    @Test
    void testPhonesV2() {
        for (int i = 0; i < 2; i++) {
                app.hbm().createContact(new ContactData()
                        .withHome(CommonFunctions.randomString(5))
                        .withMobile(CommonFunctions.randomString(5))
                        .withWork(CommonFunctions.randomString(5)));
            }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.fax())
                        .filter(s -> s != null && !s.isEmpty())
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getAllContactsPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testContactData() {
        app.hbm().createContact(new ContactData()
                    .withHome(CommonFunctions.randomString(5))
                    .withMobile(CommonFunctions.randomString(5))
                    .withWork(CommonFunctions.randomString(5))
                    .withEmail(CommonFunctions.randomString(5))
                    .withEmail2(CommonFunctions.randomString(5))
                    .withEmail3(CommonFunctions.randomString(5))
                    .withAddress(CommonFunctions.randomString(5)));
        var contacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        contacts.sort(compareById);
        var contact = contacts.get(contacts.size()-1);

        // сравниваем телефоны
        var phonesOnMainPage = app.contacts().getPhonesOnMainPage(contact);
        var phonesOnEditPage = app.contacts().getPhonesOnEditPage(contact);
        Assertions.assertEquals(phonesOnMainPage, phonesOnEditPage);

        // адреса эл. почты
        var emailsOnMainPage = app.contacts().getEmailsOnMainPage(contact);
        var emailsOnEditPage = app.contacts().getEmailsOnEditPage(contact);
        Assertions.assertEquals(emailsOnMainPage, emailsOnEditPage);

        // почтовые адреса
        var addressOnMainPage = app.contacts().getAddressOnMainPage(contact);
        var addressOnEditPage = app.contacts().getAddressOnEditPage(contact);
        Assertions.assertEquals(addressOnMainPage, addressOnEditPage);
    }

}
