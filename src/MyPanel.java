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
private int health = 5;
private Image scaledImage;

private double centerP;

   private double distance;

   private int hitCD;

   private int blink = 10;
private int ranDir;
   private Warning warn = new Warning(600, 100, "circle", 200, 200);

private boolean spawnBullets = false;
private boolean paintWarn = false;

private boolean clearPorj = false;
private int warnBlink = 3;
private int bulletCD = 0;
  private  ImageIcon heart = new ImageIcon("images/heart.png");
    ScheduledExecutorService Aubry = Executors.newScheduledThreadPool(1);
ArrayList<Projectile> porj = new ArrayList<>();

    public MyPanel() {
        setBackground(new Color(0, 10, 140));
        setFocusable(true);

        centerP = pX-12.5;


        scaledImage = heart.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);

        paintWarn = true;
        Aubry.schedule(() -> {
            //circles
circles(700,200,0,7,40);
            Aubry.schedule(() -> {
                circles(700,200,10,7,40);
                paintWarn = false;
                Aubry.schedule(() -> {
                    circles(700,200,20,7,40);
                    paintWarn = true;
                    warn = new Warning(-20,0,"rectangle",1600,100);
                    //falling lines
                    Aubry.schedule(() -> {
                       fallingLines(0,0);
                        paintWarn = false;
                        Aubry.schedule(() -> {
                         fallingLines(0,30);
                            paintWarn = true;
                            warn = new Warning(-20,400,"good",1600,100);
                            //sans
                            Aubry.schedule(() -> {
                             sansAttack(0,11);
                             paintWarn = false;
                                Aubry.schedule(() -> {
                                    sansAttack(0,11);
                                    Aubry.schedule(() -> {
                                        sansAttack(0,11);
                                        Aubry.schedule(() -> {
                                            sansAttack(0,11);
                                            warn = new Warning(575,225,"good",350,350);
                                            paintWarn = true;
                                            Aubry.schedule(() -> {
                                                fullScreenPush();
                                                Aubry.schedule(() -> {
                                                    createSquare();
                                                    paintWarn = false;
                                                    Aubry.schedule(() -> {
                                                        circles(800,100,0,3,20);
                                                        circles(800,700,10,3,20);
                                                        circles(100,450,20,3,20);
                                                        circles(1300,450,30,3,20);
                                                        Aubry.schedule(() -> {
                                                            circles(100,100,0,3,20);
                                                            circles(1500,100,10,3,20);
                                                            circles(100,800,20,3,20);
                                                            circles(1500,800,30,3,20);
                                                            Aubry.schedule(() -> {
                                                                circles(800,100,0,3,20);
                                                                Aubry.schedule(() -> {
                                                                    circles(800,700,10,3,20);
                                                                    Aubry.schedule(() -> {
                                                                        circles(100,450,20,3,20);
                                                                        Aubry.schedule(() -> {
                                                                           clearPorj = true;
                                                                            warn = new Warning(0,0,"gridT",350,350);
                                                                            paintWarn = true;
                                                                            Aubry.schedule(() -> {
                                                                              grid(0,0);
                                                                                warn = new Warning(50,50,"gridT",350,350);
                                                                                Aubry.schedule(() -> {
                                                                                    grid(50,50);
                                                                                    warn = new Warning(25,25,"gridT",350,350);
                                                                                    Aubry.schedule(() -> {
                                                                                        grid(25,25);
                                                                                        paintWarn = false;
                                                                                        Aubry.schedule(() -> {
                                                                                            spawnBullets = true;
                                                                                            Aubry.schedule(() -> {

                                                                                            }, 1000, TimeUnit.MILLISECONDS);
                                                                                        }, 700, TimeUnit.MILLISECONDS);
                                                                                    }, 700, TimeUnit.MILLISECONDS);
                                                                                }, 700, TimeUnit.MILLISECONDS);
                                                                            }, 700, TimeUnit.MILLISECONDS);
                                                                        }, 3000, TimeUnit.MILLISECONDS);
                                                                    }, 200, TimeUnit.MILLISECONDS);
                                                                }, 200, TimeUnit.MILLISECONDS);
                                                            }, 200, TimeUnit.MILLISECONDS);
                                                        }, 800, TimeUnit.MILLISECONDS);
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
        }, 2000, TimeUnit.MILLISECONDS);


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
}else{
    Aubry.shutdown();
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
            pY -= Math.abs(p.getSpeed() + 10);
        }
        if (d == 270) {
            pX -= xVel;
            pX -= Math.abs((p.getSpeed() + 10));

        }
    }
    if(hitCD < 1) {
        health-= p.getDamage();
        if(!p.getTough()) {
            porj.remove(i);
        }
        if(p.getDamage() != 0){
            hitCD = 100;
        }
    }
}

           if(porj.get(i).getX()>1600 || porj.get(i).getX()<0 - porj.get(i).getSize()
                   || porj.get(i).getY()>900 || porj.get(i).getY() < 0 - porj.get(i).getSize())
            porj.remove(i);
       }

if(spawnBullets){
    if(bulletCD > 2) {
        ranDir = (int) (Math.random() * 4);
        if (ranDir == 0) {
            porj.add(new Projectile(3, 30, Color.RED, 180, (int) (Math.random() * 1570), 870, false, 1));
        } else if (ranDir == 1) {
            porj.add(new Projectile(3, 30, Color.RED, 90, 0, (int) (Math.random() * 870), false, 1));
        } else if (ranDir == 2) {
            porj.add(new Projectile(3, 30, Color.RED, 0, (int) (Math.random() * 1570), 0, false, 1));
        } else if (ranDir == 3) {
            porj.add(new Projectile(3, 30, Color.RED, 270, 1570, (int) (Math.random() * 870), false, 1));
        }
    }
    if(bulletCD <= 0){
        bulletCD = 4;
    }
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




if(spawnBullets){
    bulletCD--;
}

if(hitCD > 0) {
    hitCD--;
    blink--;
}
warnBlink--;

if(clearPorj){
    porj.clear();
    clearPorj = false;
}
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){
            System.out.println(e);
        }
//calls paint component
        repaint();
    }//end of paint

    public void circles(int x, int y, int displacement, int speed,int size){
        for(int i = 0; i<20; i++)
            porj.add(new Projectile(speed, size, Color.RED, i*18 + displacement, x, y,false,1));

    }
//direction = 0/1
    public void fallingLines( int direction, int displacement){
        for(int i = 0; i<16; i++)
            porj.add(new Projectile(13, 17, Color.RED, direction * 180, 100*i + displacement, direction * 800,false,1));
    }

//direction = 0/1
    public void sansAttack(int direction, int speed){

        for(int i = 0; i<31; i++)
            porj.add(new Projectile(speed, 25, Color.RED, direction * 180 + 270, 1550 - direction * 1500, i*15,false,1));
        for(int i = 0; i<30; i++)
            porj.add(new Projectile(speed, 25, Color.RED, direction * 180 + 90, 50 + direction * 1500, i*15+450,false,1));
    }

    public void fullScreenPush(){
        for(int i = 0; i<16; i++){
            porj.add(new Projectile(7.5, 100, Color.BLACK, 0, i*100, 0,true,0));
            porj.add(new Projectile(12, 100, Color.BLACK, 180, i*100, 800,true,0));
        }
        for(int i = 0; i<9; i++){
            porj.add(new Projectile(25, 100, Color.BLACK, 270, 1500, i*100,true,0));
            porj.add(new Projectile(20, 100, Color.BLACK, 90, 0, i*100,true,0));
        }
    }

    public void createSquare(){

   clearPorj = true;
        try{
            Thread.sleep(20);
        }catch(InterruptedException e){
            System.out.println(e);
        }
for(int i = 0; i<35; i++){
    porj.add(new Projectile(0, 20, Color.BLACK, 90, 575, i*10 + 225,true,0));
    porj.add(new Projectile(0, 20, Color.BLACK, 270, 575+340, i*10 + 225,true,0));
    porj.add(new Projectile(0, 20, Color.BLACK, 0, 575 + i*10, 225,true,0));
    porj.add(new Projectile(0, 20, Color.BLACK, 180, 575 + i*10, 225 + 340,true,0));
}
    }//end of square

    public void grid(int x, int y){
        for(int i = 0; i<1600; i+= 100){
            porj.add(new Projectile(20, 20, Color.RED, 180, x+i, 879,false,1));
            porj.add(new Projectile(20, 20, Color.RED, 0, x+i, 0,false,1));
        }
        for(int i = 0; i<900; i+=100){
            porj.add(new Projectile(30, 20, Color.RED, 90, 0, y+i,false,1));
            porj.add(new Projectile(30, 20, Color.RED, 270, 1579, y+i,false,1));
        }
    }

}//end of class



