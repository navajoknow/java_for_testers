package ru.must.stqa.hw2;

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

    // hw-3 - проверка, что длина стороны треуголньика не является отрицательной
    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1, 1, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(1, -1, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(1, 1, -1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // hw-3 - проверка нарушения неравенства треугольников
    @Test
    void cannotCreateTriangleWithInequalityViolation() {
        try {
            new Triangle(3, 1, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(1, 3, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(1, 1, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    // hw-4 - проверка равенства треугольников независимо от порядка длин сторон
    @Test
    void testEquality() {
        var t1 = new Triangle(1,2,3);
        var t2 = new Triangle(3,1,2);
        Assertions.assertTrue(t1.equals(t2));
    }
}
