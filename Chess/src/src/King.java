public class King extends Piece {
	
	public King(boolean isWhite) {
		super(isWhite); 	
	}
	
	public boolean canMove(Tile start, Tile end) {
		int diffX = Math.abs(start.getX() - end.getX()); 
		int diffY = Math.abs(start.getY() - end.getY()); 
		if (diffX + diffY <= 2 && diffX < 2 && diffY < 2) { // can only move one space in vertical direction or horizontal direction, or one in both directions
			if (end.getPiece() == null || this.getColor() != end.getPiece().getColor()) { // check for empty space or occupied by enemy piece
				for(
			}	
		}
		
	}
	

}
