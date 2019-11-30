package hu.restumali.twokgame.gamelogic;

import javafx.scene.canvas.GraphicsContext;
import java.util.Random;


/**
 * A Tile-okat tárolja, megvalósítja a játék alapvető logikáját a Tileokon.
 */
public class Board {

    private Tile[][] grid = new Tile[4][4];
    private int score;

    /**
     * Alapértelmezett konstruktor. A Tile tömb inicializálása után hozzáad 2 Tile-t a pálya random helyére.
     */
    public Board() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }
        for (int i = 0; i < 2; i++) {
            int colrand = new Random().nextInt(4);
            int rowrand = new Random().nextInt(4);
            grid[rowrand][colrand] = new Tile(2);
        }
        this.score = 0;
    }


    /**
     * Visszatér a pálya Tile tömbjével.
     *
     * @return A pálya Tile tömbje.
     */
    public Tile[][] getGrid() {
        return grid;
    }


    /**
     * Visszaad egy megadott Tile-t a tömbből.
     *
     * @param row A Tile sorindexe.
     * @param col A Tile oszlopindexe,
     * @return A sor és oszlopindex által kiválaszott Tile.
     */
    public Tile getTile(int row, int col) {
        return grid[row][col];
    }


    /**
     * Beállítja a pálya egyik Tile-ját a megadott Tile-ra.
     *
     * @param row A beállítandó Tile sorindexe.
     * @param col A beállítandó Tile oszlopindexe.
     * @param t   A beállítandó Tile.
     */
    public void setTile(int row, int col, Tile t) {
        grid[row][col] = t;
    }


    /**
     * Athelyez egy adott Tile-t egy olyan helyre ahol 0 van, majd a kicserélt Tile eredeti helyére 0-t tesz.
     *
     * @param first  Az oszlopindex ahova a nem 0 Tile kerül.
     * @param second Az oszlopindex ahova egy új 0 Tile kerül.
     * @param row    A tábla sora amelyiken a műveletet végre kell hajtani.
     */
    public void swapWithZero(int first, int second, int row) {
        grid[row][first] = grid[row][second];
        grid[row][second] = new Tile();
    }

    /**
     * Két Tile-t összevon amennyiben lehetséges, úgy, hogy a játék logikájából adódóan az első értékét duplázza, a másodikét 0-vá teszi. Hozzáadja
     * a Score-hoz az összevonandó Tile értékét.
     *
     * @param first  A duplázandó Tile
     * @param second A másik Tile aminek a helyére 0 Tile kerül.
     * @param row    A tábla sora amelyiken az adott műveletet végre kell hajtani.
     */
    public void mergeTiles(int first, int second, int row) {
        if (grid[row][first].getMergeable()) {
            score += grid[row][first].getValue();
            grid[row][first].mergeTile();
            grid[row][second] = new Tile();
        }

    }

    /**
     * Beállítja a pálya. egyik során a Tile-ok Merge értékét igaz-ra. Tehát a Tile-ok merge-elhetők lesznek.
     *
     * @param row A pálya sora amelyiken a műveletet végre kell hajtani.
     */
    public void allowMerge(int row) {
        for (int i = 0; i < grid[row].length; i++) {
            grid[row][i].setMergeable(true);
        }
    }

    /**
     * Egy adott sorból eltávolítja a 0 Tile-okat, úgy, hogy végeredményként balra shiftelődik az adott sorban az összes nem 0 Tile.
     *
     * @param row Az adott sor amelyen a műveletet végre kell hajtani.
     */
    public void removeZeros(int row) {
        int zerocnt = 0;
        for (int i = 0; i < grid[row].length - 1; i++) {
            if (grid[row][i].getValue() == 0) {
                zerocnt++;
            }
        }
        for (int times = 0; times < zerocnt; times++) {
            for (int i = 0; i < grid[row].length - 1; i++) {
                if (grid[row][i].getValue() == 0) {
                    swapWithZero(i, i + 1, row);
                }
            }
        }
    }

    /**
     * A játék elvesztéséhez szükséges, segédfüggvény. Megnézi, hogy lehet-e még valamilyen irányba shifttelni a pályát.
     *
     * @return A pálya shiftelhetősége.
     */
    public boolean shiftable() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            //    rotateCntClockwise(i);
            rotateCntClockwise(i);
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length - 1; col++) {
                    if (grid[row][col].getValue() != grid[row][col + 1].getValue() && grid[col][row].getValue() != 0)
                        cnt++;
                }
            }
            rotateCntClockwise(4 - i);
        }
        return cnt != 46; //Ha a jatek elvesztese van teszteles alatt ezt 5-re kell allitani a gyorsabb teszteles erdekeben. Egyebkent 46-on kell hagyni.

    }

    /**
     * Megvalósítja a játék logikáját balra. Shifteli a sorokat, ha kell összevonja a Tile-okat.
     */
    public void shiftLeft() {
        if (shiftable()) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length - 1; col++) {
                    removeZeros(row);
                    if (grid[row][col].getValue() == grid[row][col + 1].getValue()) {
                        mergeTiles(col, col + 1, row);
                        removeZeros(row);
                    }
                }
                allowMerge(row);
            }
            addRandomTile();
        }
    }

    /**
     * Konzolra írja ki a pályát egy 4*4 mátrix formájában. Csak az egyes Tile-k értékét írja ki.
     */
    public void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getValue() + "    ");
            }
            System.out.println();
        }
    }

    /**
     * Óra járásával ellentétesen forgatja az egész pályát.
     *
     * @param times Kívánt forgatások száma.
     */
    public void rotateCntClockwise(int times) {
        // Tile[][] ret = new Tile[grid.length][grid.length];
        for (int time = 0; time < times; time++) {
            for (int x = 0; x < 4 / 2; x++) {
                for (int y = x; y < 4 - x - 1; y++) {
                    Tile temp = grid[x][y];
                    grid[x][y] = grid[y][4 - 1 - x];
                    grid[y][4 - 1 - x] = grid[4 - 1 - x][4 - 1 - y];
                    grid[4 - 1 - x][4 - 1 - y] = grid[4 - 1 - y][x];
                    grid[4 - 1 - y][x] = temp;
                }
            }
           /* int size = grid.length;

            for (int i = 0; i < size; ++i)
                for (int j = 0; j < size; ++j)
                    ret[i][j] = grid[j][size - i - 1]; //***
            // Consider all squares one by one
        }
        if (ret[0][0] != null)
        {
            this.grid = ret;
        }*/
        }

    }

    /**
     * Megvalósítja a játék logikáját jobbra. Mivel a shiftelés csak bal irányba van megvalósítva,
     * ezért a jobbra shiftelést balra beforgatással majd balra shifteléssel éri el. Ezután visszaforgatja a helyére a pályát.
     */
    public void shiftRight() {
        rotateCntClockwise(2);
        shiftLeft();
        rotateCntClockwise(2);
    }

    /**
     * Felfele shifteli a pályát a jobbrashifthez hasonló módon.
     */
    public void shiftUp() {
        rotateCntClockwise(1);
        shiftLeft();
        rotateCntClockwise(3);
    }

    /**
     * Lefele shifteli a pályát a jobbrashifthez hasonló módon.
     */
    public void shiftDown() {
        rotateCntClockwise(3);
        shiftLeft();
        rotateCntClockwise(1);
    }

    /**
     * Hozzáad a pályához egy random Tile-t, egy random helyre, ami még nem foglalt.
     */
    public void addRandomTile() {
        int random = new Random().nextInt(11);
        Tile t = null;
        if (random % 2 == 0) {
            t = new Tile(2);
        } else if (random % 4 == 0) {
            t = new Tile(4);
        } else {
            t = new Tile(2);
        }
        boolean success = false;
        while (!success) {
            int colrand = new Random().nextInt(4);
            int rowrand = new Random().nextInt(4);
            if (grid[rowrand][colrand].getValue() == 0) {
                grid[rowrand][colrand] = t;
                success = true;
            }
        }
    }

    /**
     * Megnézi, hogy lehet e még valamerre shiftelni, amennyiben nem a játékos elveszítette a játékot.
     *
     * @return Igaz, ha a játékos elveszítette a játékot.
     */
    public boolean gameLost() {
        return !shiftable();
    }

    /**
     * Megnézi hogy a pályán szerepel-e a 2048-as Tile. Ha igen a játékos megnyerte a játékot.
     *
     * @return Igaz, ha a játékos megnyerte a játékot.
     */
    public boolean gameWon() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Kirajzolja a pályát Tile-onként.
     *
     * @param gc A grafikus hely ahova ki kell rajzolni a pályát.
     */
    public void draw(GraphicsContext gc) {

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                grid[row][col].draw(gc, col * 100, row * 100, 35);
            }
        }
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
