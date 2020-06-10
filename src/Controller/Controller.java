package Controller;
import GameClass.GameClass;
import Viewer.*;
import Model.*;

import javax.imageio.IIOException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

    private GameClass MVCgame;
    private EventGame eventGame;

    public Controller(GameClass MVCgame){
        this.MVCgame =MVCgame;
        eventGame = new EventGame("startgameplay");
        eventGame.register("startgameplay",MVCgame);
    }


    public void launchMenu(){
        MVCgame.getViewMenu().start();
    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == MVCgame.getViewMenu().getNewButton()) {
            MVCgame.getViewMenu().setStartgame(true);
        } else if (obj == MVCgame.getViewMenu().getSettingsButton()) {


        } else if (obj == MVCgame.getViewMenu().getAboutButton()) {
            try {
                MVCgame.getViewMenu().printAbout(MVCgame.getModelMenu().getAboutText());
            }catch (IOException ex){
            }

        } else if (obj == MVCgame.getViewMenu().getExitButton()) {


        } else if (obj == MVCgame.getViewMenu().getFormOfName().getSubmit()) {
            MVCgame.getViewMenu().setVisible(false);
            //eventGame.notify("startgameplay",MVCgame.getViewMenu().getFormOfName().getName());
        } else {
            System.out.println("ActionPerformed");
        }
    }


}
