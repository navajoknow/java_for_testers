package manager;

import org.openqa.selenium.By;

public class LoginHelper {

    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    void login(String login, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(login);
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
