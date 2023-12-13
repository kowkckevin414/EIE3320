

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class GUI extends JFrame {
    JTextField jshape = new JTextField();
    JTextField jradius = new JTextField();
    JTextField jlength = new JTextField();
    JTextField jwidth = new JTextField(); 
    JTextField jarea = new JTextField();
    JTextField jperimeter = new JTextField(); 
    JButton jcalculate = new JButton("Get Results"); 
        
    public GUI() {
        JPanel s1 = new JPanel(); 
        s1.setLayout(new BorderLayout(150, 10)); 
        s1.setBorder(new LineBorder(Color.BLUE, 5));
        
        JPanel s2 = new JPanel(new GridLayout(7, 2, 5, 5)); //Editable: Variable Name, 後面個number 
        s2.add(new JLabel("Choose shape: 's', 'r' or 'c'"));
        s2.add(jshape);
        s2.add(new JLabel("Input radius of circle"));
        s2.add(jradius);
        s2.add(new JLabel("Input length of square or rectangle"));
        s2.add(jlength);
        s2.add(new JLabel("Input width of rectangle"));
        s2.add(jwidth);
        s2.add(new JLabel("Area:"));
        s2.add(jarea);
        s2.add(new JLabel("Perimeter:"));
        s2.add(jperimeter);
        s2.add(jcalculate);
        
        s1.add(s2, BorderLayout.CENTER);
        s1.add(new JLabel(""), BorderLayout.NORTH); 
        s1.add(new JLabel(""), BorderLayout.EAST);
        s1.add(new JLabel(""), BorderLayout.SOUTH);
        s1.add(new JLabel(""), BorderLayout.WEST);
        this.getContentPane().add(s1); 
        
        
        jcalculate.addActionListener(new Listener());
    }
    
    public static void main(String args[]) {
        GUI frame = new GUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,300);
        frame.setVisible(true);
    }
    
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jcalculate){
                char choice = jshape.getText().charAt(0);
                switch(choice) {
                    case 'c': 
                        Shape circle = new Circle(Float.parseFloat(jradius.getText()));
                        circle.computeArea(); 
                        circle.computePerimeter();
                        circle.draw();
                        jarea.setText(String.valueOf(circle.area));
                        jperimeter.setText(String.valueOf(circle.perimeter));
                        break;
                    case 's': 
                        Shape square = new Square(Float.parseFloat(jlength.getText()));
                        square.computeArea();
                        square.computePerimeter();
                        square.draw();
                        jarea.setText(String.valueOf(square.area));
                        jperimeter.setText(String.valueOf(square.perimeter)); 
                        break; 
                    case 'r': 
                        Shape rectangle = new Rectangle(Float.parseFloat(jlength.getText()), 
                                                        Float.parseFloat(jwidth.getText()));
                        rectangle.computeArea();
                        rectangle.computePerimeter();
                        rectangle.draw();
                        jarea.setText(String.valueOf(rectangle.area)); 
                        jperimeter.setText(String.valueOf(rectangle.perimeter));
                        break;
                    default:
                }
            }
        }
    }
}
