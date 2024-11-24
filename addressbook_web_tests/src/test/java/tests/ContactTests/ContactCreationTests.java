package tests.ContactTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import models.ContactData;
import org.junit.jupiter.api.Assertions;
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
//            for (var middle_name : List.of("", "middle name")) {
//                for (var last_name : List.of("", "last name")) {
//                    result.add(new ContactData().
//                            withFirstName(first_name).
//                            withMiddleName(middle_name).
//                            withLastName(last_name).
//                            withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
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

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        // при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        // в т.ч. присваиваем в новом созданном контакте для полей middle_name и photo пустую строку,
        // чтобы при сравнениии контактов тест не сообщал о несоответствии
        expectedList.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withMiddleName("").withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

}
