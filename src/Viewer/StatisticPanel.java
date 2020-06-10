package Viewer;

import javax.swing.*;

public class StatisticPanel extends JPanel {

    public CurStatPanel getCurStatPanel() {
        return curStatPanel;
    }

    public PocketPanel getPocketPanel() {
        return pocketPanel;
    }

    public RecordsPanel getRecordsPanel() {
        return recordsPanel;
    }

    private CurStatPanel curStatPanel;
    private PocketPanel pocketPanel;
    private RecordsPanel recordsPanel;

    public StatisticPanel(){
        curStatPanel = new CurStatPanel();
        pocketPanel = new PocketPanel();
        recordsPanel = new RecordsPanel();

        this.add(curStatPanel);
        this.add(pocketPanel);
        this.add(recordsPanel);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

}
