package src;
public class Bishop extends Piece {

	public Bishop(boolean isWhite) {
		super(isWhite, 3); 
	}
  
	public boolean canMove(Tile start, Tile end) {
    // can only move to a tile with a different color piece or an empty tile (start/end are different) 
		if (end.getPiece() == null || this.getColor() != end.getPiece().getColor() && !start.equals(end)) { 
	    	int diffX = Math.abs(start.getX() - end.getX()), diffY = Math.abs(start.getY() - end.getY()), 
	    			tempX = start.getX(), tempY = start.getY(); 
	    	if (diffX == diffY) { //the proposed move is diagonal, now check for collisions before move can be completed 
	    		Tile[][] tiles = Board.tileBoard; //retrieve tileBoard from the Board class
	    		while (tempX != end.getX() && tempY != end.getY()) { //check each tile in the diagonal for a collision before the end is reached
	    			tempX += start.getX() < end.getX() ? 1 : -1; //changes X for diagonal
	    			tempY += start.getY() < end.getY() ? 1 : -1; //changes Y for diagonal
	    			if (tempX != end.getX() && tempY != end.getY() && tiles[tempY][tempX].getPiece() 
	    					!= null) { /* if we haven't completed our diagonal check and we found a 
	    					piece in the way*/
	    				return false;
	    			}
	    		} // diagonal check completed
	    		return true; 
	    	} 
	    	else { //move not diagonal 
	    		return false;  
	    	}
		} else {
			return false;  
		}  
	}

	
	@Override
	public boolean checkingPiece(Tile start) {
		Board[][] board = Board.tileBoard; 
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
		return (this.getColor() ? "W" : "B") + "B";
	}
}
