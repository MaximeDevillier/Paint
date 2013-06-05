package paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class BarraHaute extends Panel implements ActionListener{
    public Choice couleur,forme;
    public Button beff,beffa,beffc,beffcou,brep;
    public Checkbox ccol;
    public static Main laf;

    public BarraHaute(Main m)
    {
        laf=m;
        couleur=new Choice();
        couleur.addItem("Bleu");
        couleur.addItem("Orange");
        couleur.addItem("Rouge");
        this.add(couleur);
    

        forme=new Choice();
        forme.addItem("Droite");
        forme.addItem("Cercle");
        forme.addItem("Rectangle");
        this.add(forme);


        beff=new Button("Effacer");
        this.add(beff); // rajoute le bouton à la suite à droite (ex : HTML). On met physiquement le bouton
        //beff.addActionListener(this);
        //3ème forme d'écouteur (interne et anonyme)
        beff.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(BarraHaute.laf.lesFormes.isEmpty()==false)
                {
                    BarraHaute.laf.lesFormes.remove(BarraHaute.laf.lesFormes.size()-1);
                    BarraHaute.laf.zg.repaint();
                }
            }
        });

        beffa=new Button("Effacer tout");
        this.add(beffa);
        //beffa.addActionListener(this);
        beffa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(BarraHaute.laf.lesFormes.isEmpty()==false)
                {
                    BarraHaute.laf.lesFormes.clear();
                    BarraHaute.laf.zg.repaint();
                }
            }
        });

        beffc=new Button("Effacer les cercles");
        this.add(beffc);
        beffc.addActionListener(this);

        beffcou=new Button("Effacer les formes de couleur");
        this.add(beffcou);
        beffcou.addActionListener(this);

        brep=new Button("Repeindre");
        this.add(brep);
        brep.addActionListener(this);

        ccol=new Checkbox("Colorier");
        this.add(ccol);
        

    }

   public void actionPerformed(ActionEvent e) {
         if(this.laf.lesFormes.isEmpty()==false)
         {

             if(e.getSource()==beffc)
             {
                Forme f;
                Iterator it;
                it=this.laf.lesFormes.iterator();
                while(it.hasNext())
                {
                    f=(Forme)(it.next());
                    if(f instanceof Cercle)
                    {
                        it.remove();
                    }
                }
             }

             if(e.getSource()==beffcou)
             {

                Forme f;
                Iterator it;
                it=this.laf.lesFormes.iterator();
                while(it.hasNext())
                {
                    Color c = null;
                    switch(this.laf.bh.couleur.getSelectedIndex())
                    {
                        case 0: c=Color.BLUE;break;
                        case 1: c=Color.ORANGE;break;
                        case 2: c=Color.RED;break;
                    }

                    f=(Forme)(it.next());
                    if(f.col==c)
                    {
                        it.remove();
                    }
                }
             }
             if(e.getSource()==brep)
             {
                Forme f;
                Iterator it;
                it=this.laf.lesFormes.iterator();
                while(it.hasNext())
                {
                    Color c = null;
                    switch(this.laf.bh.couleur.getSelectedIndex())
                    {
                        case 0: c=Color.BLUE;break;
                        case 1: c=Color.ORANGE;break;
                        case 2: c=Color.RED;break;
                    }

                    f=(Forme)(it.next());
                    if(f.col!=c)
                    {
                        f.col=c;
                    }
                }
             }

             if(e.getSource()==beff)
             {
                 this.laf.lesFormes.remove(this.laf.lesFormes.size()-1); // On efface le dernier élément (taille -1)
             }
             if(e.getSource()==beffa)
             {
                 this.laf.lesFormes.clear(); // On efface tout
             }
             this.laf.zg.repaint();
         }


    }
}
