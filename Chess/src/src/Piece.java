package src;

public abstract class Piece {
	private boolean isWhite, isAlive;
	private int points;
	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
		isAlive = true;
	}
	public abstract boolean canMove(Tile end);
	public boolean moveTo(Tile end) {
		return true;
	}
}
