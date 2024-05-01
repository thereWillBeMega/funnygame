import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MyPanel extends JPanel {


private double pX = 700;
private double pY = 600;
    private int xVel;
    private int yVel;
    private int speed = 5;
private int health = 50;
private Image scaledImage;

private double centerP;

   private double distance;

   private int hitCD;

   private int blink = 10;

   private Warning warn = new Warning(600, 100, "circle", 200, 200);


private boolean paintWarn = false;

private int warnBlink = 3;
  private  ImageIcon heart = new ImageIcon("images/heart.png");

ArrayList<Projectile> porj = new ArrayList<>();

    public MyPanel() {
        setBackground(new Color(0, 10, 140));
        setFocusable(true);

        centerP = pX-12.5;


        scaledImage = heart.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        ScheduledExecutorService bob = Executors.newScheduledThreadPool(1);
        paintWarn = true;
        bob.schedule(() -> {
            //circles
circles(700,200,0);
            bob.schedule(() -> {
                circles(700,200,10);
                paintWarn = false;
                bob.schedule(() -> {
                    circles(700,200,20);
                    paintWarn = true;
                    warn = new Warning(-20,0,"rectangle",1600,100);
                    //falling lines
                    bob.schedule(() -> {
                       fallingLines(0,0);
                        paintWarn = false;
                        bob.schedule(() -> {
                         fallingLines(0,30);
                            //sans
                            bob.schedule(() -> {
                             sansAttack(0,12);
                                bob.schedule(() -> {
                                    sansAttack(0,12);
                                    bob.schedule(() -> {
                                        sansAttack(1,17);
                                        bob.schedule(() -> {
                                            sansAttack(0,17);
                                            warn = new Warning(575,225,"good",350,350);
                                            paintWarn = true;
                                            bob.schedule(() -> {
                                                fullScreenPush();
                                                bob.schedule(() -> {
                                                    createSquare();
                                                    paintWarn = false;
                                                    bob.schedule(() -> {
                                                        porj.add(new Projectile(5, 100, Color.red, 0, 575 , 225 + 340,true));
                                                        bob.schedule(() -> {
                                                            porj.add(new Projectile(5, 100, Color.red, 90, 575 , 225 + 340,true));
                                                            bob.schedule(() -> {
                                                                porj.add(new Projectile(5, 100, Color.red, 180, 575 , 225 + 340,true));
                                                                bob.schedule(() -> {
                                                                    porj.add(new Projectile(5, 100, Color.red, 270, 575 , 225 + 340,true));
                                                                }, 400, TimeUnit.MILLISECONDS);
                                                            }, 400, TimeUnit.MILLISECONDS);
                                                        }, 400, TimeUnit.MILLISECONDS);
                                                    }, 400, TimeUnit.MILLISECONDS);
                                                }, 400, TimeUnit.MILLISECONDS);
                                            }, 2000, TimeUnit.MILLISECONDS);
                                        }, 1000, TimeUnit.MILLISECONDS);
                                    }, 1000, TimeUnit.MILLISECONDS);
                                }, 1000, TimeUnit.MILLISECONDS);
                            }, 1300, TimeUnit.MILLISECONDS);
                        }, 1000, TimeUnit.MILLISECONDS);
                    }, 1000, TimeUnit.MILLISECONDS);
                }, 400, TimeUnit.MILLISECONDS);
            }, 400, TimeUnit.MILLISECONDS);
        }, 400, TimeUnit.MILLISECONDS);


    }//end of constructor


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

if(paintWarn){

    if(warnBlink > 1) {

        warn.paint(g);

    }else if(warnBlink <= 0){
        warnBlink = 8;
    }
}



if(health > 0) {
    g.setColor(Color.green);
    if (blink > 3) {

        g.fillOval((int) pX, (int) pY, 25, 25);

    } else if (blink <= 0) {
        blink = 10;
    }
}



       for(int i = 0; i < health; i++){
g.drawImage(scaledImage, i*75, 0, null);
       }
//projectile stuff
       for(int i = 0; i < porj.size(); i++) {

Projectile p = porj.get(i);
           double d = p.getDirection();
p.move(g);
p.paint(g);

distance = p.calcDis(pX,pY);

if(distance < 12.5 + p.getSize()/2){

    if(p.getTough()) {
        if (d == 0) {
            pY -= yVel;
            pY += (p.getSpeed() + 10);
        }
        if (d == 90) {
            pX -= xVel;
            pX += p.getSpeed() + 10;
        }
        if (d == 180) {
            pY -= yVel;
            pY -= Math.abs(p.getSpeed() - 10);
        }
        if (d == 270) {
            pX -= xVel;
            pX -= Math.abs((p.getSpeed() - 10));

        }
    }
    if(hitCD < 1) {
        health--;
        if(!p.getTough()) {
            porj.remove(i);
        }
        hitCD = 100;
    }
}

           if(porj.get(i).getX()>1600 || porj.get(i).getX()<0 - porj.get(i).getSize()
                   || porj.get(i).getY()>900 || porj.get(i).getY() < 0 - porj.get(i).getSize())
            porj.remove(i);
       }



        pX += xVel;
        pY += yVel;
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
warnBlink--;
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){
            System.out.println(e);
        }
//calls paint component
        repaint();
    }//end of paint

    public void circles(int x, int y, int displacement){
        for(int i = 0; i<20; i++)
            porj.add(new Projectile(7, 30, Color.RED, i*18 + displacement, x, y,false));

    }
//direction = 0/1
    public void fallingLines( int direction, int displacement){
        for(int i = 0; i<16; i++)
            porj.add(new Projectile(13, 17, Color.RED, direction * 180, 100*i + displacement, direction * 800,false));
    }

//direction = 0/1
    public void sansAttack(int direction, int speed){

        for(int i = 0; i<30; i++)
            porj.add(new Projectile(speed, 25, Color.BLACK, direction * 180 + 270, 1550 - direction * 1500, i*15,true));
        for(int i = 0; i<30; i++)
            porj.add(new Projectile(speed, 25, Color.BLACK, direction * 180 + 90, 50 + direction * 1500, i*15+450,true));
    }

    public void fullScreenPush(){
        for(int i = 0; i<16; i++){
            porj.add(new Projectile(8, 100, Color.BLACK, 0, i*100, 0,true));
            porj.add(new Projectile(8, 100, Color.BLACK, 180, i*100, 800,true));
        }
        for(int i = 0; i<9; i++){
            porj.add(new Projectile(17, 100, Color.BLACK, 270, 1500, i*100,true));
            porj.add(new Projectile(17, 100, Color.BLACK, 90, 0, i*100,true));
        }
    }

    public void createSquare(){

      porj.clear();
for(int i = 0; i<35; i++){
    porj.add(new Projectile(0, 20, Color.BLACK, 90, 575, i*10 + 225,true));
    porj.add(new Projectile(0, 20, Color.BLACK, 270, 575+340, i*10 + 225,true));
    porj.add(new Projectile(0, 20, Color.BLACK, 0, 575 + i*10, 225,true));
    porj.add(new Projectile(0, 20, Color.BLACK, 180, 575 + i*10, 225 + 340,true));
}
    }//end of square
}//end of class



