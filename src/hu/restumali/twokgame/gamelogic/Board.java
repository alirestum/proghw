package hu.restumali.twokgame.gamelogic;

public class Board {

    private Tile[][] board= new Tile[4][4];

    public Board(){
        for (int i=0; i<board.length; i++){
            for (int j = 0; j<board[i].length; j++){
                board[i][j] = new Tile(0, "grey");
            }
        }

        board[0][0].setValue(2);
        board[0][1].setValue(2);
        board[0][2].setValue(0);
        board[0][3].setValue(4);
    }

    public void shiftLeft(){
        Tile[][] shiftedBoard = new Tile[4][4];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; ++col) {
                if (board[row][col].getValue() == board[row][(col+1) % board[row].length].getValue()){
                    board[row][col].squareTile();
                    board[row][(col+1) % board[row].length]= new Tile();
                } else if (col != 3 && board[row][(col+1) % board[row].length].getValue() != 0){
                    board[row][col] = board[row][(col+1) % board[row].length];
                    board[row][(col+1) % board[row].length]= new Tile();
                }
            }
        }
       // board = shiftedBoard;
    }

    public void shiftLeftTest(){

    }

    public void printBoard(){
        for (int i=0; i<board.length; i++){
            for (int j = 0; j<board[i].length; j++){
                System.out.print(board[i][j].getValue());
            }
            System.out.println();
        }
    }
}
