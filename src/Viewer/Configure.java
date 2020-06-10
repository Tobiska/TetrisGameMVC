package Viewer;

import Model.Coords;

import java.awt.*;

public class Configure {
        public static final int WIDTH = 10;
        public  static final int HEIGHT = 25;

        public static final int SIZE = 20;


        public static final int SIZEPOCKETBOX = 10;
        public static final int POCKETHEIGHT = 6;
        public static final int POCKETWEIGHT = 6;

        public static final Color BACK = new Color(215,215,215);
        public  Color FORE = Color.CYAN;

        public static final int LEVELGAME = 400;
        public static Coords defautspawncoords;
        public static final Color BACKRecords = new Color(0,56,65);
        public static final Color BACKPocket = new Color(0,204,102);
        public static final Color BACKCurStats = new Color(23,255,153);


        public static Font font  = new Font("Courier", Font.BOLD, 16);


        public static void setDefCoords(){
                int randx=(int)(Math.random()*6);
                defautspawncoords = new Coords(randx,-1);
        }


}
