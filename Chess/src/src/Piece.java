package src;

public abstract class Piece {
	private boolean isWhite, isAlive; //maybe get rid of isAlive?
	private int points;
	public Piece(boolean isWhite, int points) {
		this.isWhite = isWhite;
		isAlive = true;
		this.points = points;
	}
	public abstract boolean canMove(Tile start, Tile end);
	public boolean moveTo(Tile start, Tile end) { //doesn't work for castling
		if(canMove(start, end)) {
			Piece captured = end.getPiece();
			if(captured != null) {
				captured.isAlive = false;
				if(captured.isKing()) {
					if(captured.getColor()) {
						Board.whiteAlive = false;
					}
					else {
						Board.blackAlive = false;
					}
				}
				else {
					if(captured.getColor()) {
						Board.pointsForBlack += captured.points;
					}
					else {
						Board.pointsForWhite += captured.points;
					}
				}
				captured = null;
			}
			end.setPiece(this);
			start.setPiece(null);
			return true;
		}
		else {
			return false;
		}
	}
	public boolean getColor() {
		return isWhite;
	}
	public boolean isKing() {
		return false;
	}
	public abstract String toString();
}
