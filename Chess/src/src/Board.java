package src;

public class Board {
	public static Tile[][] tileBoard = new Tile[8][8];
	public static int pointsForWhite = 0, pointsForBlack = 0; 
	public static boolean whiteAlive = true, blackAlive = true;
  
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
		tileBoard[7][0] = new Tile(7, 0, new Rook(false));//back row for black
		tileBoard[7][1] = new Tile(7, 1, new Knight(false));
		tileBoard[7][2] = new Tile(7, 2, new Bishop(false));
		tileBoard[7][3] = new Tile(7, 3, new Queen(false));
		tileBoard[7][4] = new Tile(7, 4, new King(false));
		tileBoard[7][5] = new Tile(7, 5, new Bishop(false));
		tileBoard[7][6] = new Tile(7, 6, new Knight(false));
		tileBoard[7][7] = new Tile(7, 7, new Rook(false)); 
	}
  
	public static void printBoard() {
		for(int f=0; f<8; f++) {
			for(int g=0; g<8; g++) {
				Piece curr = tileBoard[f][g].getPiece();
				if(curr != null) {
					System.out.print(curr.toString() + " ");
				}
				else {
					System.out.print("NA ");
				}
			}
			System.out.println();
		}
	}
}
