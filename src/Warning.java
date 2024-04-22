import java.awt.*;

public class Warning {

    private double x;
    private double y;
    private String type;
    private int time;
    private int width;
    private int height;


    public Warning(double x, double y, String type, int time, int width, int height){
this.x = x;
        this.y = y;
        this.type = type;
        this.time = time;
        this.width = width;
        this.height = height;
    }

public void paint(Graphics g){
    g.setColor(Color.RED);
    if(type.equals("rectangle")){
        g.fillRect((int)x, (int)y,width,10);
        g.fillRect((int)x, (int)y,10,height);
        g.fillRect((int)x + width - 10, (int)y,width,10);
        g.fillRect((int)x, (int)y + height - 10,10,height);
        g.fillRect((int)x + width/2 - width/12, (int)y - height/2 - 1,width/6,height/4);
    }

}//end of paint


}//end of class
