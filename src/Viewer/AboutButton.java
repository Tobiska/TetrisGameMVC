package Viewer;

import javax.swing.*;
import java.awt.*;

public class AboutButton extends JButton {
    private String btext = "About";

    public AboutButton(){
        this.setText(btext);
        this.setSize(new Dimension(100,50));
        this.setMinimumSize(new Dimension(100,50));
        this.setMaximumSize(new Dimension(100,50));
    }
}
