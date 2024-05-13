import java.awt.*;

public class Projectile {
private double speed;
private double size;
private Color color;
private double direction;
private double x;
private double y;

private double xVel;

private double yVel;

private double center;

private boolean tough;
private int damage;




public Projectile(double speed, double size, Color color, double direction, double x, double y, boolean tough, int damage){
this.speed = speed;
this.size = size;
this.color = color;
this.direction = direction;
this.x = x;
this.y = y;
this.tough = tough;
this.damage = damage;
center = x-size/2;


direction = Math.toRadians(direction);
xVel = Math.sin(direction) * speed;
yVel = Math.cos(direction) * speed;
}


public void paint(Graphics g){
    g.setColor(color);
    g.fillOval((int)x,(int)y,(int)size,(int)size);
}//end of paint


public void move(Graphics g){
    x+=xVel;
    y+=yVel;

}//end of move

    public double calcDis(double x, double y){
return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }


public double getCenter(){
    return center;
}
    public double getDirection(){
        return direction;
    }
    public void setDirection(double dur){
        direction = Math.toRadians(dur);
        xVel = Math.sin(direction) * speed;
        yVel = Math.cos(direction) * speed;
    }
    public double getSize(){
        return size;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getSpeed(){
        return speed;
    }
    public boolean getTough(){
        return tough;
    }
    public int getDamage(){return damage;}

}
