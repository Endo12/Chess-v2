package src;

public class Board {
  public Tile[][] tileBoard;
  public static int pointsForWhite = 0; 
  public static int pointsForBlack = 0; 
  
  public Board() {
    for(int i= 0; i < 8; i++) { //create pawns 
      tileBoard[1][i] = new Tile(1, i, new Pawn(true)); 
      tileBoard[6][i] = new Tile(6, i, new Pawn(false)); 
    }
    for(int i = 2; i < 6; i++) { //create empty spaces
       for(int j = 0; j < 8; j++) {
          tileBoard[i][j] = new Tile(i, j, null);  
       }
    }
    tileBoard[0][0] = new Tile(0, 0, new Rook(true)); //back row for white
    tileBoard[0][1] = new Tile(0, 1, new Knight(true));
    tileBoard[0][2] = new Tile(0, 2, new Bishop(true));
    tileBoard[0][3] = new Tile(0, 3, new Queen(true));
    tileBoard[0][4] = new Tile(0, 4, new King(true));
    tileBoard[0][5] = new Tile(0, 5, new Bishop(true));
    tileBoard[0][6] = new Tile(0, 6, new Knight(true));
    tileBoard[0][7] = new Tile(0, 7, new Rook(true));
    tileBoard[0][1] = new Tile(7, 0, new Rook(false));//back row for black
    tileBoard[0][1] = new Tile(7, 1, new Knight(false));
    tileBoard[0][1] = new Tile(7, 2, new Bishop(false));
    tileBoard[0][1] = new Tile(7, 3, new Queen(false));
    tileBoard[0][1] = new Tile(7, 4, new King(false));
    tileBoard[0][1] = new Tile(7, 5, new Bishop(false));
    tileBoard[0][1] = new Tile(7, 6, new Knight(false));
    tileBoard[0][1] = new Tile(7, 7, new Rook(false));
    
    
  }
}
