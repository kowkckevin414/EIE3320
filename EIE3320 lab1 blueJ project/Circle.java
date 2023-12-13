//19069748d Kwok Kevin
import java.awt.geom.*;
import java.util.Scanner;

public class Circle extends Shape implements Drawable{
    private float radius;
    public Circle() {
        readShape();
        computeArea();
        computePerimeter();
    };
    public Circle(float r) {
        radius = r;
    };
    public void readShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the radius:");
        radius = scanner.nextFloat();
    }
    public void computeArea() {
        area = (float)Math.PI * radius * radius;
    };
    public void computePerimeter() {
        perimeter = 2 * (float)Math.PI * radius;
    };
    public void displayShape() {
        System.out.println("Area of circle = " + area);
        System.out.println("Perimeter of circle = " + perimeter);
    };
    public void draw() {
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, "blue", new Ellipse2D.Double(10, 120, 2 * radius, 2 * radius));
    }
}