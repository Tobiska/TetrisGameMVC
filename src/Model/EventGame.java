package Model;

import Viewer.EventListener;
import Viewer.EventListener2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventGame {
    private Map<String , List<EventListener2>> listeners = new HashMap<>();


    public EventGame(String ... operations){
        for(String it_op:operations)
            this.listeners.put(it_op,new ArrayList<>());
    }


    public void register(String eventType,EventListener2 listener){
        List<EventListener2> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unregister(String eventType,EventListener2 listener){
        List<EventListener2> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType,String name){
        List<EventListener2> users =listeners.get(eventType);
        for(EventListener2 listener :users){
            listener.update(eventType,name);
        }
    }
}
