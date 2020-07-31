public class Pawn extends Piece {
  
  public Pawn(boolean isWhite, int points){
    super(isWhite, points); 
  }
  
  public boolean canMove(Tile start, Tile end) {
    if (start.piece != null && 
        start.getPiece() instanceof Pawn && !(end.getX() == start.getX()) { //check that it's occupied and that it's a pawn
      if (end.getY() == start.getY() + 1 && end.piece == null) { //if forward, not occupied
      else if (end.getY() == start.getY() + 1 && end.getX() == start.getX() + 1 && end.piece != null) {
        
      }
      
    }
  
  
  
  
  
}
