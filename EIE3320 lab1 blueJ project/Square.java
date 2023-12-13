//19069748d Kwok Kevin
import java.util.Scanner;
import java.awt.geom.*;

public class Square extends Shape implements Drawable {
    private float length; 
    public Square() {
        readShape();
        computeArea();
        computePerimeter();
    };
    public Square(float l) {
        length = l;
    };
    public void readShape() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input the length:");
            length = scanner.nextFloat();
    }
    public void computeArea() {
        area = length * length;
    };
    public void computePerimeter() {
        perimeter = 4 * length;
    };

    public void displayShape() {
        System.out.println("Area of square = " + area);
        System.out.println("Perimeter of square = " + perimeter);
    };
    public void draw() {
        Canvas canvas = Canvas.getCanvas();
        canvas.draw(this, "blue", new Rectangle2D.Double(180, 20, length, length));
    }
}