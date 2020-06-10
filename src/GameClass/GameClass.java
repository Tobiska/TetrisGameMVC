package GameClass;

import Controller.*;
import Viewer.*;
import Model.*;

import javax.swing.*;

public class GameClass implements EventListener2{

    private MenuV viewMenu = null;
    private Menu modelMenu = null;
    private Controller controller;
    private boolean Game = false;



    private ModelBoarder actionFigureModel;
    private ControllerTetris controllerTetris;
    private Boarder boarder;



    public GameClass (){

    }


    public MenuV getViewMenu(){
        return viewMenu;
    }
    public Menu getModelMenu(){
        return modelMenu;
    }
    public Controller getController(){
        return controller;
    }
    public void setGame(boolean ex) {
        Game = ex;
    }


    public ModelBoarder getActionFigureModel() { return actionFigureModel;}
    public ControllerTetris getControllerTetris() { return controllerTetris; }


    public void startTetris(){
        controller = new Controller(this);
        modelMenu = new Menu(this);
        //controller = new Controller(this);
        viewMenu = new MenuV(this);
        getController().launchMenu();
    }

    public void update(String eventType,String name){
        if(eventType =="startgameplay"){
            initBoarder(name);
        }
    }

    public void initBoarder(String name){
        controllerTetris = new ControllerTetris(this);
       actionFigureModel = new ModelBoarder(this);
        boarder = new Boarder(this);
        actionFigureModel.eventManager.register("add",boarder);
        actionFigureModel.eventManager.register("move",boarder);
        actionFigureModel.eventManager.register("rotate",boarder);
        actionFigureModel.eventManager.register("deleteFullline",boarder);
        actionFigureModel.eventManager.register("finish",boarder);
        actionFigureModel.eventManager.register("addLines",boarder);
        actionFigureModel.eventManager.register("addScore",boarder);
        actionFigureModel.eventManager.register("LevelUp",boarder);
        actionFigureModel.eventManager.register("catchInPocket",boarder);
        actionFigureModel.eventManager.register("throwFromPocket",boarder);
        actionFigureModel.eventManager.register("printRecords",boarder);
        //getModelMenu().setUserName(name);
        getActionFigureModel().setUserName(name);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            getControllerTetris().Gameplay();
        }catch(Exception ex){
            System.out.println("Exc");
        }

    }


}


