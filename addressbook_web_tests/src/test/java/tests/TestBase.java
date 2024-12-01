package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            // создаем только один экземпляр ApplicationManager для управления
            app = new ApplicationManager();
            // используем системное свойство, заданное или по умолчанию
            app.init(System.getProperty("browser", "chrome"), properties);
        }
    }

    // проверка из лекции 6.5 "С чем не может справиться ORM: испорченные связи"
    @AfterEach
    void testDatabaseConsistency() {
        app.jdbc().checkConsistency();
    }
}
