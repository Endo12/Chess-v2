package src;

public class Tile {
	private final int XPOS;
	private final int YPOS;
	private Piece piece;
	public Tile(int XPOS, int YPOS, Piece piece){
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
}
