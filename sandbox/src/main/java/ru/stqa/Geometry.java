package ru.stqa;

import ru.stqa.hw2.Triangle;

import static ru.stqa.hw1.Square.printSquareArea;
import static ru.stqa.hw2.Triangle.*;


public class Geometry {
    public static void main(String[] args) {

        // hw1
        printSquareArea(3);

        // hw2

           // вызов методов, обращающихся к свойствам создаваемых объектов
           printTriangleArea(new Triangle(5.0,6.0,7.0));
           printTrianglePerimeter(new Triangle(5.0,6.0,7.0));

           // вызов методов с передачей параметров
           previousPrintTriangleArea(5.0,6.0,7.0);
           previousPrintTrianglePerimeter(5.0,6.0,7.0);
    }
}
