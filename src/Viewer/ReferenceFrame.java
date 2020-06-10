package Viewer;

import javax.swing.*;
import java.awt.*;

public class ReferenceFrame extends JFrame {

    private JPanel SomePanel;

    public ReferenceFrame(){
        setSize(Configure.WIDTH* Configure.SIZE+100, Configure.HEIGHT* Configure.SIZE+15);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public void setPanel(JPanel panel){
        SomePanel = panel;
        this.add(SomePanel);
        setVisible(false);
        repaint();
    }

}
