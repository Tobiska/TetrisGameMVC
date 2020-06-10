package Viewer;

import Controller.ControllerTetris;

import javax.swing.*;
import java.awt.*;

public class FormOfName extends JPanel {
    public void setName(JTextField name) {
        Name = name;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    JTextField Name = new JTextField();
    JButton submit = new JButton();

    public FormOfName(){
        submit.setSize(100,25);
        submit.setMaximumSize(new Dimension(100,25));
        submit.setMinimumSize(new Dimension(100,25));
        submit.setText("submit");
        Name.setSize(100,25);
        Name.setMaximumSize(new Dimension(100,20));
        Name.setMinimumSize(new Dimension(100,20));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(Name);
        this.add(submit);
        repaint();
    }

    public String getName(){
        return Name.getText();
    }
    public JButton getSubmit(){
        return this.submit;
    }
}
