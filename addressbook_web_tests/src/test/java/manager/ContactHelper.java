package manager;
import models.ContactData;
import models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        addNewContact();
        fillContactForm(contact);
        attachPhoto(contact);
        submitCreation();
        goToHomePage();
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        addNewContact();
        fillContactForm(contact);
        attachPhoto(contact);
        selectGroup(group);
        submitCreation();
        goToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id = '%s']", contact.id())));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.first_name());
        type(By.name("middlename"), contact.middle_name());
        type(By.name("lastname"), contact.last_name());
    }

    public void attachPhoto(ContactData contact) {
        attach(By.name("photo"), contact.photo());
    }

    public boolean isContactPresent() {
        goToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        goToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteContact(ContactData contact) {
        goToHomePage();
        selectContact(contact);
        deleteSelectedContacts();
        goToHomePage();
    }

    private void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void deleteMultipleContacts() {
        goToHomePage();
        selectAllItems();
        deleteSelectedContacts();
        goToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        goToHomePage();
        selectGroupToSeeContent(group);
        selectContact(contact);
        removeContact();
        goToHomePage();
    }

    private void removeContact() {
        click(By.name("remove"));
    }

    private void selectGroupToSeeContent(GroupData group) {
        new Select(manager.driver.findElement(By.cssSelector("select[name='group']"))).selectByValue(group.id());
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
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }
}




