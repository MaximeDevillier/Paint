package paint;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Frame implements ActionListener{
    public ZoneGraphique zg;
    public BarraHaute bh;
    public BarraBasse bb;
    public MenuBar mb;
    public Menu m1;
    public MenuItem m11,m12,m13;

    public ArrayList<Forme> lesFormes;
    
    public Main()
    {
        lesFormes= new ArrayList<Forme>();
        bh=new BarraHaute(this);
        bb=new BarraBasse();
        zg=new ZoneGraphique(this);

        mb=new MenuBar();
        m1=new Menu("Actions");
        m11=new MenuItem("Save");
        m12=new MenuItem("Open");
        m13=new MenuItem("Symetrie");

        m1.add(m12);
        m1.add(m11);
        m1.add(m13);
        mb.add(m1);
        this.setMenuBar(mb);

        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        
        BorderLayout bl=new BorderLayout();
        this.setLayout(bl);
        this.add(bh,BorderLayout.NORTH);
        this.add(bb,BorderLayout.SOUTH);
        this.add(zg,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Main f=new Main();
        f.setSize(800, 500);
        f.setLocation(200, 200);
        f.setTitle("TP Paint");
        
        f.setVisible(true); // En dernier, tout ce qui touche à l'apparence avant
        
        //EcouteurFenetre Ef=new EcouteurFenetre(); //Ef est un écouteur externe
        //f.addWindowListener(Ef);
        f.addWindowListener(new WindowAdapter(){ //Adapter permet de ne surcharger que les méthodes qui nous interesse
            @Override
            public void windowClosing(WindowEvent e) {
        // On arrête l'application
            System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==m11)
        {
            try {
                FileOutputStream f = new FileOutputStream("dessin");
                ObjectOutputStream out = new ObjectOutputStream(f);
                //out.writeObject(lesFormes);
                Iterator it=this.lesFormes.iterator();
                while(it.hasNext())
                {
                    Forme fo=(Forme)(it.next());
                    out.writeObject(fo);
                }
                out.close();
                f.close();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource()==m12)
        {
            FileInputStream f = null;
            ObjectInputStream in = null;

            try {
                this.lesFormes.clear();
                f = new FileInputStream("dessin");
                in = new ObjectInputStream(f);
                //lesFormes=(ArrayList<Forme>)in.readObject();
                while(true)
                {
                    Forme fo=(Forme)(in.readObject());
                    this.lesFormes.add(fo);
                }

            } catch (Exception ex) {
                try {
                    in.close();
                    f.close();
                    this.zg.repaint();
                } catch (Exception ex1) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
                }                                
            }
        }
        if(e.getSource()==m13)
        {
            Iterator it;
            it=this.lesFormes.iterator();
            while(it.hasNext())
            {
                Forme f=(Forme)(it.next());
                f.xf = zg.getWidth() - f.xf;
                f.xi= zg.getWidth() -f.xi;
            }
            this.zg.repaint();
        }
    }

}
