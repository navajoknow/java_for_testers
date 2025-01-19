package ru.must.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.nio.file.Paths;

public class HelperBase {
    protected final ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String text) {
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        //абсолютный путь к файлу для загрузки
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    protected void submitCreation() {
        click(By.name("submit"));
    }

    protected void goToHomePage() {
        click(By.linkText("home"));
    }

    protected void selectAllItems() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
//      можно написать в стиле функционального программирования
//      manager.driver
//                .findElements(By.name("selected[]"))
//                .forEach(WebElement::click);
        }
    }
}
