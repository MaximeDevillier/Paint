package paint;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class ZoneGraphique extends Canvas implements MouseListener,MouseMotionListener{
    public Main laf;
    public int xi,yi,xf,yf;
    Droite dtemp;
    public ZoneGraphique(Main m)
    {
        laf=m;
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g)
    {
        Forme f;
        Iterator it;
        it=this.laf.lesFormes.iterator();
        while(it.hasNext())
        {
            f=(Forme)(it.next());
            f.seDessiner(g);

        }
    }
    public void mouseClicked(MouseEvent e) { // Ecouteur interne à une classe
        
    }

    public void mousePressed(MouseEvent e) {
        xi=e.getX();
        yi=e.getY();
        if(this.laf.bh.ccol.getState()==true)
        {
            Color c = null;
            Graphics g;
            
            

            switch(this.laf.bh.couleur.getSelectedIndex())
            {
                case 0: c=Color.BLUE;break;
                case 1: c=Color.ORANGE;break;
                case 2: c=Color.RED;break;
            }

            g=this.getGraphics();
            g.setColor(c);
            Iterator it;
            it=this.laf.lesFormes.iterator();
            while(it.hasNext())
            {
                Forme f=(Forme)(it.next());
                if(f instanceof Rectangle)
                {
                    if((xi>Math.min(f.xi,f.xf)) && (xi<Math.max(f.xi,f.xf)))
                    {
                        if((yi>Math.min(f.yi,f.yf)) && (yi<Math.max(f.yi,f.yf)))
                        {
                            f.col=c;
                        }
                    }
                }
                if(f instanceof Cercle)
                {
                    if((xi>Math.min(f.xi,f.xf)) && (xi<Math.max(f.xi,f.xf)))
                    {
                        if((yi>Math.min(f.yi,f.yf)) && (yi<Math.max(f.yi,f.yf)))
                        {
                            f.col=c;
                        }
                    }
                }
            }
            this.repaint();
        }
}

    public void mouseReleased(MouseEvent e) {
            if(this.laf.bh.ccol.getState()==false)
            {

            Color c = null;
            Graphics g;

            xf=e.getX();
            yf=e.getY();

            switch(this.laf.bh.couleur.getSelectedIndex())
            {
                case 0: c=Color.BLUE;break;
                case 1: c=Color.ORANGE;break;
                case 2: c=Color.RED;break;
            }

            g=this.getGraphics();
            g.setColor(c);

            switch(this.laf.bh.forme.getSelectedIndex())
            {
                case 0: {
                    Droite d=new Droite(xi,yi,xf,yf,c);
                    d.seDessiner(g);
                    this.laf.lesFormes.add(d);break;}
                case 1: {
                    Cercle c1=new Cercle(xi,yi,xf,yf,c);
                    c1.seDessiner(g);
                    this.laf.lesFormes.add(c1);break;}
                case 2:{
                    Rectangle r=new Rectangle(xi,yi,xf,yf,c);
                    r.seDessiner(g);
                    this.laf.lesFormes.add(r);break;}
            }
            
            }
        }

    public void mouseEntered(MouseEvent e) {
        }

    public void mouseExited(MouseEvent e) {
        }

    public void mouseDragged(MouseEvent e) {
        if(this.laf.bh.ccol.getState()==false)
        {
        Graphics g;
        Color c=Color.BLACK;
        g=this.getGraphics();
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        this.paint(g);

        xf=e.getX();
        yf=e.getY();

        switch(this.laf.bh.couleur.getSelectedIndex())
        {
            case 0: c=Color.BLUE;break;
            case 1: c=Color.ORANGE;break;
            case 2: c=Color.RED;break;
        }

        g=this.getGraphics();
        g.setColor(c);

        switch(this.laf.bh.forme.getSelectedIndex())
        {
            case 0: {
                Droite d=new Droite(xi,yi,xf,yf,c);
                d.seDessiner(g);
                break;}
            case 1: {
                Cercle c1=new Cercle(xi,yi,xf,yf,c);
                c1.seDessiner(g);
                break;}
            case 2:{
                Rectangle r=new Rectangle(xi,yi,xf,yf,c);
                r.seDessiner(g);
                break;}
        }
        }
}

    public void mouseMoved(MouseEvent e) {
        this.laf.bb.x.setText("X= "+e.getX());
        this.laf.bb.y.setText("Y= "+e.getY());
        }
}
