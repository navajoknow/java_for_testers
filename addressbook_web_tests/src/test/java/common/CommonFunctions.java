package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {

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

    // метод находит случайный файл в указанной директории и возвращает его полный путь в виде строки
    public static String randomFile(String dir) {
        // объект для взаимодействия с файловой системой; метод list возвращает
        // массив имен файлов и подкаталогов в указанной папке
        var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        // объект Path, представляющий путь к выбранному файлу, преобразуется в строку
        return Paths.get(dir, fileNames[index]).toString();
    }
}
