import java.awt.*;

public class Warning {

    private double x;
    private double y;
    private String type;

    private int width;
    private int height;


    public Warning(double x, double y, String type, int width, int height){
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
    }

public void paint(Graphics g){
    if(type.equals("rectangle")){
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y,width,10);
        g.fillRect((int)x, (int)y,10,height);
        g.fillRect((int)x + width - 10, (int)y,10,height);
        g.fillRect((int)x, (int)y + height - 10,width,10);
        g.fillRect((int)x + width/2 - width/12, (int)y + height/6,width/6,height/3);
        g.fillOval((int)x + width/2 - width/12, (int)y + height - height/3,width/6,height/6);
    }else if(type.equals("circle")){
        g.setColor(Color.RED);
        g.drawOval((int)x, (int)y, height, width );
        g.fillRect((int)x + width/2 - width/12, (int)y + height/6,width/6,height/3);
        g.fillOval((int)x + width/2 - width/12, (int)y + height - height/3,width/6,height/6);
    }else if(type.equals("good")){
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y,width,10);
        g.fillRect((int)x, (int)y,10,height);
        g.fillRect((int)x + width - 10, (int)y,10,height);
        g.fillRect((int)x, (int)y + height - 10,width,10);
    }else if(type.equals("gridT")){
        g.setColor(Color.RED);
        for(int i = 0; i<1600; i+= 100){
            g.drawLine((int)x + i, 0, (int)x+i, 900);
        }
        for(int i = 0; i<900; i+=100){
            g.drawLine(0, (int)y+i, 1600, (int)y+i);
        }

    }

}//end of paint


}//end of class
