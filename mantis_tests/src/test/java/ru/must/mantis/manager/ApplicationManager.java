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
    private SignupHelper signupHelper;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;

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

    public SignupHelper signupHelper() {
        if (signupHelper == null) {
            signupHelper = new SignupHelper(this);
        }
        return signupHelper;
    }

    public SessionHelper sessionHelper() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper httpSessionHelper() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }
    public String property(String name) {
        return properties.getProperty(name);
    }

    public JamesCliHelper jamesCliHelper() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mailHelper() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

}


