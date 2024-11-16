package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        // создаем только один экземпляр для управления
        if (app == null) {
            app = new ApplicationManager();
        }
        //для удобства используем системное свойство, которое установили в настройках конфигурации среды разработки
        app.init(System.getProperty("browser", "chrome"));
    }

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            // символ 'a' (одинарные ковычки) интерпретируется как число 97 (номер символа в Unicode), а после
            // суммирования получившееся число преобразуется обратно в символ через оператор приведения типов - (char)
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

}
