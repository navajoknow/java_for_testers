package ru.stqa.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    // проверки для методов, обращающихся к свойствам объектов

    @Test
    void canCalculateTrianglePerimeter() {
        var t = new Triangle(5, 6, 7);
        // вызываем функцию в объекте
        var result = t.calculatePerimeter();
        Assertions.assertEquals(18.0, result);
    }

    @Test
    void canCalculateTriangleArea() {
        // более лаконичная запись
        Assertions.assertEquals(14.696938456699069, new Triangle(5, 6, 7).calculateArea());
    }
}