package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

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

    // метод находит случайный файл в указанной директории и возвращает его полный путь в виде строки
    public static String randomFile(String dir) {
        // объект для взаимодействия с файловой системой; метод list возвращает массив имен файлов
        // и подкаталогов в указанной папке
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        // объект Path, представляющий путь к выбранному файлу, преобразуется в строку
        return Paths.get(dir, fileNames[index]).toString();
    }

}
