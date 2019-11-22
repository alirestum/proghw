package hu.restumali.twokgame.gamelogic;

public enum TileType {
    NUMBER_2(2, "asd"), NUMBER_4(4, "asd"), NUMBER_8(8, "asd"), NUMBER_16(16, "asd"),
    NUMBER_32(32, "asd"), NUMBER_64(64, "asd"), NUMBER_128(128, "asd"), NUMBER_256(256, "asd"),
    NUMBER_512(512, "asd"), NUMBER_1024(1024, "asd"), NUMBER_2048(2048, "asd"), BLANK(0, "asd");

    private final int number;
    private final String color;

    TileType(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    public int getNumber() {
        return this.number;
    }
}
