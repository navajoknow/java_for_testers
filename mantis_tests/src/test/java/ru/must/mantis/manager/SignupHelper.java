package ru.must.mantis.manager;

import org.openqa.selenium.By;

public class SignupHelper extends HelperBase {

    public SignupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void signup(String username, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//input[@value='Signup']"));
    }

    public void accountActivation(String url) {
        manager.driver().get(url);
    }

    public void editAccount(String realname, String password) {
        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//span[contains(text(), 'Update User')]"));
    }

}