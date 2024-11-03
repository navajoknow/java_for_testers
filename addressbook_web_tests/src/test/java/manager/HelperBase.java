package manager;

import org.openqa.selenium.By;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String text) {
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void submitCreation() {
        click(By.name("submit"));
    }

    protected void selectItem() {
        click(By.name("selected[]"));
    }

    protected void goToHomePage() {
        click(By.linkText("home"));
    }
}
