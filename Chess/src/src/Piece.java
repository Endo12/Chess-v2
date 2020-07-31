package src;

public abstract class Piece {
	private boolean isWhite, isAlive; //maybe get rid of isAlive?
	private int points;
	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
		isAlive = true;
	}
	public abstract boolean canMove(Tile start, Tile end);
	public boolean moveTo(Tile start, Tile end) { //doesn't work for castling
		if(canMove(start, end)) {
			Piece captured = end.getPiece();
			if(captured != null) {
				captured.isAlive = false;
				//Code to send points to main
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
}
