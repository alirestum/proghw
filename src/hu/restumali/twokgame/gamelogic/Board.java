package hu.restumali.twokgame.gamelogic;

public class Board {




    private Tile[][] grid = new Tile[4][4];

    public Board(){
        for (int i = 0; i< grid.length; i++){
            for (int j = 0; j< grid[i].length; j++){
                grid[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public Tile getTile(int row, int col){
        return grid[row][col];
    }


    public void setTile(int row, int col, Tile t){
        grid[row][col] = t;
    }


    public void swapWithZero(int first, int second, int row){
        grid[row][first] = grid[row][second];
        grid[row][second] = new Tile();
    }

    public void mergeTiles(int first, int second, int row){
        grid[row][first].mergeTile();
        grid[row][second] = new Tile();
    }




    public void shiftLeft(){
        Tile[][] shiftedBoard = new Tile[4][4];

       for (int row = 0; row< grid.length; row++){
           for (int col = 1; col< grid[row].length; col++){
               if (grid[row][0].getValue() == 0){
                   grid[row][col-1] = grid[row][col];
                   grid[row][col] = new Tile();
                   for (int i = col; i< grid[row].length; i++){
                       if (grid[row][i].getValue() != 0){
                           grid[row][col]= grid[row][i];
                           grid[row][i] = new Tile();
                           break;
                       }
                   }
                   if(grid[row][col].getValue() == grid[row][col-1].getValue() && grid[row][col-1].getMergeable()){
                       grid[row][col-1].mergeTile();
                       grid[row][col] = new Tile();
                       for (int i = col; i< grid[row].length; i++){
                           if (grid[row][i].getValue() != 0){
                               grid[row][col]= grid[row][i];
                               grid[row][i] = new Tile();
                               break;
                           }
                       }
                   }
               } else {
                   if(grid[row][col].getValue() == grid[row][col-1].getValue() && grid[row][col-1].getMergeable()){
                       grid[row][col-1].mergeTile();
                       grid[row][col] = new Tile();
                       for (int i = col; i< grid[row].length; i++){
                           if (grid[row][i].getValue() != 0){
                               grid[row][col]= grid[row][i];
                               grid[row][i] = new Tile();
                               break;
                           }
                       }
                   }
                   for (int i = col; i< grid[row].length; i++){
                       if (grid[row][i].getValue() != 0){
                           grid[row][col]= grid[row][i];
                           grid[row][i] = new Tile();
                           break;
                       }
                   }
                   if(grid[row][col].getValue() == grid[row][col-1].getValue() && grid[row][col-1].getMergeable()){
                       grid[row][col-1].mergeTile();
                       grid[row][col] = new Tile();
                       for (int i = col; i< grid[row].length; i++){
                           if (grid[row][i].getValue() != 0){
                               grid[row][col]= grid[row][i];
                               grid[row][i] = new Tile();
                               break;
                           }
                       }
                   }
               }
           }
       }
       // board = shiftedBoard;
    }



    public void printBoard(){
        for (int i = 0; i< grid.length; i++){
            for (int j = 0; j< grid[i].length; j++){
                System.out.print(grid[i][j].getValue());
            }
            System.out.println();
        }
    }

}
