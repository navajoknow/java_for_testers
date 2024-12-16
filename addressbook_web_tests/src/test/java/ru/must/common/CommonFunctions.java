package ru.must.common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {

//   public static String randomString(int n) {
//        var rnd = new Random();
//        var result = "";
//        for (int i = 0; i < n; i++) {
//            // 'a' (символ в одинарных ковычках) интерпретируется как число 97 (номер символа в Unicode), а после
//            // суммирования получившееся число преобразуется обратно в символ через оператор приведения типов - (char)
//            result = result + (char)('a' + rnd.nextInt(26));
//        }
//        return result;
//    }

    // реализация этого же метода, но в стиле функционального программирования
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        return Stream.generate(randomNumbers)
                .limit(n)
                // функция-трансформатор
                .map(i -> 'a' + i)
                .map(i -> Character.toString(i))
                // собираем строки из 1 символа в одну строку
                .collect(Collectors.joining());
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
