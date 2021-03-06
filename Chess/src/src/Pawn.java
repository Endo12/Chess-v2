package src;

public class Pawn extends Piece {
  
	public Pawn(boolean isWhite){
		super(isWhite, 1); 
	}
  
	public boolean canMove(Tile start, Tile end) {
		if (!start.equals(end)) { //check that start and end are not the same tile 
			if (this.getColor()) { // a forward move for white is +1 in y direction, right is +1 in x
				if (((end.getY() == start.getY() + 2 && start.getY() == 1) || end.getY() == start.getY() + 1)
				    && start.getX() == end.getX() && end.getPiece() == null) { 
					//if forward, it must not occupied by ANY piece
					
					return true; 
				}
				//piece tries forward one, and left one/right one (diagonal)
				else if (end.getPiece() != null && !end.getPiece().getColor() &&
						end.getY() == start.getY() + 1 && 
						(end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1)) { 
					//if diagonal, it must be occupied by black piece
					return true; 
				} 
				else {
					return false; 
				}
			} 
			else { //confirmed black pawn, whose "forward" is -1 in y and whose right is -1 in x 
				if (((end.getY() == start.getY() - 2 && start.getY() == 6) || end.getY() == start.getY() - 1) 
				    && start.getX() == end.getX() && end.getPiece() == null) { 
					//if forward, it must not occupied by ANY piece
					return true; 
				}
				//piece tries forward one, and left one/right one (diagonal)
				else if (end.getPiece() != null && end.getPiece().getColor() &&
						end.getY() == start.getY() - 1 && 
						(end.getX() == start.getX() + 1 || end.getX() == start.getX() - 1)) { 
					//if diagonal, it must be occupied by WHITE piece
					return true; 
				} 
				else {
					return false; 
				}
			}
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
	public boolean canPromote(Tile location) {
		if (this.getColor()) {
			if (location.getY() == 7) {
				return true; 	
			}
		} 
		else {
			if (location.getY() == 0) {
				return true; 	
			}
		}
		return false; 
	}
	
	@Override
	public String toString() {
		return (this.getColor() ? "W" : "B") + "P";
	}
}
