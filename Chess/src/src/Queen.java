package src;

public class Queen extends Piece {

	public Queen(boolean isWhite) {
		super(isWhite, 9);
	}

	@Override
	public boolean canMove(Tile start, Tile end) {
		Piece destPiece = end.getPiece();
		/*Checks to make sure tiles are different, and that the queen wouldn't be capturing a piece of
		her own color*/
		if(!start.equals(end) && (destPiece == null || destPiece.getColor() != this.getColor()) ) {
			Tile boardArr[][] = Board.tileBoard;
			int currX = start.getX(), endX = end.getX(), currY = start.getY(), endY = end.getY(), 
					diffX = Math.abs(start.getX() - end.getX()), diffY = Math.abs(start.getY() - end.getY()); 
			if(diffX != 0 && diffY == 0) { /*Cycles through X positions and returns false if there's 
				any collisions*/
				for(int f=Math.min(currX, endX) + 1; f<Math.max(currX, endX); f++) {
					if(boardArr[currY][f].getPiece() != null) {
						return false;
					}
				}
			}
			else if(diffX == 0 && diffY != 0) { /*Same as above, but with Y positions*/
				for(int f=Math.min(currY, endY) + 1; f<Math.max(currY, endY); f++) {
					if(boardArr[f][currX].getPiece() != null) {
						return false;
					}
				}
			}
			else if(diffX == diffY) { /*the proposed move is diagonal, now check for collisions before 
				move can be completed*/
	    		while (currX != end.getX() && currY != end.getY()) { /*check each tile in the diagonal 
	    			for a collision before the end is reached*/
	    			currX += start.getX() < end.getX() ? 1 : -1; //changes X for diagonal
	    			currY += start.getY() < end.getY() ? 1 : -1; //changes Y for diagonal
	    			if (currX != end.getX() && currY != end.getY() && boardArr[currY][currX].getPiece() 
	    					!= null) { /* if we haven't completed our diagonal check and we found a 
	    					piece in the way*/
	    				return false;
	    			}
	    		} // diagonal check completed
			}
			else { //proposed move is neither straight nor diagonal
				return false;
			}
	    	return true; 
		}
		else {
			return false;
		}
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
		return (this.getColor() ? "W" : "B") + "Q";
	}
}
