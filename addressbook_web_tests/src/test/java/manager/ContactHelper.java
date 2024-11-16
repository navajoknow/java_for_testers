package manager;

import models.ContactData;
import org.openqa.selenium.By;

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

    public void deleteContact() {
        goToHomePage();
        selectContact();
        deleteSelectedContacts();
        goToHomePage();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.First_name());
        type(By.name("middlename"), contact.Middle_name());
        type(By.name("lastname"), contact.Last_name());
        type(By.name("nickname"), contact.Nickname());
        type(By.name("title"), contact.Title());
        type(By.name("company"), contact.Company());
        type(By.name("address"), contact.Address());
        type(By.name("home"), contact.Home_phone());
        type(By.name("mobile"), contact.Mobile());
        type(By.name("work"), contact.Work_phone());
        type(By.name("fax"), contact.Fax());
        type(By.name("email"), contact.Email());
        type(By.name("email2"), contact.Email2());
        type(By.name("email3"), contact.Email3());
        type(By.name("homepage"), contact.Homepage());
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

}



