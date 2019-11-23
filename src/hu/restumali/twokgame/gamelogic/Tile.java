package hu.restumali.twokgame.gamelogic;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Tile extends Label {

    private static int GLOBAL_ID_COUNTER = 0;
    private final int id = GLOBAL_ID_COUNTER++;
    private TileType type;
    private boolean mergeable;


    public Tile(){
        type = TileType.BLANK;
        this.mergeable= true;
        this.setText(type.toString());
    }

    public Tile(int n){
        if (n == 2){
            type = TileType.NUMBER_2;
            setText(type.toString());
        } else{
            type = TileType.NUMBER_4;
            setText(type.toString());
        }
        mergeable = true;
    }

    public int getValue() {
        return type.getNumber();
    }

    public boolean getMergeable() {return this.mergeable;}

    public void setMergeable(boolean mergeable) { this.mergeable = mergeable;}

    public String getColor() {
        return type.getColor();
    }

    public void mergeTile(){
        type = type.getNext();
        setText(type.toString());
        this.mergeable = false;
    }

    @Override
    public String toString() {
        Integer i = this.type.getNumber();
        return i.toString();
    }


    public boolean compare(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return mergeable == tile.mergeable &&
                type == tile.type;
    }


    public boolean equals(Tile obj) {
        return obj.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mergeable);
    }
}
