package ru.must.stqa.hw2;

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

        // hw3 - обработка исключения, когда одна из сторон < 0
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Сторона треугольника не должна быть отрицательной");
        }
        // hw3 - обработка исключения, когда нарушается неравенство треугольника
        if (a + b < c || a + c < b || c + b < a) {
            throw new IllegalArgumentException("Сумма двух любых сторон треугольника должна быть не меньше третьей стороны");
        }
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

    // hw-4
    @Override
    // метод вызывается в объекте, для его сравнения с другим объектом, переданным в качестве параметра метода
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)
                // далее самодельная доработка (переопределение) метода equals согласно задаче
                || (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.c) == 0)
                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.a) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0)
                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.a) == 0);
    }

    // hw-4
    @Override
    public int hashCode() {
        // return Objects.hash(a, b, c);
        // упростил по примеру из лекции (т.е. проверка с помощью метода hashCode по существу не осуществляется, т.к. всегда возращается единица):
        return 1;
    }
}

