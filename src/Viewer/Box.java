package Viewer;

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    private  Color color;

    public Box(int x,int y,int size){
        setBounds(size*x,size*y,size,size);
        setBackground(Configure.BACK);
        setLocation(x* size,y*size);
        setVisible(true);
    }

    public Color getBoxColor(){
        return this.color;
    }

    public void setColor(Color color) {
        this.setBackground(color);
        this.color = color;
    }
}
