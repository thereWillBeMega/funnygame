import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class MyPanel extends JPanel {


private int pX = 800;
private int pY = 450;
    private int xVel;
    private int yVel;
    private int speed = 5;
private int health = 5;
private Image scaledImage;


    public MyPanel() {
        setBackground(new Color(0, 10, 77));
        setFocusable(true);
        ImageIcon heart = new ImageIcon("images/heart.png");
        scaledImage = heart.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
    }//end of constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

pX += xVel;
pY += yVel;
g.setColor(Color.green);
        g.fillOval(pX,pY,25,25);

        //delay
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){
            System.out.println(e);
        }
       for(int i = 0; i < health; i++){
g.drawImage(scaledImage, i*75, 0, null);
       }

this.addKeyListener(new KeyListener() {



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();
        if(key == 'w'){
            yVel = -speed;
        }else if(key == 'a'){
          xVel = -speed;
        }else if(key == 's'){
            yVel = speed;
        }else if(key == 'd'){
            xVel = speed;
        }

    }//end of keyPressed
//thanks chat GPT for key Released and stoping ball from going off screen
    @Override
    public void keyReleased(KeyEvent e) {
        char key2 = e.getKeyChar();

        if (key2 == 'w' && yVel < 0) {
            yVel = 0;
        } else if (key2 == 'a' && xVel < 0) {
            xVel = 0;
        } else if (key2 == 's' && yVel > 0) {
            yVel = 0;
        } else if (key2 == 'd' && xVel > 0) {
            xVel = 0;
        }
    }

});//end pf key listener

        if (pX < 0) {
            pX = 0;
        } else if (pX > 1438 - 25) {
            pX = 1438 - 25;
        }

        if (pY < 0) {
            pY = 0;
        } else if (pY > 850 - 25) {
            pY = 850 - 25;
        }




//calls paint component
        repaint();
    }//end of paint




}//end of class
