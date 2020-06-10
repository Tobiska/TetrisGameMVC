package Viewer;

import Model.Coords;
import Model.Shape;

public interface EventListener {
    void update(String eventType, Shape figure, Coords coords,int countDropLines,String Table);
}
