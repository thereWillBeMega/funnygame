import java.awt.*;

public class Projectile {
private int speed;
private int size;
private Color color;
private double direction;
private int x;
private int y;

public Projectile(int speed, int size, Color color, double direction, int x, int y){
this.speed = speed;
this.size = size;
this.color = color;
this.direction = direction;
this.x = x;
this.y = y;
}


public void paint(Graphics g){
    g.setColor(color);
    g.fillOval(x,y,size,size);
}

}
