//19069748d Kwok Kevin
import java.util.Scanner;

public class ShapeTester {
    public static void main(String args[]) {
        boolean loop = true;
        char option;
        while(loop == true) {
            System.out.println();
            System.out.println("**************************************");
            System.out.println("* Please choose one the followings:  *");
            System.out.println("* Press 'c' - Circle                 *");
            System.out.println("* Press 's' - Square                 *");
            System.out.println("* Press 'r' - Rectangle              *");
            System.out.println("* Press 'x' - EXIT                   *");
            System.out.println("**************************************");
            System.out.println();
            Scanner scanner = new Scanner(System.in); 
            option = scanner.next(".").charAt(0);
            switch(option) {
                case 'c': 
                    Shape circle = new Circle();
                    circle.displayShape();
                    circle.draw();
                    break;
                case 's': 
                    Shape square = new Square();
                    square.displayShape();
                    square.draw();
                    break; 
                case 'r': 
                    Shape rectangle = new Rectangle();
                    rectangle.displayShape();
                    rectangle.draw();
                    break;
                case 'x': 
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        } 
    }
}