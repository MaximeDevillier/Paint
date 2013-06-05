package paint;

import java.awt.*;
import java.io.*;

public abstract class Forme implements Serializable{
    public int xi,yi,xf,yf;
    public Color col;

    public Forme(int a,int b,int c,int d,Color co)
    {
        xi=a;
        yi=b;
        xf=c;
        yf=d;
        col=co;
    }

    public abstract void seDessiner(Graphics g);
}
