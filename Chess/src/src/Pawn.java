package src;

public class Pawn extends Piece {
  
	public Pawn(boolean isWhite){
		super(isWhite, 1); 
	}
  
	public boolean canMove(Tile start, Tile end) {
		if (!start.equals(end)) { //check that start and end are not the same tile 
			if (this.getColor()) { // a forward move for white is +1 in y direction, right is +1 in x
				if (end.getY() == start.getY() + 1 && end.getPiece() == null) { //if forward, it must not occupied by ANY piece
					return true; 
				}
				//piece tries forward one, and left one/right one (diagonal)
				else if (end.getY() == start.getY() + 1 && (end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) 
						&& !end.getPiece().getColor()) { //if diagional, it must be occupied by black piece
					return true; 
				} else {
					return false; 
				}
			} 
			else { //confirmed black pawn, whose "forward" is -1 in y and whose right is -1 in x 
				if (end.getY() == start.getY() - 1 && end.getPiece() == null) { //if forward, it must not occupied by ANY piece
					return true; 
				}
				else if (end.getY() == start.getY() - 1 && (end.getX() == start.getX() + 1 || end.getX() == start.getX() - 1) //piece tries forward one, and left one/right one (diagonal)
					&& end.getPiece().getColor()) { 
					//if diagional, it must be occupied by WHITE piece
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
	public String toString() {
		return (this.getColor() ? "W" : "B") + "P";
	}
}
