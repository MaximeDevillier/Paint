package paint;

import java.awt.*;

public class Rectangle extends Forme{
    public Rectangle (int a,int b,int c,int d,Color cc)
    {
        super(a,b,c,d,cc);
    }

    @Override
    public void seDessiner(Graphics g)
    {
        g.setColor(col);
        g.drawRect(Math.min(xi,xf),Math.min(yi,yf),Math.abs(xi-xf),Math.abs(yi-yf));
    }
}
