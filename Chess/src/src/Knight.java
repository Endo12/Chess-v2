package src;

public class Knight extends Piece {

	public Knight(boolean isWhite) {
		super(isWhite, 3); 
	}
	
	public boolean canMove(Tile start, Tile end) {
		int diffX = Math.abs(start.getX() - end.getX()); // requested horizontal distance of move
		int diffY = Math.abs(start.getY() - end.getY()); // requested vertical distance of move
		if ((diffX == 2 && diffY == 1) || (diffY == 2 && diffX == 1)) { // knight can move 2 horizontal and 1 vertical, or 1 horizontal and 2 vertical 
			if (end.getPiece() == null || end.getPiece().getColor() != this.getColor()) { // can move as long as the end tile is empty or has a different color piece
				return true; 
			}
		} 
		return false; 
	}

	@Override
	public String toString() {
		return (this.getColor() ? "W" : "B") + "Kn";
	}
}
