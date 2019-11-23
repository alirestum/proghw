package hu.restumali.twokgame.gamelogic;

public enum TileType {
    NUMBER_2048(2048, "asd", null),
    NUMBER_1024(1024, "asd", NUMBER_2048),
    NUMBER_512(512, "asd", NUMBER_1024),
    NUMBER_256(256, "asd", NUMBER_512),
    NUMBER_128(128, "asd", NUMBER_256),
    NUMBER_64(64, "asd", NUMBER_128),
    NUMBER_32(32, "asd", NUMBER_64),
    NUMBER_16(16, "asd", NUMBER_32),
    NUMBER_8(8, "asd", NUMBER_16),
    NUMBER_4(4, "asd", NUMBER_8),
    NUMBER_2(2, "asd", NUMBER_4),
    BLANK(0, "asd", null){
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

    public String getColor(){
        return this.color;
    }

    public int getNumber() {
        return this.number;
    }

    public TileType getNext() {
        return next;
    }
}
