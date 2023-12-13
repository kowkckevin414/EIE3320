//19069748d Kwok Kevin
import java.util.ArrayList;
import java.util.Iterator;

public class Picture {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    public void addShape(Shape s) {
        shapes.add(s);
    }
    public void computeShape() {
        for(Iterator iter = shapes.iterator(); iter.hasNext();) {
            Shape shape = (Shape)iter.next();
            shape.computeArea();
            shape.computePerimeter();
        }
    }
    public void listAllShapeTypes() {
        for(Iterator iter = shapes.iterator(); iter.hasNext();) {
            Shape shape = (Shape)iter.next();
            shape.displayShape();
        }
    }
    public void listSingleShapeType(String Class) {
        for(Iterator iter = shapes.iterator(); iter.hasNext();) {
            Shape shape = (Shape)iter.next();
            if (shape.getClass().getName().equals(Class)) {
                shape.displayShape();
            }
        }
    }
}