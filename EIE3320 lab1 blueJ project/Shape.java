//19069748d Kwok Kevin
abstract class Shape implements Drawable {
    protected float area; 
    protected float perimeter;
    
    // To read the shape information from users
    abstract public void readShape();
    
    // To compute the shape’s area
    abstract public void computeArea();
    
    // To computer the shape’s perimeter
    abstract public void computePerimeter();
    
    // To display the area and perimeter of the shape
    abstract public void displayShape(); 
    
}