package Model;

import GameClass.GameClass;
import Viewer.Configure;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class ModelBoarder {

    private ActionFigure figure;
    private Shape FigureinPocket;
    public EventManager eventManager;
   // private Coords coordsCurFigure;
    private GameClass MVCgame;
    private int[][] LandedBoxMap;
    private int Lines,Score,LastLinesCount = 0;
    private int[] Levels = {0,100,400,800,1200,1500,2000};
    private int CurLevel;
    private static final String FileName = "C:\\Users\\kiril\\IdeaProjects\\TetrisGameMVC\\src\\Model\\RecordsFile.txt";
    private List<Map.Entry<String,Integer>> TableRecords;
    private String name = "Tobiska";

   public enum  Direction{
        DOWN,RIGHT,LEFT,UP,NOWAY;
    }


    public void setUserName(String name){
       name = name;
    }

    public ModelBoarder(GameClass MVCgame){
       this.CurLevel = 0;
        this.LandedBoxMap = new int[Configure.WIDTH][Configure.HEIGHT];
        this.MVCgame = MVCgame;
        for(int i = 0;i<Configure.WIDTH;++i)
            Arrays.fill(LandedBoxMap[i],0);
        this.eventManager =new EventManager("move","rotate","add","deleteFullline",
                "finish","addLines","addScore","LevelUp","catchInPocket","throwFromPocket","printRecords");
        this.figure = new ActionFigure(LandedBoxMap,eventManager);
    }



    public void FillTable() {
        String TableForUI = "";
       String tmp = new String();
        TreeMap<String,Integer> tmpMap = new TreeMap<>();
       String info[] = new String[2];
       try(
        FileReader fileRecords = new FileReader(FileName);
        Scanner scan = new Scanner(fileRecords);) {
           while (scan.hasNextLine()) {
               tmp = scan.nextLine();
               TableForUI+=tmp + '\n';
               info = tmp.split(" ");
               if ((!isDigit(info[1])) || (!isName(info[0]))) {
                   throw new IOException("Invalid Data:((");
               }
               tmpMap.put(info[0], Integer.valueOf(info[1]));
           }
       }catch (IOException e){
           System.out.println(e.getMessage());
       }
        Set<Map.Entry<String,Integer>> tmpSet = tmpMap.entrySet();
        TableRecords = new ArrayList<>(tmpSet);
        Collections.sort(TableRecords,new MyComporator());
        eventManager.notify("printRecords",this.figure.getFigure(),this.figure.getCoordsCurFigure(),0,TableForUI);
    }

    public boolean isName(String str){
       for(int i = 0;i < str.length();i++){
          if(!Character.isLetter(str.charAt(i))){
              return false;
          }
       }
        return true;
    }
    public boolean isDigit(String str){
        for(int i = 0;i < str.length();i++){
            Character ch = str.charAt(i);
            if(!Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }

    public ActionFigure getFigure(){
        return figure;
    }

    public Coords getCoordsCurFigure(){
        return this.figure.getCoordsCurFigure();
    }


    public boolean isStripLine(int check_line){
       //int check_sum = 0;
       for(int i = 0;i<Configure.WIDTH;++i){
           if(LandedBoxMap[i][check_line]!=1)
                return false;
       }
       return true;
    }

    public int tryDelLineANDupLevel(){

       int countDropLines =0;
       for(int itH =Configure.HEIGHT-1;itH>=0;itH--)
           while (isStripLine(itH)) {
               dropLine(itH);
               countDropLines++;
           }
       if(countDropLines > 0) {
          this.Lines+=countDropLines;
          Score +=(countDropLines*100);
           eventManager.notify("deleteFullline", this.figure.getFigure(), this.figure.getCoordsCurFigure(),0,null);
           eventManager.notify("addScore", this.figure.getFigure(), this.figure.getCoordsCurFigure(),Score,null);
           eventManager.notify("addLines", this.figure.getFigure(), this.figure.getCoordsCurFigure(),Lines,null);
           if(checkLevels()){
               eventManager.notify("LevelUp", this.figure.getFigure(), this.figure.getCoordsCurFigure(),CurLevel,null);
               return CurLevel;
           }
       }
       return -1;
    }

    public boolean checkLevels(){
       if(CurLevel != 6) {
           if (Score >= Levels[CurLevel + 1]) {
               CurLevel = CurLevel + 1;
               return true;
           }
       }
      return false;
    }

    public void dropLine(int y){
       for(int my=y-1;my>=0;my--)
           for(int x = 0;x<Configure.WIDTH;x++)
               LandedBoxMap[x][my+1] = LandedBoxMap[x][my];
           for(int x = 0;x<Configure.WIDTH;x++)
               LandedBoxMap[x][0] = 0;
    }

    public int Canmove(Shape figure,int shiftX,int shiftY,Direction direction){
        if(figure.getSizeFigure().sizeYbr+this.figure.getCoordsCurFigure().y +shiftY <0)return -1 ;//move up
        if(figure.getSizeFigure().sizeYtl+this.figure.getCoordsCurFigure().y+shiftY> Configure.HEIGHT-1)return -2; //move down//////////
        if(figure.getSizeFigure().sizeXbr+this.figure.getCoordsCurFigure().x+shiftX < 0)return -3 ;//move left
        if(figure.getSizeFigure().sizeXtl+this.figure.getCoordsCurFigure().x+shiftX > Configure.WIDTH-1)return -4;//move right
        for(Coords it_coords:figure.getDots()) {
            if (LandedBoxMap[this.figure.getCoordsCurFigure().x+it_coords.x+shiftX][this.figure.getCoordsCurFigure().y+it_coords.y+shiftY]==1){
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

    public void catchFigureinPocket(){
       FigureinPocket = figure.getFigure();
       eventManager.notify("catchInPocket",this.FigureinPocket,this.figure.getCoordsCurFigure(),0,null);
    }






    public boolean isGameOver(){
        for(int it= 0;it < Configure.WIDTH;it++){
            if(LandedBoxMap[it][2]==1){
                eventManager.notify("finish",this.figure.getFigure(),this.figure.getCoordsCurFigure(),0,null);
                return true;
            }
        }
        return false;
    }

    public void createRandomFigure(){
        Configure.setDefCoords();
        figure.setFigure(Shape.getRandomFigure(),Configure.defautspawncoords);
        eventManager.notify("add",this.figure.getFigure(),this.figure.getCoordsCurFigure(),0,null);
    }

    public void createSpecificFigure(){
        Configure.setDefCoords();
        figure.setFigure(FigureinPocket,Configure.defautspawncoords);
        eventManager.notify("throwFromPocket",this.figure.getFigure(),this.figure.getCoordsCurFigure(),0,null);
    }

    public void writeResult(){
       TreeMap<String,Integer> tmpMap = new TreeMap<>();
       for(Map.Entry<String,Integer> it:TableRecords){
           tmpMap.put(it.getKey(),it.getValue());
       }

        if(tmpMap.containsKey(name)){
            if(tmpMap.get(name)<Score){
                tmpMap.put(name,Score);
            }
        }else{
            tmpMap.put(name,Score);
        }

        Set<Map.Entry<String,Integer>> tmpSet = tmpMap.entrySet();
        try(
        FileWriter fileRecord = new FileWriter("C:\\Users\\kiril\\IdeaProjects\\TetrisGameMVC\\src\\Model\\RecordsFile.txt",false);
        ) {

            TableRecords = new ArrayList<>(tmpSet);
            Collections.sort(TableRecords, new MyComporator());
            for (int i = 0; i < TableRecords.size(); i++) {
                fileRecord.write(TableRecords.get(i).getKey() + " " + TableRecords.get(i).getValue() + "\n");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
