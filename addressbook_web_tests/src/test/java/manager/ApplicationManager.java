package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// класс, отвечающий за управление приложением
public class ApplicationManager {

    protected WebDriver driver;
    // далее ссылки на помощников
    // LoginHelper - как для логина, так и для логаута
    private LoginHelper session;
    private GroupHelper groups;


    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
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
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
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
