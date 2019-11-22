package hu.restumali.twokgame.gamelogic;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Tile{

    private TileType type;
    private boolean mergeable;


    public Tile(){
        type = TileType.BLANK;
        this.mergeable= true;
    }

    public Tile(int n){
        if (n == 2){
            type = TileType.NUMBER_2;
        } else{
            type = TileType.NUMBER_4;
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
        this.mergeable = false;
    }

    @Override
    public String toString() {
        Integer i = this.type.getNumber();
        return i.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return mergeable == tile.mergeable &&
                type == tile.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mergeable);
    }
}
