import GameClass.GameClass;
import Model.Coords;
import Model.Shape;
import Viewer.EventListener;
import Viewer.EventListener2;

import javax.swing.*;
//import java.awt.*;

public class Main {
    public static void main(String[] args) {


        GameClass game = new GameClass();
        //game.startTetris();
         /*HAHA haha = new HAHA();
         haha.startik();*/

    try {
          game.initBoarder("123");
      }catch (Exception e){
          System.out.println(e.getMessage());
      }
    }

    }

