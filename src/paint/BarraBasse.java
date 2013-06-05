package paint;

import java.awt.*;

public class BarraBasse extends Panel{
    public Label message,x,y;

    public BarraBasse(){
        message=new Label("Cliquer ici pour commencer");
        this.add(message);

        x=new Label("         ");
        this.add(x);
        
        y=new Label("         ");
        this.add(y);
    }
}
