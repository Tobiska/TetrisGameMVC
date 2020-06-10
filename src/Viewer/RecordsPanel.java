package Viewer;

import javax.swing.*;
import java.awt.*;

public class RecordsPanel extends JPanel {

    private Color colorRec;
    private JTextArea Records;

    public RecordsPanel()
    {
        colorRec = Configure.BACKRecords;
        Records = new JTextArea("Nothing");
        this.setBackground(colorRec);
        this.setLayout(new GridBagLayout());

        Records.setMaximumSize(new Dimension(80,(Configure.HEIGHT* Configure.SIZE)/3));
        Records.setMinimumSize(new Dimension(80,(Configure.HEIGHT* Configure.SIZE)/3));
        Records.setPreferredSize(new Dimension(80,(Configure.HEIGHT* Configure.SIZE)/3));
        this.add(Records);

        this.setMaximumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+30)/3));
        this.setMinimumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+30)/3));
        this.setPreferredSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+30)/3));
    }

    public void printTableRecords(String Text){
        Records.setText(Text);
        Records.repaint();
    }
}
