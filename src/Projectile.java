import java.awt.*;

public class Projectile {
private double speed;
private int size;
private Color color;
private double direction;
private double x;
private double y;

private double xVel;

private double yVel;

public Projectile(double speed, int size, Color color, double direction, double x, double y){
this.speed = speed;
this.size = size;
this.color = color;
this.direction = direction;
this.x = x;
this.y = y;

xVel = Math.sin(direction) * speed;
yVel = Math.cos(direction) * speed;
}


public void paint(Graphics g){
    g.setColor(color);
    g.fillOval((int)x,(int)y,size,size);
}


public void move(Graphics g){
    x+=xVel;
    y+=yVel;

}


}
