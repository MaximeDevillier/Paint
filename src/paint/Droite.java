package paint;

import java.awt.*;

public class Droite extends Forme{
    public Droite (int a,int b,int c,int d,Color cc)
    {
        super(a,b,c,d,cc);
    }

    @Override
    public void seDessiner(Graphics g)
    {
        g.setColor(col);
        g.drawLine(xi,yi,xf,yf);
    }

}
