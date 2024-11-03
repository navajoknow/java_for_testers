package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// класс, отвечающий за управление приложением
public class ApplicationManager {

    protected WebDriver driver;
    // далее ссылки на помощников
    // LoginHelper - как для логина, так и для логаута
    private LoginHelper session;
    private GroupHelper group;
    private ContactHelper contact;

    public void init(String browser) {
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                // примеры плейсхолдеров: %s для строки, %d для целого числа, %f для чисел с плавающей точкой
                throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1037, 817));
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {
        if (session == null) {
            //образно говоря, помощник при собственном конструировании получает информацию о том,
            // кто является его менеджером
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (group == null) {
            group = new GroupHelper(this);
        }
        return group;
    }

    public ContactHelper contacts() {
        if (contact == null) {
            contact = new ContactHelper(this);
        }
        return contact;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
