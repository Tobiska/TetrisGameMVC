package Viewer;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {
    private String AboutText;
    private JButton BackButton;
    private JTextArea AboutArea;

    public AboutPanel(String Text){
        this.setMaximumSize(new Dimension(Configure.HEIGHT*Configure.SIZE,Configure.WIDTH*Configure.SIZE));
        this.setMinimumSize(new Dimension(Configure.HEIGHT*Configure.SIZE,Configure.WIDTH*Configure.SIZE));
        this.setPreferredSize(new Dimension(Configure.HEIGHT*Configure.SIZE,Configure.WIDTH*Configure.SIZE));
        this.setLayout(null);
        this.AboutText = Text;
        this.AboutArea = new JTextArea();
        this.AboutArea.setMaximumSize(new Dimension(Configure.HEIGHT*Configure.SIZE-10,Configure.WIDTH*Configure.SIZE));
        this.AboutArea.setMinimumSize(new Dimension(Configure.HEIGHT*Configure.SIZE-10,Configure.WIDTH*Configure.SIZE));
        this.AboutArea.setPreferredSize(new Dimension(Configure.HEIGHT*Configure.SIZE-10,Configure.WIDTH*Configure.SIZE));
        this.BackButton = new JButton();
        this.AboutArea.setText(this.AboutText);
        BackButton.setText("Back");
        BackButton.setSize(new Dimension(10,Configure.WIDTH*Configure.SIZE));
        this.add(this.AboutArea);
        this.add(this.BackButton);
    }
}
