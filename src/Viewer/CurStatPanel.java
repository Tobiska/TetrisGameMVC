package Viewer;

import javax.swing.*;
import java.awt.*;

public class CurStatPanel extends JPanel {
    private JLabel ScoreText,LineText,LevelText;

    public CurStatPanel(){
        ScoreText = new JLabel("Score: "+ 0);
        LineText = new JLabel("Lines: "+ 0);
        LevelText = new JLabel("Level: "+ 0);
        ScoreText.setVisible(true);
        LineText.setVisible(true);
        LevelText.setVisible(true);
        ScoreText.setFont(Configure.font);
        LineText.setFont(Configure.font);
        LevelText.setFont(Configure.font);
        ScoreText.setForeground(Color.BLACK);
        LineText.setForeground(Color.BLACK);
        LevelText.setForeground(Color.BLACK);
        this.setLayout(new GridLayout(3,1));

        this.setMaximumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+20)/3));
        this.setMinimumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+20)/3));
        this.setPreferredSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE)/3));

        this.add(ScoreText);
        this.add(LineText);
        this.add(LevelText);
        this.setBackground(Configure.BACKCurStats);
    }



    public void ChangeInfoLines(int count){
        LineText.setText("Lines: "+ count);
        this.repaint();
    }

    public void ChangeInfoScore(int count){
        ScoreText.setText("Score: "+ count);
        this.repaint();
    }
    public void ChangeInfoLevel(int count){
        LevelText.setText("Level: "+ count);
        this.repaint();
    }


}
