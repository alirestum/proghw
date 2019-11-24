package hu.restumali.twokgame.gamelogic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Board {

    private Tile[][] grid = new Tile[4][4];
    private int score;

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

    public Tile[][] getGrid() {
        return grid;
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }


    public void setTile(int row, int col, Tile t) {
        grid[row][col] = t;
    }


    public void swapWithZero(int first, int second, int row) {
        grid[row][first] = grid[row][second];
        grid[row][second] = new Tile();
    }

    public void mergeTiles(int first, int second, int row) {
        if (grid[row][first].getMergeable()) {
            score += grid[row][first].getValue();
            grid[row][first].mergeTile();
            grid[row][second] = new Tile();
        }

    }

    public void allowMerge(int row) {
        for (int i = 0; i < grid[row].length; i++) {
            grid[row][i].setMergeable(true);
        }
    }

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

    public boolean shiftable() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            rotateCntClockwise(i);
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length - 1; col++) {
                    if (grid[row][col].getValue() != grid[row][col+1].getValue() && grid[col][row].getValue() !=0)
                        cnt++;
                }
            }
        }
        //rotateCntClockwise(1);
        System.out.println(cnt);
        return cnt != 46;
    }

    public void shiftLeft() {
        if (shiftable()){
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

    public void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getValue());
            }
            System.out.println();
        }
    }

    public void rotateCntClockwise(int times) {
        for (int i = 0; i < times; i++) {
            for (int x = 0; x < 4 / 2; x++) {
                for (int y = x; y < 4 - x - 1; y++) {
                    Tile temp = grid[x][y];
                    grid[x][y] = grid[y][4 - 1 - x];
                    grid[y][4 - 1 - x] = grid[4 - 1 - x][4 - 1 - y];
                    grid[4 - 1 - x][4 - 1 - y] = grid[4 - 1 - y][x];
                    grid[4 - 1 - y][x] = temp;
                }
            }
            // Consider all squares one by one
        }
    }

    public void shiftRight() {
        rotateCntClockwise(2);
        shiftLeft();
        rotateCntClockwise(2);
    }

    public void shiftUp() {
        rotateCntClockwise(1);
        shiftLeft();
        rotateCntClockwise(3);
    }

    public void shiftDown() {
        rotateCntClockwise(3);
        shiftLeft();
        rotateCntClockwise(1);
    }

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

    public boolean gameLost() {
        /*for (int row = 0; row < 4; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;*/
        return !shiftable();
    }

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
