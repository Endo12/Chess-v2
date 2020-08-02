public class Pawn extends Piece {
  
  public Pawn(boolean isWhite, int points){
    super(isWhite, points); 
  }
  
  public boolean canMove(Tile start, Tile end) {
    if (start.piece != null && 
        start.getPiece() instanceof Pawn && end.getX() != start.getX() { //check that it's occupied and that it's a pawn, and that the "end" requires movement
      
      if (isWhite) { // a forward move for white is +1 in y direction, right is +1 in x
        if (end.getY() == start.getY() + 1 && end.piece == null) { //if forward, it must not occupied by ANY piece
          return true; 
        }
        else if (end.getY() == start.getY() + 1 && (end.getX() == start.getX() - 1 || end.getX() == start.getX() + 1) //piece tries forward one, and left one/right one (diagonal)
               && !end.piece.getColor()) { //if diagional, it must be occupied by black piece
          return true; 
        } else {
          return false; 
        }
      } else { //confirmed black pawn, whose "forward" is -1 in y and whose right is -1 in x 
        if (end.getY() == start.getY() - 1 && end.piece == null) { //if forward, it must not occupied by ANY piece
          return true; 
        }
        else if (end.getY() == start.getY() - 1 && (end.getX() == start.getX() + 1 || end.getX() == start.getX() - 1) //piece tries forward one, and left one/right one (diagonal)
               && end.piece.getColor()) { //if diagional, it must be occupied by WHITE piece
          return true; 
        } else {
          return false; 
        }
      
      }
   } else {
         return false;  
      }
  
  
  
  
  
}
