package hu.restumali.twokgame.gamelogic;

public class Board {

    private Tile[][] grid = new Tile[4][4];

    public Board() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }
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
            grid[row][first].mergeTile();
            grid[row][second] = new Tile();
        }

    }

    public void AllowMerge(int row) {
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


    public void shiftLeft() {
        Tile[][] shiftedBoard = new Tile[4][4];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length - 1; col++) {
                removeZeros(row);
                if (grid[row][col].getValue() == grid[row][col + 1].getValue()) {
                    mergeTiles(col, col + 1, row);
                    removeZeros(row);
                }
            }
            AllowMerge(row);
        }
      /*  for (int row = 0; row < grid.length; row++) {
            for (int col = 1; col < grid[row].length; col++) {
                if (grid[row][0].getValue() == 0) {
                    //grid[row][col-1] = grid[row][col];
                    //grid[row][col] = new Tile();

                    //  removeZeros(col-1,row);

                    if (grid[row][col].getValue() == grid[row][col - 1].getValue() && grid[row][col - 1].getMergeable()) {
                        mergeTiles(col - 1, col, row);
                        //     removeZeros(col, row);
                    }
                } else {
                    if (grid[row][col].getValue() == grid[row][col - 1].getValue() && grid[row][col - 1].getMergeable()) {
                        mergeTiles(col - 1, col, row);
                        //  removeZeros(col, row);
                    }
                    // for (int i = col; i< grid[row].length; i++){
                    //     if (grid[row][i].getValue() != 0){
                    //         grid[row][col]= grid[row][i];
                    //         grid[row][i] = new Tile();
                    //         break;
                    //     }
                    // }
                    // if(grid[row][col].getValue() == grid[row][col-1].getValue() && grid[row][col-1].getMergeable()){
                    //     grid[row][col-1].mergeTile();
                    //     grid[row][col] = new Tile();
                    //     for (int i = col; i< grid[row].length; i++){
                    //         if (grid[row][i].getValue() != 0){
                    //             grid[row][col]= grid[row][i];
                    //             grid[row][i] = new Tile();
                    //             break;
                    //         }
                    //     }
                    // }
                }
            }
        } */
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
                // Consider elements in group of 4 in
                // current square
                for (int y = x; y < 4 - x - 1; y++) {
                    // store current cell in temp variable
                    Tile temp = grid[x][y];

                    // move values from right to top
                    grid[x][y] = grid[y][4 - 1 - x];

                    // move values from bottom to right
                    grid[y][4 - 1 - x] = grid[4 - 1 - x][4 - 1 - y];

                    // move values from left to bottom
                    grid[4 - 1 - x][4 - 1 - y] = grid[4 - 1 - y][x];

                    // assign temp to left
                    grid[4 - 1 - y][x] = temp;
                }
            }
            // Consider all squares one by one
        }
    }

    public void shiftRight(){
        rotateCntClockwise(2);
        shiftLeft();
        rotateCntClockwise(2);
    }

    public void shiftUp(){
        rotateCntClockwise(1);
        shiftLeft();
        rotateCntClockwise(3);
    }

    public void shiftDown(){
        rotateCntClockwise(3);
        shiftLeft();
        rotateCntClockwise(1);
    }


}
