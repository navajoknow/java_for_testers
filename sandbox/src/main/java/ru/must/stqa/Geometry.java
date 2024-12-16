package ru.must.stqa;

import ru.must.stqa.hw2.Triangle;
import ru.must.stqa.hw1.Square;


public class Geometry {
    public static void main(String[] args) {

        // hw1
        Square.printSquareArea(3);

        // hw2

           // вызов методов, обращающихся к свойствам создаваемых объектов
           Triangle.printTriangleArea(new Triangle(5.0,6.0,7.0));
           Triangle.printTrianglePerimeter(new Triangle(5.0,6.0,7.0));

           // вызов методов с передачей параметров
           Triangle.previousPrintTriangleArea(5.0,6.0,7.0);
           Triangle.previousPrintTrianglePerimeter(5.0,6.0,7.0);
    }
}
