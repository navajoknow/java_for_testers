package ru.must.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        // класс помощник использует родительский -HelperBase- конструктор как свой собственный
        super(manager);
    }

    void login(String login, String password) {
        type(By.name("user"), login);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
