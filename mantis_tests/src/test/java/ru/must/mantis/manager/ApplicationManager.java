package ru.must.mantis.manager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String string;
    private Properties properties;

    //ссылки на помощников
    private SessionHelper session;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;

    }

    public WebDriver driver() {
        if (driver == null) {
                if ("chrome".equals(string)) {
                    driver = new ChromeDriver();
                } else if ("firefox".equals(string)) {
                    driver = new FirefoxDriver();
                } else {
                    throw new IllegalArgumentException(String.format("Unknown browser: %s", string));
                }
                Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
                driver.get(properties.getProperty("web.baseUrl"));
                driver.manage().window().setSize(new Dimension(1037, 817));
            }
        return driver;
    }

    public SessionHelper session() {
        if (session == null) {
            session = new SessionHelper(this);
        }
        return session;
    }

}


