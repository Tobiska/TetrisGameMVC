package Model;

import java.util.Comparator;
import java.util.Map;

public class MyComporator implements Comparator<Map.Entry<String,Integer>> {
    @Override
    public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2){
        return (e2.getValue().compareTo(e1.getValue()));
    }
}

