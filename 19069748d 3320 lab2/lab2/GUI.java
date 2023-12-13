import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument.Content;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame {
    JTextArea info = new JTextArea();
    JTextField ISBN = new JTextField(10);
    JTextField Title = new JTextField(10);
    JButton jadd = new JButton("Add");
    JButton jedit = new JButton("Edit");
    JButton jsave = new JButton("Save");
    JButton jdelete = new JButton("Delete");
    JButton jsearch = new JButton("Search");
    JButton jmore = new JButton("More>>");
    JButton jload = new JButton("Load Test Data");
    JButton jdisplay1 = new JButton("Display All");
    JButton jdisplay2 = new JButton("Display All by ISBN");
    JButton jdisplay3 = new JButton("Display All by Title");
    JButton jexit = new JButton("Exit");
    JTextArea bookinfo = new JTextArea();
    JTextArea bookstatus = new JTextArea();
    JButton jborrow = new JButton("Borrow");
    JButton jreturn = new JButton("Return");
    JButton jreserve = new JButton("Reserve");
    JButton jwaiting_queue = new JButton("Waiting Queue");
    int index = -1;
    boolean ascending1 = true;
    boolean ascending2 = true;
    MyLinkedList<Book> bookList = new MyLinkedList<Book>();    
    DefaultTableModel model = new DefaultTableModel();
    JTable jtable = new JTable(model);
    JScrollPane jscroll = new JScrollPane(jtable);
    
    //New Try
    Timer timer = new Timer();
    int counter_min = 07;
    int counter_sec = 10;
    JLabel counter = new JLabel("Period Expire: " + counter_min + ":" + counter_sec);
    TimerTask StopApp = new TimerTask() {
        @Override
        public void run() {
            System.exit(0);
        }
    };
    
    TimerTask Update = new TimerTask() {
        @Override
        public void run() {
            counter_sec -= 1;
            if (counter_sec == -1) {
                counter_sec += 60;
                counter_min -= 1;
            }
            if (counter_sec < 10) {
                counter.setText("Period Expire: " + counter_min + ":0" + counter_sec);
            }
            else {
                counter.setText("Period Expire: " + counter_min + ":" + counter_sec);
            }
            
            if (counter_sec == 0 && counter_min == 0) {
                JOptionPane.showMessageDialog(null, "Period expired, please re-open");
                System.exit(0);
            }
        }
    };
    
    public GUI() {
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        info.setEditable(false);
        info.append("Student Name and ID: Kwok Kevin // 19069748d \n");
        info.append(new Date().toString());
        info.setPreferredSize(new Dimension(80, 80));
        model.addColumn("ISBN");
        model.addColumn("Title");
        model.addColumn("Available");
        jsave.setEnabled(false);    
        this.getContentPane().add(p1);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 1, 5, 5));
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.add(new JLabel("ISBN: "));
        p3.add(ISBN);
        p3.add(new JLabel("Title: "));
        p3.add(Title);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(jadd);
        p4.add(jedit);
        p4.add(jsave);
        p4.add(jdelete);
        p4.add(jsearch);
        p4.add(jmore);
        
        JPanel p5 = new JPanel();
        p5.setLayout(new FlowLayout());
        p5.add(jload);
        p5.add(jdisplay1);
        p5.add(jdisplay2);
        p5.add(jdisplay3);
        p5.add(jexit);
        
        p2.add(p3);
        p2.add(p4);
        p2.add(p5);
        
        p1.add(info, BorderLayout.NORTH);
        p1.add(jscroll, BorderLayout.CENTER);     
        p1.add(p2, BorderLayout.SOUTH);     
        
        Listener listener = new Listener();
        jadd.addActionListener(listener);
        jedit.addActionListener(listener);
        jsave.addActionListener(listener);
        jdelete.addActionListener(listener);
        jsearch.addActionListener(listener);
        jmore.addActionListener(listener);
        jload.addActionListener(listener);
        jdisplay1.addActionListener(listener);
        jdisplay2.addActionListener(listener);
        jdisplay3.addActionListener(listener);
        jexit.addActionListener(listener);
        
        jtable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {                
                ISBN.setText((String)jtable.getValueAt(jtable.rowAtPoint(e.getPoint()), 0));
                Title.setText((String)jtable.getValueAt(jtable.rowAtPoint(e.getPoint()), 1));
            }
        });
                //New Try
        timer.scheduleAtFixedRate(Update, 1000, 1000);
        p3.add(counter);
    }
    
    public static void main(String args[]) {
        GUI frame = new GUI();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setVisible(true);
        frame.setTitle("Library Admin System");
    }
    
    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {  
            if (e.getSource() == jadd) {                
                if (ISBN.getText().isEmpty() && Title.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ISBN Cannot be Empty\nTitle Cannot be Empty");
                }
                else if (ISBN.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ISBN Cannot be Empty");
                }
                else if (Title.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title: Title Cannot be Empty");
                }
                else {
                    boolean exist = false;
                    Book book = new Book(Title.getText(), ISBN.getText());
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bookList.get(i).getISBN().equals(book.getISBN())) {
                            JOptionPane.showMessageDialog(null, "Error: book exists.");
                            exist = true;
                        }
                    }
                    if (!exist) {
                        bookList.add(book);
                    }
                    jdisplay1.doClick();
                    ISBN.setText("");
                    Title.setText("");
                }
            }
            else if (e.getSource() == jedit) {
                boolean exist = false;
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getISBN().equals(ISBN.getText())) {
  
                        exist = true;
                    }
                }
                if (exist) {
                    for(int i = 0; i < bookList.size(); i++) {
                        if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                            Title.setText(bookList.get(i).getTitle());
                            index = i;
                        }
                    }        
                    jadd.setEnabled(false);
                    jedit.setEnabled(false);
                    jdelete.setEnabled(false);
                    jsearch.setEnabled(false);
                    jmore.setEnabled(false);
                    jload.setEnabled(false);
                    jdisplay1.setEnabled(false);
                    jdisplay2.setEnabled(false);
                    jdisplay3.setEnabled(false);
                    jexit.setEnabled(false);
                    jsave.setEnabled(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: book not exists.");
                }        
            }

            
            else if (e.getSource() == jsave) {
                
                if (ISBN.getText().isEmpty() && Title.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ISBN Cannot be Empty\nTitle Cannot be Empty");
                }
                else if (ISBN.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ISBN Cannot be Empty");
                }
                else if (Title.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title: Title Cannot be Empty");
                }
        else if (ISBN.getText() != bookList.get(index).getISBN()) {
                    boolean exist = false;
                    Book book = new Book(Title.getText(), ISBN.getText());
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bookList.get(i).getISBN().equals(book.getISBN())) {
                            JOptionPane.showMessageDialog(null, "Error: book exists.");
                            exist = true;
                        }
                    }
                    if (exist == false) {
                        bookList.get(index).setTitle(Title.getText());
                        bookList.get(index).setISBN(ISBN.getText());
                        jsave.setEnabled(false);
                        jadd.setEnabled(true);
                        jedit.setEnabled(true);
                        jdelete.setEnabled(true);
                        jsearch.setEnabled(true);
                        jmore.setEnabled(true);
                        jload.setEnabled(true);
                        jdisplay1.setEnabled(true);
                        jdisplay2.setEnabled(true);
                        jdisplay3.setEnabled(true);
                        jexit.setEnabled(true);       
                        jdisplay1.doClick();
                        index = -1;
                        ISBN.setText("");
                        Title.setText("");
                    }
                }
                else {
                    bookList.get(index).setTitle(Title.getText());
                    bookList.get(index).setISBN(ISBN.getText());
                        jsave.setEnabled(false);
                        jadd.setEnabled(true);
                        jedit.setEnabled(true);
                        jdelete.setEnabled(true);
                        jsearch.setEnabled(true);
                        jmore.setEnabled(true);
                        jload.setEnabled(true);
                        jdisplay1.setEnabled(true);
                        jdisplay2.setEnabled(true);
                        jdisplay3.setEnabled(true);
                        jexit.setEnabled(true);       
                        jdisplay1.doClick();
                    index = -1;
                    ISBN.setText("");
                    Title.setText("");
                }
            }
            
            else if (e.getSource() == jdelete) {
                for(int i = 0; i < bookList.size(); i++) {
                    if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                        model.removeRow(i);
                        bookList.remove(i);
                    }
                }
                jdisplay1.doClick();
            }
            else if (e.getSource() == jsearch) {                
                model.setRowCount(0);
                for (int i = 0; i < bookList.size(); i++) {
                    if (Title.getText().isEmpty() && !ISBN.getText().isEmpty()) {
                        if (bookList.get(i).getISBN().contains(ISBN.getText())) { 
                            model.addRow(new Object[] {bookList.get(i).getISBN(), bookList.get(i).getTitle(), bookList.get(i).isAvailable()});
                        }
                    }
                    else if (!Title.getText().isEmpty() && ISBN.getText().isEmpty()) {
                        if (bookList.get(i).getTitle().contains(Title.getText()) && ISBN.getText().isEmpty()) { 
                            model.addRow(new Object[] {bookList.get(i).getISBN(), bookList.get(i).getTitle(), bookList.get(i).isAvailable()});
                        }
                    }
                    else if (!Title.getText().isEmpty() && !ISBN.getText().isEmpty()) {
                        if (bookList.get(i).getISBN().contains(ISBN.getText())) { //Search ISBN
                            model.addRow(new Object[] {bookList.get(i).getISBN(), bookList.get(i).getTitle(), bookList.get(i).isAvailable()});
                        }
                        else if (bookList.get(i).getTitle().contains(Title.getText())) { //Search Title if ISBN not containing
                            model.addRow(new Object[] {bookList.get(i).getISBN(), bookList.get(i).getTitle(), bookList.get(i).isAvailable()});
                        }
                    }
                }
                ISBN.setText("");
                Title.setText("");
            }
            else if (e.getSource() == jmore) {
                if (ISBN.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ISBN Cannot be Empty");
                }
                else {
                    Listener listener = new Listener();
                    jborrow.addActionListener(listener);
                    jreturn.addActionListener(listener);
                    jreserve.addActionListener(listener);
                    jwaiting_queue.addActionListener(listener);
                
                    JPanel p6 = new JPanel();
                    p6.setLayout(new BorderLayout());
                    p6.add(bookinfo, BorderLayout.NORTH);
                    p6.add(bookstatus, BorderLayout.SOUTH);
                    
                    JPanel p7 = new JPanel();
                    p7.setLayout(new FlowLayout());
                    p7.add(jborrow);
                    p7.add(jreturn);
                    p7.add(jreserve);
                    p7.add(jwaiting_queue);
                     
                    p6.add(p7, BorderLayout.CENTER);
                    
                    JFrame frame = new JFrame();
                    JDialog dialog = new JDialog(frame, "new");
                    dialog.setSize(550, 500);
                    dialog.getContentPane().add(p6);
                    dialog.setVisible(true);   
                    dialog.setTitle(Title.getText());
                    bookinfo.setEditable(false);
                    int index2 = -1;
                    for(int i = 0; i < bookList.size(); i++) {
                        if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                            Title.setText(bookList.get(i).getTitle());
                            index2 = i;
                        }
                    }
                    bookinfo.setText("");
                    bookinfo.append("ISBN: " + bookList.get(index2).getISBN() + "\n");
                    bookinfo.append("Title: " + bookList.get(index2).getTitle() + "\n");
                    bookinfo.append("Available: " + bookList.get(index2).isAvailable() + "\n");    
                    
                    if (bookList.get(index2).isAvailable()) {
                        jborrow.setEnabled(true);
                        jreturn.setEnabled(false);
                        jreserve.setEnabled(false);
                        jwaiting_queue.setEnabled(false);
                    }
                    else {
                        jborrow.setEnabled(false);
                        jreturn.setEnabled(true);
                        jreserve.setEnabled(true);
                        jwaiting_queue.setEnabled(true);
                    }
                }
                           
            }
            else if (e.getSource() == jload) {
                boolean exist = false;
                Book book = new Book("HTML How to Program", "0131450913");
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getISBN().equals(book.getISBN())) {
                        JOptionPane.showMessageDialog(null, "Error: book exists in the current database.");
                        exist = true;
                    }
                }
                if (!exist) {
                    bookList.add(book);
                }
                
                exist = false;
                book = new Book("C++ How to Program", "0131857576");
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getISBN().equals(book.getISBN())) {
                        JOptionPane.showMessageDialog(null, "Error: book exists.");
                        exist = true;
                    }
                }
                if (!exist) {
                    bookList.add(book);
                }
                
                exist = false;
                book = new Book("Java How to Program", "0132222205");
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getISBN().equals(book.getISBN())) {
                        JOptionPane.showMessageDialog(null, "Error: book exists.");
                        exist = true;
                    }
                }
                if (!exist) {
                    bookList.add(book);
                }
                
                jdisplay1.doClick();
            }
            else if (e.getSource() == jdisplay1) {
                model.setRowCount(0);                
                for (int i = 0; i < bookList.size(); i++) {
                    model.addRow(new Object[] {bookList.get(i).getISBN(), bookList.get(i).getTitle(), bookList.get(i).isAvailable()});
                }
            }
            else if (e.getSource() == jdisplay2) {
                String[][] book_array = new String[bookList.size()][3];
                for (int i = 0; i < bookList.size(); i++) {
                    book_array[i][0] = bookList.get(i).getISBN();
                    book_array[i][1] = bookList.get(i).getTitle();
                    book_array[i][2] = String.valueOf(bookList.get(i).isAvailable());
                }               
                if(ascending1) {
                    Arrays.sort(book_array, (a, b) -> a[0].compareTo(b[0]));
                }
                else {
                    Arrays.sort(book_array, (b, a) -> a[0].compareTo(b[0]));
                }
                ascending1 = !ascending1;
                
                model.setRowCount(0);
                for (int i = 0; i < bookList.size(); i++) {
                    model.addRow(new Object[] {book_array[i][0], book_array[i][1], book_array[i][2]});
                }
            }
            else if (e.getSource() == jdisplay3) {
                String[][] book_array = new String[bookList.size()][3];
                for (int i = 0; i < bookList.size(); i++) {
                    book_array[i][0] = bookList.get(i).getISBN();
                    book_array[i][1] = bookList.get(i).getTitle();
                    book_array[i][2] = String.valueOf(bookList.get(i).isAvailable());
                }               
                if(ascending2) {
                    Arrays.sort(book_array, (a, b) -> a[1].compareTo(b[1]));
                }
                else {
                    Arrays.sort(book_array, (b, a) -> a[1].compareTo(b[1]));
                }
                ascending2 = !ascending2;
           
                model.setRowCount(0);
                for (int i = 0; i < bookList.size(); i++) {
                    model.addRow(new Object[] {book_array[i][0], book_array[i][1], book_array[i][2]});
                }
            }
            else if (e.getSource() == jexit) {
                System.exit(0);
                System.out.println("exit");
            }
            else if (e.getSource() == jborrow) {
                int index2 = -1;
                for(int i = 0; i < bookList.size(); i++) {
                    if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                        Title.setText(bookList.get(i).getTitle());
                        index2 = i;
                    }
                }           
                bookList.get(index2).setAvailable(false);       
                bookinfo.setText("");
                bookinfo.append("ISBN: " + bookList.get(index2).getISBN() + "\n");
                bookinfo.append("Title: " + bookList.get(index2).getTitle() + "\n");
                bookinfo.append("Available: " + bookList.get(index2).isAvailable() + "\n");             
                bookstatus.append("The book is borrowed.");         
                jborrow.setEnabled(false);
                jreturn.setEnabled(true);
                jreserve.setEnabled(true);
                jwaiting_queue.setEnabled(true);
            }
            else if (e.getSource() == jreturn) {
                System.out.println("button13");
                bookstatus.setText("");
                bookstatus.append("The book is returned.\n");
                int index2 = -1;
                for(int i = 0; i < bookList.size(); i++) {
                    if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                        Title.setText(bookList.get(i).getTitle());
                        index2 = i;
                    }
                }
                
                if (bookList.get(index2).getReservedQueue().getSize() != 0) {                             
                    bookstatus.append("The book is now borrowed by " + bookList.get(index2).getReservedQueue().getList().getFirst());
                    bookList.get(index2).getReservedQueue().dequeue();
                }
                else {
                    bookList.get(index2).setAvailable(true);
                    bookinfo.setText("");
                    bookinfo.append("ISBN: " + bookList.get(index2).getISBN() + "\n");
                    bookinfo.append("Title: " + bookList.get(index2).getTitle() + "\n");
                    bookinfo.append("Available: " + bookList.get(index2).isAvailable() + "\n");
                    jborrow.setEnabled(true);
                    jreturn.setEnabled(false);
                    jreserve.setEnabled(false);
                    jwaiting_queue.setEnabled(false);
                }  
            }
            else if (e.getSource() == jreserve) {
                int index2 = -1;
                for(int i = 0; i < bookList.size(); i++) {
                    if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                        Title.setText(bookList.get(i).getTitle());
                        index2 = i;
                    }
                }
                String name = JOptionPane.showInputDialog("What's your name");
                bookList.get(index2).getReservedQueue().enqueue(name);    
                bookstatus.setText("");
                bookstatus.append("The book is reserved by " + name + ".");
            }
            else if (e.getSource() == jwaiting_queue) {
                int index2 = -1;
                for(int i = 0; i < bookList.size(); i++) {
                    if(bookList.get(i).getISBN().equals(ISBN.getText())) {
                        Title.setText(bookList.get(i).getTitle());
                        index2 = i;
                    }
                }
                bookstatus.setText("");
                bookstatus.append("The waiting queue:\n");
                for(int i = 0; i < bookList.get(index2).getReservedQueue().getSize(); i++) {
                    bookstatus.append(bookList.get(index2).getReservedQueue().getList().get(i));
                    bookstatus.append("\n");
                }
            }
        }
    }
}