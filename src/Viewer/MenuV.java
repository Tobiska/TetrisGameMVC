package Viewer;

import GameClass.GameClass;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuV extends JFrame{

    private FormOfName formOfName;

    private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    private boolean startgame = false;

    private JPanel AboutPanel;

    private boolean finishgame = false;

    private GameClass MVCgame = null;

    public NewButton getNewButton() {
        return newButton;
    }

    public SettingsButton getSettingsButton() {
        return settingsButton;
    }

    public AboutButton getAboutButton() {
        return aboutButton;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    private NewButton newButton;
    private SettingsButton settingsButton;
    private AboutButton aboutButton;
    private ExitButton exitButton;

    private JPanel P;

    private ReferenceFrame RefFrame;

    private int DWidth = 200,DHeight = 300;

    private final String title = "Tetris";

    public  MenuV(GameClass game){
        this.MVCgame = game;
        RefFrame = new ReferenceFrame();

        P = new JPanel();
       // setLayout(new BorderLayout());
        newButton = new NewButton();
        newButton.addActionListener(game.getController());
        settingsButton = new SettingsButton();
        //settingsButton().addActionListener(game.getControllerTetris());
        aboutButton = new AboutButton();
        aboutButton.addActionListener(game.getController());
        exitButton = new ExitButton();
        exitButton.addActionListener(game.getController());

        P.setLayout(new BoxLayout(P,BoxLayout.Y_AXIS));
        P.add(newButton);
        P.add(settingsButton);
        P.add(aboutButton);
        P.add(exitButton);

        add(P);
        setSize(new Dimension(90,200));

    }



    public void printAbout(String aboutText){
        AboutPanel aboutPanel = new AboutPanel(aboutText);
        RefFrame.setPanel(AboutPanel);
    }


    public FormOfName getFormOfName(){
        return formOfName;
    }

    public void setStartgame(boolean e){
        P.setVisible(false);
        formOfName = new FormOfName();
        formOfName.getSubmit().addActionListener(MVCgame.getController());
        this.add(formOfName);
        setSize(new Dimension(200,100));
        repaint();
    }
    public void start(){
        setVisible(true);
    }



}
