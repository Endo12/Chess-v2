package src;

public class Tile {
	private final int XPOS;
	private final int YPOS;
	private Piece piece;
	public Tile(int YPOS, int XPOS, Piece piece){
		this.XPOS = XPOS;
		this.YPOS = YPOS;
		this.piece = piece;
	}
	public int getX(){
		return this.XPOS;
	}
	public int getY(){
		return this.YPOS;
	}
	public void setPiece(Piece piece){
		this.piece = piece;
	}
	public Piece getPiece(){
		return this.piece;
	}
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		else if(!(o instanceof Tile)) {
			return false;
		}
		else {
			Tile t = (Tile) o;
			return XPOS == t.getX() && YPOS == t.getY() && piece == t.getPiece();
		}
	}
}
