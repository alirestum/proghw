package hu.restumali.twokgame.gamelogic;

import com.fasterxml.jackson.annotation.*;


/**
 * Tile enumeráció. Az egyes Tile-ok lehetséges értékeit tartalmazza. Minden értéknek van saját színe és értéke,
 * ez alapján rajzolódnak ki. Az egyes értékek továbbá tartalmazzák a következő csempét is.
 */
public enum TileType {
    NUMBER_2048(2048, "#D65133", null),
    NUMBER_1024(1024, "#D96045", NUMBER_2048),
    NUMBER_512(512, "#CC674B", NUMBER_1024),
    NUMBER_256(256, "#D5826B", NUMBER_512),
    NUMBER_128(128, "#E3AC9C", NUMBER_256),
    NUMBER_64(64, "#DBB955", NUMBER_128),
    NUMBER_32(32, "#E4CC83", NUMBER_64),
    NUMBER_16(16, "#DFAD81", NUMBER_32),
    NUMBER_8(8, "#E7C3A3", NUMBER_16),
    NUMBER_4(4, "#D8CBB3", NUMBER_8),
    NUMBER_2(2, "#DFD4C0", NUMBER_4),
    BLANK(0, "#F0ECE3", null){
        @Override
        public TileType getNext(){
            return this;
        }
    };


    private final int number;
    private final String color;
    private final TileType next;


    TileType(int number, String color, TileType next) {
        this.number = number;
        this.color = color;
        this.next = next;
    }

    /**
     * Enum color getter
     * @return az adott enum színértéke Stringként.
     */
    public String getColor(){
        return this.color;
    }

    /**
     * Enum szám getter
     * @return az adott enum számértéke intként.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * A játék logikája alapján visszatér a következő értékű csempe típussal.
     * @return A következő csempe típus.
     */
    public TileType getNext() {
        return next;
    }


    /**
     *
     * @return
     */
    @JsonInclude
    @JsonValue
    public String getName() {
        return super.name();
    }

    /**
     * toString függvény felüldefiniálása, hogy az adott típus számát adja vissza Stringként.
     * @return az adott típus száma Stringként.
     */
    @Override
    public String toString() {
        return Integer.toString(number);
    }

}
