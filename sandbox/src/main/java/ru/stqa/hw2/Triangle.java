package ru.stqa.hw2;

import static java.lang.Math.sqrt;


public class Triangle {

    // var нельзя использовать для свойств объекта, необходимо указывать тип
    private double a;
    private double b;
    private double c;

    // конструктор для инициализиации объектов класса
    public Triangle(double a, double b, double c) {
        //"this._" - указание на свойство объекта, "= _" - указание на присваиваемое значение для такого свойства
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // методы, обращающиеся к свойствам объектов
    // NB: отсутствует ключевое слово static, т.к. методы должны вызываться в объекте; данные берутся из объекта, а не из пераметров метода

    public static void printTrianglePerimeter(Triangle t) {
        var text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.calculatePerimeter());
        System.out.println(text);
    }
    public double calculatePerimeter() {
        return this.a + this.b + this.c;
    }

    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.calculateArea());
        System.out.println(text);
    }
    public double calculateArea() {
        var sp = calculatePerimeter() / 2;
        return sqrt(sp * (sp - this.a) * (sp - this.b) * (sp - this.c));
    }


    // первоначально созданные методы без обращения к свойствам объектов (данные принимаются из параметров методов)

    public static void previousPrintTrianglePerimeter(double a, double b, double c) {
        var text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", a, b, c, previousCalculatePerimeter(a, b, c));
        System.out.println(text);
    }
    public static double previousCalculatePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static void previousPrintTriangleArea(double a, double b, double c) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", a, b, c, previousCalculateArea(a, b, c));
        System.out.println(text);
    }
    public static double previousCalculateArea(double a, double b, double c) {
        var sp = previousCalculatePerimeter(a, b, c) / 2;
        return sqrt(sp * (sp - a) * (sp - b) * (sp - c));
    }

}

