package src;

public class Rook extends Piece {

	private boolean castleCheck;
	
	public Rook(boolean isWhite) {
		super(isWhite, 5);
		castleCheck = true;
	}

	@Override
	public boolean canMove(Tile start, Tile end) {
		Piece destPiece = end.getPiece();
		int startX = start.getX(), endX = end.getX(), startY = start.getY(), endY = end.getY();
		boolean diffX = startX != endX, diffY = startY != endY;
		if((destPiece != null && destPiece.getColor() == this.getColor()) || start.equals(end) || 
				diffX && diffY) { /*Condition ensures that the piece wouldn't be capturing another 
				piece on its team, that the 2 tiles are different, and that the 2 tiles share a row 
				or column*/
			return false;
		}
		Tile boardArr[][] = Board.tileBoard;
		if(diffX) { /*Cycles through X positions and returns false if there's any collisions*/
			for(int f=Math.min(startX, endX) + 1; f<Math.max(startX, endX); f++) {
				if(boardArr[startY][f].getPiece() != null) {
					return false;
				}
			}
		}
		else { /*Same as above, but with Y positions*/
			for(int f=Math.min(startY, endY) + 1; f<Math.max(startY, endY); f++) {
				if(boardArr[f][startX].getPiece() != null) {
					return false;
				}
			}
		}
		if(castleCheck) {
			if(this.getColor()) {
				if(start.getX() == 0) {
					Board.whiteQCastle = false;
				}
				else {
					Board.whiteKCastle = false;
				}
			}
			else {
				if(start.getX() == 0) {
					Board.blackQCastle = false;
				}
				else {
					Board.blackKCastle = false;
				}
			}
			castleCheck = false;
		}
		return true;
	}
	
	@Override
	public boolean checkingPiece(Tile start) {
		Tile[][] board = Board.tileBoard; 
			for(Tile[] row: board) {
				for(Tile t: row) {
					if (t.getPiece() != null && t.getPiece() instanceof King && t.getPiece().getColor() != this.getColor()) { //its an enemy king
						if (canMove(start, t)) {
							return true;	
						}
						return false;
					}
				}
			}
		return false; 	
	}
	

	@Override
	public String toString() {
		return (this.getColor() ? "W" : "B") + "R";
	}

}
