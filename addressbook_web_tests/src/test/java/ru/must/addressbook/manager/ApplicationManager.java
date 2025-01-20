package ru.must.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

// класс, отвечающий за управление приложением
public class ApplicationManager {

    protected WebDriver driver;
    // далее ссылки на помощников
    // LoginHelper - как для логина, так и для логаута
    private LoginHelper session;
    private GroupHelper group;
    private ContactHelper contact;
    private JdbcHelper jdbc;
    private HibernateHelper hbm;

    private Properties properties;

    public void init(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        if (driver == null) {
            var seleniumServer = properties.getProperty("seleniumServer");
            if ("firefox".equals(browser)) {
                if (seleniumServer != null) {
                    driver = new RemoteWebDriver(new URL(seleniumServer), new FirefoxOptions());
                } else {
                    driver = new FirefoxDriver();
                }
            } else if ("chrome".equals(browser)) {
                    if (seleniumServer != null) {
                        driver = new RemoteWebDriver(new URL(seleniumServer), new ChromeOptions());
                    } else {
                        driver = new ChromeDriver();
                    }
            } else {
                // примеры плейсхолдеров: %s для строки, %d для целого числа, %f для чисел с плавающей точкой
                throw new IllegalArgumentException(String.format("Unknown browser: %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1037, 817));
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    public LoginHelper session() {
        if (session == null) {
            // образно говоря, помощник при собственном конструировании получает информацию о том,
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

    public JdbcHelper jdbc() {
        if (jdbc == null) {
            jdbc = new JdbcHelper(this);
        }
        return jdbc;
    }

    public HibernateHelper hbm() {
        if (hbm == null) {
            hbm = new HibernateHelper(this);
        }
        return hbm;
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
