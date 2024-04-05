import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MyPanel extends JPanel {


private double pX = 800;
private double pY = 450;
    private int xVel;
    private int yVel;
    private int speed = 5;
private int health = 5;
private Image scaledImage;

private double centerP;

   private double distance;

   private int hitCD;

   private int blink = 10;


ArrayList<Projectile> porj = new ArrayList<>();

    public MyPanel() {
        setBackground(new Color(0, 10, 77));
        setFocusable(true);
        ImageIcon heart = new ImageIcon("images/heart.png");
        centerP = pX-12.5;

        scaledImage = heart.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        ScheduledExecutorService bob = Executors.newScheduledThreadPool(1);

        bob.schedule(() -> {
for(int i = 0; i<20; i++)
                porj.add(new Projectile(7, 25, Color.RED, i*18, 700, 100));

            bob.schedule(() -> {
                for(int i = 0; i<20; i++)
                    porj.add(new Projectile(7, 25, Color.RED, i*18 + 10, 700, 100));

                bob.schedule(() -> {
                    for(int i = 0; i<20; i++)
                        porj.add(new Projectile(7, 25, Color.RED, i*18, 700, 100));


                }, 500, TimeUnit.MILLISECONDS);
            }, 500, TimeUnit.MILLISECONDS);
        }, 500, TimeUnit.MILLISECONDS);


    }//end of constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);





pX += xVel;
pY += yVel;
g.setColor(Color.green);
if(blink > 3) {

    g.fillOval((int) pX, (int) pY, 25, 25);

}else if(blink <= 0){
    blink = 10;
}




        //delay
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){
            System.out.println(e);
        }
       for(int i = 0; i < health; i++){
g.drawImage(scaledImage, i*75, 0, null);
       }

       for(int i = 0; i < porj.size(); i++) {
           //checks if porjectile is off screen


           porj.get(i).move(g);
porj.get(i).paint(g);

distance = porj.get(i).calcDis(pX,pY);

if(distance < 12.5 + porj.get(i).getSize()/2){
    if(hitCD < 1) {
        health--;
        porj.remove(i);
        hitCD = 100;
    }
}

           if(porj.get(i).getX()>1600 || porj.get(i).getX()<0 - porj.get(i).getSize()
                   || porj.get(i).getY()>900 || porj.get(i).getY() < 0 - porj.get(i).getSize())
            porj.remove(i);
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
//thanks chatGPT for key Released and stoping ball from going off screen
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






if(hitCD > 0) {
    hitCD--;
    blink--;
}
//calls paint component
        repaint();
    }//end of paint




}//end of class



//attacks

//line, dodge by going between projectiles
//for(int i = 0; i<15; i++) {
//        porj.add(new Projectile(3, 50, Color.RED, 0, i*100, 200));
//        }