package Model;

import GameClass.GameClass;
import Viewer.MenuV;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;




public class Menu {

    private GameClass MVCgame;
    private String UserName;
    private String AboutText;
    private String AboutFileName = "fileAbout.txt";

    public Menu(GameClass game){
        this.MVCgame =game;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName(){
        return UserName;
    }

    public String getAboutText()throws IOException{
        StringBuilder tmp = new StringBuilder();
        FileReader fileAbout = new FileReader(AboutFileName);
        Scanner scan = new Scanner(fileAbout);
        while(scan.hasNextLine()){
            tmp.append(scan.nextLine());
            tmp.append('\n');
        }
        this.AboutText = new String(tmp);
        return AboutText;
    }

}
