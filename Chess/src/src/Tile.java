package src;

public class Tile {
	private int xPos;
	 private int yPos;
	 private Piece piece;
	 public Tile(int xPos, int yPos, Piece piece){
	   this.xPos = xPos;
	   this.yPos = yPos;
	   this.piece = piece;
	 }
	 public void setX(int xPos){
	   this.xPos = xPos;
	 }
	 public void set(int yPos){
	   this.yPos = yPos;
	 }
	 public int getX(){
	   return this.xPos;
	 }
	 public int getY(){
	   return this.yPos;
	 }
	 public void setPiece(Piece piece){
	   this.piece = piece;
	 }
	 public Piece getPiece(){
	   return this.piece;
	 }
}
