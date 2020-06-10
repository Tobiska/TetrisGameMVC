package Viewer;

import javax.swing.*;
import java.awt.*;

public class ExitButton extends JButton {

    private String btext = "Exit";

    public ExitButton() {
        this.setText(btext);
        this.setSize(new Dimension(100, 50));
        this.setMinimumSize(new Dimension(100, 50));
        this.setMaximumSize(new Dimension(100, 50));
    }
}
