
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {


    public static void main(String[] args) {



        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();


        frame.setSize(1600,900);
        frame.add(panel);

        frame.setVisible(true);
    }//end of main method
}