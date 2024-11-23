package common;

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
}
