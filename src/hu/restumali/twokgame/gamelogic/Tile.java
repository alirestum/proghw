package hu.restumali.twokgame.gamelogic;

import java.util.concurrent.atomic.AtomicInteger;

public class Tile{

    private int value;
    private String color;

    public Tile(){
        this.value=0;
        this.color="";
    }

    public Tile(int v, String c){
        this.value = v;
        this.color = c;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void squareTile(){
        this.value *= this.value;
    }



}