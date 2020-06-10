package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public enum Shape {
        NOSHAPE (0,0,0,0,0,0,0,0,0,0,0),
        I1(0,125,52,0,0 ,1,0 ,2,0 ,3,0),//FIXED
        I2(0,125,52, 0,0, 0,1, 0,2, 0,3),

        L1(47,101,86,0,0, 1,0, 0,1, 0,2),//FIXED
        L2(47,101,86,0,0, 1,0, 2,0, 2,1),
        L3(47,101,86,0,0, 0,1, 1,1, 2,1),
        L4(47,101,86,0,2, 1,2, 1,1, 1,0),

        J1(0,155,118,0,0, 1,0, 1,1, 1,2),//FIXED
        J2(0,155,118,0,1, 1,1, 2,1, 2,0),
        J4(0,155,118,0,0, 0,1, 0,2, 1,2),
        J3(0,155,118,0,0, 0,1, 1,0, 2,0),



        O(0,56,65,0,0, 1,0 ,0,1, 1,1),//Квадрат



        Z1(83,55,122,0,1, 1,1, 1,0, 2,0),//FIXED
        Z2(83,55,122,0,0, 0,1, 1,1, 1,2),//FIXED


        T1(40,7,26,0,0, 1,0, 2,0, 1,1),//FIXED
        T2(40,7,26,0,1, 1,0, 1,1, 1,2),//FIXED
        T3(40,7,26,0,1, 1,0, 1,1, 2,1),//FIXED
        T4(40,7,26,0,0, 0,1, 0,2, 1,1),//FIXED

        S1(54,44,18,0,0, 0,1, 1,1,  1,2),//FIXED
        S2(54,44,18,1,0, 0,1, 1,1, 2,0);//FIXED


        private List<Coords> dots;
        private Color ShapeColor;
        private SizeFigure sizeFigure;

        private Shape(int r,int g,int b,int ... coords){
            ShapeColor = new Color(r,g,b);
            dots = new ArrayList<Coords>();
            for(int it = 0; it < coords.length;it+=2){
                dots.add(new Coords(coords[it],coords[it+1]));
            }
        }
        private Shape(){
            ShapeColor = new Color(0,0,0);
            dots = new ArrayList<Coords>();
            for(int it = 0; it < 4;it+=2){
                dots.add(new Coords(0,0));
            }
        }

        public List<Coords> getDots(){
             return dots;
        }
        public Color getShapeColor(){
            return ShapeColor;
        }

        public SizeFigure getSizeFigure(){
            return sizeFigure;
        }


        public int x(Coords el){
            return el.x;
        }
        public int y(Coords el){
            return el.y;
        }



       /* public static Shape getRandomFigure(){
            return Shape.values()[(int)(Math.random()*Shape.values().length)];
        }*/


       public static Shape getRandomFigure(){
           switch((int)(Math.random()*7)){
               case 0:return I1;
               case 1:return J1;
               case 2:return L1;
               case 3:return O;
               case 4:return S1;
               case 5:return T1;
               case 6:return Z1;
           }
           return NOSHAPE;
       }

        public void setSizeFigure(){
            SizeFigure tmp = new SizeFigure(-100,-100,100,100);
            for(int i = 0;i < 4;++i){
                if(tmp.sizeYtl < dots.get(i).y){
                    tmp.sizeYtl = dots.get(i).y;
                }
                if(tmp.sizeYbr > dots.get(i).y){
                    tmp.sizeYbr = dots.get(i).y;
                }
                if(tmp.sizeXtl < dots.get(i).x){
                    tmp.sizeXtl = dots.get(i).x;
                }
                if(tmp.sizeXbr > dots.get(i).x){
                    tmp.sizeXbr = dots.get(i).x;
                }
            }
            tmp.CalculateSize();
            this.sizeFigure = tmp;
        }



        public Shape rotateLeft(){
            switch (this){
                case I1:return I2;
                case I2:return I1;

                case L1:return L2;
                case L2:return L3;
                case L3:return L4;
                case L4:return L1;

                case J1:return J2;
                case J2:return J3;
                case J3:return J4;
                case J4:return J1;

                case T1:return T2;
                case T2:return T3;
                case T3:return T4;
                case T4:return T1;

                case S1:return S2;
                case S2:return S1;

                case Z1:return Z2;
                case Z2:return Z1;

                case O:return O;
            }
            return O;
        }

        public Shape rotateRight(){
            return this.rotateLeft().rotateLeft().rotateLeft();
        }

        /*public Shape rotateLeft(){
            if(this == Shape.O){
                return this;
            }else{
                for(int it = 0;it < 4;it++){
                    Coords tmp = new Coords(dots.get(it).x,dots.get(it).y);
                    tmp.x = dots.get(it).y;
                    tmp.y = -1*dots.get(it).x;
                    dots.set(it,tmp);
                }
            }
            return this;
        }*/



        /*public Shape rotateRight(){
            if(this == Shape.O){
                return this;
            }else{
                for(int it = 0;it < 4;it++){
                    Coords tmp = new Coords(dots.get(it).x,dots.get(it).y);
                    tmp.x = -1*dots.get(it).y;
                    tmp.y = dots.get(it).x;
                    dots.set(it,tmp);
                }
            }
            return this;
        }*/
        }
