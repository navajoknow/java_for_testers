package tests.ContactTests;

import models.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        //при первом обращении к методу contacts() помощник (экземпляр ContactHelper) будет проиницализрован
        app.contacts().createContact(new ContactData(
                "default First_name",
                "default Middle_name",
                "default Last_name",
                "default Nickname",
                "default Title",
                "default Company",
                "default Address",
                "default Home_phone",
                "default Mobile",
                "default Work_phone",
                "default Fax",
                "default Email",
                "default Email2",
                "default Email3",
                "default Homepage"
                ));
    }

    @Test
    public void canCreateEmptyContact() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFirstNameOnly() {
        //withName не модифицирует объект, в котором он вызван, а возвращает новый объект
        app.contacts().createContact(new ContactData().withFirstName("default First_name only"));
    }
}
