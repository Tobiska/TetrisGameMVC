package Viewer;

import javax.swing.*;
import java.awt.*;

import GameClass.GameClass;
import Model.Coords;
import Model.Shape;


public class Boarder extends JFrame implements EventListener,Runnable {


    private Shape figure;
    private Box[][] boxes;
    private JPanel boxPanel;
    private StatisticPanel statsPanel;
    private Coords curcoords;
    private GameClass MVCgame;
    public Boarder(GameClass MVCgame){
        this.MVCgame =MVCgame;
        boxPanel = new JPanel();
        curcoords = new Coords(0,0);
        boxes = new Box[Configure.WIDTH][Configure.HEIGHT];
        addKeyListener(MVCgame.getControllerTetris());
        initForm();
        initBoxes();
        initStats();
        pack();
        setVisible(true);
        repaint();
    }


    public void initStats(){
        statsPanel = new StatisticPanel();
        add(statsPanel);
    }




    @Override
    public void update(String event,Shape figure,Coords curcoords,int countInfo,String Table){
        switch (event){
            case "move": moveFigure(figure,curcoords);  break;
            case "rotate": turnFigureW(figure,curcoords); break;
            case "add": addFigure(figure,curcoords); break;
            case "deleteFullline": deleteLine();break;
            case "finish": printGameOver();break;
            case "addLines": statsPanel.getCurStatPanel().ChangeInfoLines(countInfo);break;
            case "addScore": statsPanel.getCurStatPanel().ChangeInfoScore(countInfo);break;
            case "LevelUp": statsPanel.getCurStatPanel().ChangeInfoLevel(countInfo);break;
            case "catchInPocket": hideFigureShape(figure,curcoords);statsPanel.getPocketPanel().printFigureInPocket(figure); break;
            case "throwFromPocket": addFigure(statsPanel.getPocketPanel().deleteFigureFromPocket(),curcoords); break;
            case "printRecords":statsPanel.getRecordsPanel().printTableRecords(Table); break;
            default:
                break;
        }
    }

    private void initForm(){
        setSize(Configure.WIDTH* Configure.SIZE+100, Configure.HEIGHT* Configure.SIZE+15);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setTitle("Tetris");
        setVisible(true);
    }

    private void initBoxes(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 120;
        c.ipady = 300;
        for(int y = 0 ; y < Configure.HEIGHT;y++)
            for(int x = 0; x < Configure.WIDTH;x++){
                boxes[x][y] = new Box(x,y,Configure.SIZE);
             boxPanel.add(boxes[x][y]);
            }
        boxPanel.setLayout(new GridLayout(Configure.HEIGHT,Configure.WIDTH));
       add(boxPanel,c);

    }


    public void addFigure(Shape figure,Coords curcoords){
        this.figure = figure;
        this.curcoords.x = curcoords.x;
        this.curcoords.y =curcoords.y;
        showFigure(this.figure,this.curcoords);
         repaint();
    }

    public void run(){
        this.repaint();
    }


    public void moveFigure(Shape figure,Coords curcoords){
            hideFigureShape(this.figure, this.curcoords);
            this.figure =figure;
            this.curcoords.x = curcoords.x;
            this.curcoords.y =curcoords.y;
            showFigure(this.figure, this.curcoords);
         repaint();
    }

    public void turnFigureW(Shape figure,Coords curcoords){
        hideFigureShape(this.figure,this.curcoords);
        this.curcoords.x = curcoords.x;
        this.curcoords.y =curcoords.y;
        showFigure(this.figure,this.curcoords);
        repaint();
    }


    public void printGameOver(){
        JPanel SadPanel = new JPanel();
        Color colorbackSadPanel = new Color(2,86,105,2);
        SadPanel.setLayout(null);
        SadPanel.setSize(Configure.WIDTH* Configure.SIZE+15, Configure.HEIGHT* Configure.SIZE+15);
        SadPanel.setBackground(colorbackSadPanel);
        JLabel SadLabel = new JLabel("Game Over");
        SadPanel.add(SadLabel);
        this.add(SadPanel);
        SadPanel.setVisible(true);
    }

    public boolean isStripLine(int check_line){
        for(int i = 0;i<Configure.WIDTH;++i){
            if(boxes[i][check_line].getBackground()== Configure.BACK)
                return false;
        }
        return true;
    }


    public void deleteLine(){
        for(int itH = Configure.HEIGHT-1;itH >=0;itH--)
            while(isStripLine(itH))
                dropLine(itH);

    }

   public void dropLine(int y){
        for(int my = y-1;my>=0;my--)
            for(int x = 0;x < Configure.WIDTH;x++)
                setBoxesColor(x,my+1,boxes[x][my].getBackground(),boxes);
        for(int x = 0;x < Configure.WIDTH;x++)
            setBoxesColor(x,0,Configure.BACK,boxes);
   }


    private void showFigure(Shape figure,Coords at){
        for(Coords it_coords:figure.getDots()){
            setBoxesColor(at.x+it_coords.x,at.y+it_coords.y,figure.getShapeColor(),boxes);
        }
    }

    private void hideFigureShape(Shape figure,Coords at){
        for(Coords it_coords:figure.getDots()){
            setBoxesColor(at.x+it_coords.x,at.y+it_coords.y,Configure.BACK,boxes);
        }
    }

    private void setBoxesColor(int x,int y,Color color,Box[][] boxes){
        if(x < 0 || x >= Configure.WIDTH)return;
        if(y < 0 || y >= Configure.HEIGHT)return;
        boxes[x][y].setColor(color);
    }









}
