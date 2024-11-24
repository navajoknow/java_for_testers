package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        // создаем только один экземпляр для управления
        if (app == null) {
            app = new ApplicationManager();
        }
        // для удобства используем системное свойство, которое установили в настройках конфигурации среды разработки
        app.init(System.getProperty("browser", "chrome"));
    }

}
