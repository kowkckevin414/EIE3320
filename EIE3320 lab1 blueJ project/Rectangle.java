//19069748d Kwok Kevin
import java.util.Scanner;
import java.awt.geom.*;

public class Rectangle extends Shape implements Drawable{
    private float length;
    private float width;
    public Rectangle() {
        readShape();
        computeArea();
        computePerimeter();
    };
    public Rectangle(float l, float w) {
        length = l;
        width = w;
    };
    public void readShape() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the length:");
        length = scanner.nextFloat();
        System.out.println("Please input the width:");
        width = scanner.nextFloat();
    }
    public void computeArea() {
        area = length * width;
    };
    public void computePerimeter() {
        perimeter = 2 * (length + width);
    };
    public void displayShape() {
        System.out.println("Area of rectangle = " + area);
        System.out.println("Perimeter of rectangle = " + perimeter);
    };
    public void draw() {
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, "blue", new Rectangle2D.Double(180, 180, length,width));
    }
}