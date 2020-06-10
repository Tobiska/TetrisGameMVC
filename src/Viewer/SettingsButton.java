package Viewer;

import javax.swing.*;
import java.awt.*;

public class SettingsButton extends JButton {
    private String btext = "Settings";

    public SettingsButton(){
        this.setText(btext);
        this.setSize(new Dimension(100,50));
        this.setMinimumSize(new Dimension(100,50));
        this.setMaximumSize(new Dimension(100,50));
    }
}
