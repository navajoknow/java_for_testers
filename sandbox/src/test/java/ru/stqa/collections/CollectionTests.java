package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTests() {
        var array = new String[]{"a", "b", "c"};
        Assertions.assertEquals("a", array[0]);
        Assertions.assertEquals(3, array.length);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests() {
        var list = new ArrayList<String>();
        Assertions.assertEquals(0, list.size());

        list.add("a");
        list.add("b");
        list.add("c");

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("c", list.get(2));

        list.set(2, "d");
        Assertions.assertEquals("d", list.get(2));

        // список, создаваемый таким образом, является немодифицируемым
        var list2 = List.of("a", "b", "c");
        Assertions.assertEquals("c", list2.get(2));
        // проверка выдаст ошибку:
        // list2.set(2, "d");
        // Assertions.assertEquals("d", list2.get(2));

        // поэтому удобнее создать немодифицируемый спиок, а потом из него сконструировать модифицируемый,
        // при этом указание типа элементов <String> уже не требуется
        var list3 = new ArrayList<>(List.of("a", "b", "c"));
        Assertions.assertEquals("c", list3.get(2));
    }

}
