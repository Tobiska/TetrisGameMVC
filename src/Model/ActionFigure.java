package Model;

import Viewer.Configure;

import java.util.List;

public class ActionFigure {

    private Shape figure;
    private Coords coordsCurFigure;
    private int[][] LandedBoxMap;
    private EventManager eventManager;

    public ActionFigure(int[][] landedBoxMap,EventManager eventManager){
        this.eventManager = eventManager;
        LandedBoxMap = landedBoxMap;
        coordsCurFigure = new Coords(0,0);

    }

    public Shape getFigure(){
        return figure;
    }

    public void setFigure(Shape figure,Coords coords){
        this.figure = figure;
        figure.setSizeFigure();
        coordsCurFigure = coords;
    }


    public Coords getCoordsCurFigure() {
        return coordsCurFigure;
    }

    public int Canmove(Shape figure, int shiftX, int shiftY, ModelBoarder.Direction direction){
        if(figure.getSizeFigure().sizeYbr+coordsCurFigure.y +shiftY <0)return -1 ;//move up
        if(figure.getSizeFigure().sizeYtl+coordsCurFigure.y+shiftY> Configure.HEIGHT-1)return -2; //move down//////////
        if(figure.getSizeFigure().sizeXbr+coordsCurFigure.x+shiftX < 0)return -3 ;//move left
        if(figure.getSizeFigure().sizeXtl+coordsCurFigure.x+shiftX > Configure.WIDTH-1)return -4;//move right
        for(Coords it_coords:figure.getDots()) {
            if (LandedBoxMap[coordsCurFigure.x+it_coords.x+shiftX][coordsCurFigure.y+it_coords.y+shiftY]==1){
                switch (direction){
                    case DOWN: return -2;
                    case UP: return -1;
                    case LEFT: return -3;
                    case RIGHT: return -4;
                    case NOWAY: return -5;
                }
            }
        }
        return 0;
    }




    public int moveFigure(Coords shiftcoords, ModelBoarder.Direction direction){
        int ret_code = Canmove(this.figure,shiftcoords.x,shiftcoords.y,direction);
        if(ret_code!=0){
            if(ret_code == -2){
                List<Coords> tmp;
                tmp =figure.getDots();
                for(Coords it_tmp:tmp){
                    this.LandedBoxMap[coordsCurFigure.x+it_tmp.x][coordsCurFigure.y+it_tmp.y]=1;
                }
                //this.Score++;
                //eventManager.notify("addScore", this.figure, this.coordsCurFigure,Score,null);
            }
            eventManager.notify("move",this.figure,this.coordsCurFigure,0,null);
            return ret_code;
        }else {
            coordsCurFigure.x += shiftcoords.x;
            coordsCurFigure.y += shiftcoords.y;
        }
        eventManager.notify("move",this.figure,this.coordsCurFigure,0,null);
        return ret_code;
    }

    public void turnFigure(){
        Shape tmp = figure.rotateLeft();
        tmp.setSizeFigure();
        int it =Canmove(tmp,0,0, ModelBoarder.Direction.NOWAY);
        if(it!=0){
            tmp = figure.rotateRight();
        }
        it =Canmove(tmp,0,0, ModelBoarder.Direction.NOWAY);
        if(it!=0) {

            if(it == -5){
                System.out.println('\n');

            }else{
                return;
            }
        }

        this.figure = tmp;
        eventManager.notify("rotate",this.figure,coordsCurFigure,0,null);
    }

}
