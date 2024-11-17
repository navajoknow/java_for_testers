package manager;
import models.ContactData;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        openContactPage();
        fillContactForm(contact);
        submitCreation();
        goToHomePage();
    }

    public void deleteContact(ContactData contact) {
        goToHomePage();
        selectContact(contact);
        deleteSelectedContacts();
        goToHomePage();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id = '%s']", contact.id())));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.first_name());
        type(By.name("middlename"), contact.middle_name());
        type(By.name("lastname"), contact.last_name());
        type(By.name("nickname"), contact.nickname());
        attach(By.name("photo"), contact.photo());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home_phone());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work_phone());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());
    }

    public boolean isContactPresent() {
        goToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int getCount() {
        goToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteMultipleContacts() {
        goToHomePage();
        selectAllItems();
        deleteSelectedContacts();
        goToHomePage();
    }

    public List<ContactData> getList() {
        goToHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("id");
            var tds = tr.findElements(By.cssSelector("td"));
                var last_name = tds.get(1).getText();
                var first_name = tds.get(2).getText();
                contacts.add(new ContactData().withId(id).withLastName(last_name).withFirstName(first_name));
            }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        goToHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        goToHomePage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.linkText(String.format("edit.php?id='%s'", contact.id())));
    }
}



