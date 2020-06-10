package Controller;

import GameClass.GameClass;
import Model.EventGame;
import Model.ModelBoarder;
import Model.Coords;
import Viewer.Configure;
import Viewer.MenuV;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ControllerTetris implements KeyListener {

    private GameClass MVCgame;
    private boolean isPressShiftin,isPressShiftout,isFigureInPocket;


    public ControllerTetris(GameClass MVCgame) {
        isPressShiftin = isPressShiftout = isFigureInPocket=false;
        this.MVCgame = MVCgame;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                MVCgame.getActionFigureModel().getFigure().moveFigure(new Coords(-1, 0), ModelBoarder.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                MVCgame.getActionFigureModel().getFigure().moveFigure(new Coords(1, 0), ModelBoarder.Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                MVCgame.getActionFigureModel().getFigure().moveFigure(new Coords(0, -1), ModelBoarder.Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                MVCgame.getActionFigureModel().getFigure().moveFigure(new Coords(0, 1), ModelBoarder.Direction.DOWN);
                break;
            case KeyEvent.VK_SPACE:
                MVCgame.getActionFigureModel().getFigure().turnFigure();
                break;
            case KeyEvent.VK_SHIFT:
                if(isFigureInPocket != true&&isPressShiftout==false) {
                    isPressShiftin = true;
                    isFigureInPocket = true;
                }
                else if(isFigureInPocket == true) {
                    isPressShiftout = true;
                    isFigureInPocket = false;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void Gameplay() throws Exception {
        MVCgame.getActionFigureModel().FillTable();
        int speedUp = 1,tmp;
        outside:
        while (true) {

            if(MVCgame.getActionFigureModel().isGameOver()){
                MVCgame.getActionFigureModel().writeResult();
                break outside;
            }
            if(isPressShiftout) {
                MVCgame.getActionFigureModel().createSpecificFigure();
                isPressShiftout = false;
            }else
                MVCgame.getActionFigureModel().createRandomFigure();
            while (MVCgame.getActionFigureModel().getFigure().moveFigure(new Coords(0, 1), ModelBoarder.Direction.DOWN) != -2) {
                tmp=MVCgame.getActionFigureModel().tryDelLineANDupLevel();
                if(tmp != -1) speedUp = tmp;
                if(isPressShiftin){
                    MVCgame.getActionFigureModel().catchFigureinPocket();
                    isPressShiftin = false;
                    break;
                }
                //MVCgame.getActionFigureModel().catchFigureinPocket();
                Thread.sleep(Configure.LEVELGAME/speedUp);
            }
        }
        MVCgame.getActionFigureModel().writeResult();
    }
}
