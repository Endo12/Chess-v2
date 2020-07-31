public class Pawn extends Piece {
  public boolean isWhite;
  public int points; 
  
  public Pawn(boolean isWhite, int points){
    super(isWhite, points); 
  }
  
  public boolean canMove(Tile start, Tile end) {
    if (start.piece != null && 
        start.piece.instanceOf(Pawn) == true) { //check that it's occupied and that it's a pawn
      
      
    }
  
  
  
  
  
}
