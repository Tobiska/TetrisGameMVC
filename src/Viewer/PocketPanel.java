package Viewer;

import Model.Coords;

import javax.swing.*;
import java.awt.*;
import Model.Shape;

public class PocketPanel extends JPanel {

    private Box[][] pocketBoxes;
    private JPanel Pocket;
    private Color colorPocket;
    private Shape CatchedFigure;

    public PocketPanel(){
        pocketBoxes = new Box[Configure.POCKETWEIGHT][Configure.POCKETHEIGHT];
        Pocket = new JPanel();
        initPocketBoxes();
        this.add(Pocket);

        this.setMinimumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+50)/3));
        this.setMaximumSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+30)/3));
        this.setPreferredSize(new Dimension(100,(Configure.HEIGHT* Configure.SIZE+30)/3));
        this.setLayout(new GridBagLayout());
        this.setBackground(Configure.BACKPocket);
    }


    public void printFigureInPocket(Shape figure){
        this.CatchedFigure = figure;
        Coords at = new Coords(2,2);
        for(Coords it_coords:CatchedFigure.getDots()){
            setBoxesColor(at.x+it_coords.x,at.y+it_coords.y,CatchedFigure.getShapeColor(),pocketBoxes);
        }
    }

    public Shape deleteFigureFromPocket(){
        for(Coords it_coords:CatchedFigure.getDots()){
            Coords at = new Coords(2,2);
            setBoxesColor(at.x+it_coords.x,at.y+it_coords.y,Configure.BACK,pocketBoxes);
        }
        return CatchedFigure;
    }
    private void initPocketBoxes(){
        for(int y = 0 ; y < 6;y++)
            for(int x = 0; x < 6;x++){
                pocketBoxes[x][y] = new Box(x,y,Configure.SIZEPOCKETBOX);
                Pocket.add(pocketBoxes[x][y]);
            }
        Pocket.setLayout(new GridLayout(6,6));
    }

    private void setBoxesColor(int x,int y,Color color,Box[][] boxes){
        if(x < 0 || x >= Configure.WIDTH)return;
        if(y < 0 || y >= Configure.HEIGHT)return;
        boxes[x][y].setColor(color);
    }

}
