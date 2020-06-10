import GameClass.GameClass;
import Viewer.EventListener2;

public class HAHA implements EventListener2 {
    GameClass game;
    public void startik(){
        game = new GameClass();
        game.startTetris();
    }

    public void update(String eventType,String name){
        game.initBoarder(name);
    }
}
