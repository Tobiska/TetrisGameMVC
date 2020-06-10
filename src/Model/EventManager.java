package Model;

import java.util.*;
import Viewer.EventListener;

public class EventManager {
    private Map<String ,List<EventListener>> listeners = new HashMap<>();


    public EventManager(String ... operations){
        for(String it_op:operations)
            this.listeners.put(it_op,new ArrayList<>());
    }


    public void register(String eventType,EventListener listener){
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unregister(String eventType,EventListener listener){
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType,Shape figure,Coords curcoords,int countDropLines,String Table){
        List<EventListener> users =listeners.get(eventType);
        for(EventListener listener :users){
            listener.update(eventType,figure,curcoords,countDropLines,Table);
        }
    }


}
