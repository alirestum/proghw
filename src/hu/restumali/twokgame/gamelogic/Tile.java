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

/**
 * A játékban ilyen Tile objektumokból áll. TileType enumáricót tartalmaznak, így mindegyiknek van száma színe és következő értéke.
 * Az osztály továbbá tárol egy bool tagváltozót ami azt mondja meg, hogy az adott lépésben növelhető-e az adott Tile.
 */
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

    /**
     * Konstruktor. Kettes vagy négyes csempét generál.
     * @param n Véletlenszerű szám amely alapján eldönti, hogy kettes vagy négyes legyen.
     */
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

    /**
     * toString fgv. felüldefiniálás, hogy a Tile számértékét adja vissza.
     * @return A Tile számértéke Stringként.
     */
    @Override
    public String toString() {
        return Integer.toString(type.getNumber());
    }

    public boolean compare(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return mergeable == tile.mergeable &&
                type == tile.type;
    }

    /**
     * Kirajzolja a Tile-t a megadott grafikai helyre. A Tile vizuális megjelenése változik az értéke alapján.
     * @param gc A grafikus hely ahova ki kell rajzolni.
     * @param x  A kirajzolás helyének x pozíciója.
     * @param y  A kirajzolás helyének y pozíciója.
     * @param spacing Két Tile között kihagyandó hely.
     */
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
