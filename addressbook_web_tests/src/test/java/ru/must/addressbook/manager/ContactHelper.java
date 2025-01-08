package ru.must.addressbook.manager;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        // передача параметра конструктору родительского класса
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

    public void putContactToGroup(ContactData contact, GroupData group) {
        goToHomePage();
        selectContact(contact);
        chooseGroupToAddContact(group);
        addContactToGroup();
        goToHomePage();
    }

    private void chooseGroupToAddContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void addContactToGroup() {
        click(By.cssSelector("input[type='submit'][value='Add to']"));
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[id='%s'][name='selected[]']", contact.id())));
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

    public Map<String, String> getAllContactsPhones() {
        goToHomePage();
        var result = new HashMap<String, String>();
        var rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getPhonesOnMainPage(ContactData contact) {
        goToHomePage();
        var result = new HashMap<String, String>();
        var id = contact.id();
        var text = manager.driver.findElement(By.xpath(
                // в xpath /../ это подъем на один уровень вверх
                // в данном случае поднимаемся на 2 уровня вверх и находим в ряду ячейку по заданному id
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
        var phones = Stream.of(text)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, phones);
        return result;
    }

    public Map<String, String> getPhonesOnEditPage(ContactData contact) {
        goToHomePage();
        initContactModification(contact);
        var result = new HashMap<String, String>();
        var id = contact.id();
        var home = manager.driver.findElement(By.name("home")).getAttribute("value");
        var mobile = manager.driver.findElement(By.name("mobile")).getAttribute("value");
        var work = manager.driver.findElement(By.name("work")).getAttribute("value");
        var phones = Stream.of(home, mobile, work)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, phones);
        return result;
    }

    public Map<String, String> getEmailsOnMainPage(ContactData contact) {
        goToHomePage();
        var result = new HashMap<String, String>();
        var id = contact.id();
        String emails = manager.driver.findElements(
                By.xpath(String.format("//input[@id='%s']/../../td[5]//a[contains(@href, 'mailto:')]", contact.id())))
                .stream()
                .map(WebElement::getText)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, emails);
        return result;
    }

    public Map<String, String> getEmailsOnEditPage(ContactData contact) {
        goToHomePage();
        initContactModification(contact);
        var result = new HashMap<String, String>();
        var id = contact.id();
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        var emails = Stream.of(email, email2, email3)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, emails);
        return result;
    }

    public Map<String, String> getAddressOnMainPage(ContactData contact) {
        goToHomePage();
        var result = new HashMap<String, String>();
        var id = contact.id();
        var text = manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
        var address = Stream.of(text)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, address);
        return result;
    }

    public Map<String, String> getAddressOnEditPage(ContactData contact) {
        goToHomePage();
        initContactModification(contact);
        var result = new HashMap<String, String>();
        var id = contact.id();
        var text = manager.driver.findElement(By.name("address")).getText();
        var address = Stream.of(text)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("\n"));
        result.put(id, address);
        return result;
    }

}




