package hu.restumali.twokgame.gamelogic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.util.Objects;

public class Tile {

    private static int GLOBAL_ID_COUNTER = 0;
    private final int id = GLOBAL_ID_COUNTER++;

    @JsonProperty
    private TileType type;

    @JsonProperty
    private boolean mergeable;


    public Tile() {
        type = TileType.BLANK;
        this.mergeable = true;
    }

    public Tile(int n) {
        if (n == 2) {
            type = TileType.NUMBER_2;
        } else {
            type = TileType.NUMBER_4;
        }
        mergeable = true;
    }

    @JsonIgnore
    public int getValue() {
        return type.getNumber();
    }

    public boolean getMergeable() {
        return this.mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    @JsonIgnore
    public String getColor() {
        return type.getColor();
    }

    public void mergeTile() {
        type = type.getNext();
        this.mergeable = false;
    }

    @Override
    public String toString() {
        Integer i = this.type.getNumber();
        return i.toString();
    }

    public boolean compare(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return mergeable == tile.mergeable &&
                type == tile.type;
    }

    public void draw(GraphicsContext gc, int x, int y, int spacing) {
        gc.setFill(Paint.valueOf("#786f55"));
        gc.fillRect(x, y, 100, 100);
        gc.setFill(Paint.valueOf(type.getColor()));
        gc.fillRoundRect(x + 5, y + 5, 90, 90, 10, 10);

        if (type.getNumber() != 0) {
            gc.setFill(Color.GREY);
            gc.setFont(new Font("Verdana", 50));
            if (type.getNumber() < 10) {
                gc.fillText(type.toString(), x + spacing, y + spacing * 2);
            } else if (type.getNumber() < 100) {
                gc.fillText(type.toString(), x + 20, y + spacing * 2);
            } else if (type.getNumber() < 1000) {
                gc.fillText(type.toString(), x, y + spacing * 2);
            } else {
                gc.setFont(new Font("Verdana", 36));
                gc.fillText(type.toString(), x + 5, y + spacing + 30);
            }
        }
    }


    public boolean equals(Tile obj) {
        return obj.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, mergeable);
    }
}
