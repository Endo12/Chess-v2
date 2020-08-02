package src;
import java.lang.Math.abs;
public class Bishop extends Piece {

  public Bishop(boolean isWhite, int points) {
    super(isWhite, points); 
  }
  
  public boolean canMove(Tile start, Tile end) {
     // checklist: different start/end, that the move is "diagonal", that it avoids collisions along the way, and that when it does collide, it's w/ opposite color
    boolean thisWhite = this.getColor(); 
    boolean moveIsDiagonal = true; 
    boolean avoidsCollisions = true; 
    
    if (!end.getPiece() || this.getColor != end.getPiece().getColor() && start != end) { // can only move to a tile with a different color piece or an empty tile (start/end are different) 
      int diffX = Math.abs(start.getX(), end.getX()); 
      int diffY = Math.abs(start.getY(), end.getY()); 
      int tempX = start.getX(); //used to check 
      int tempY = start.getX(); 
      if (diffX == diffY) { //the proposed move is diagonal, now check for collisions before move can be completed 
         while (tempX != end.getX() && tempY != end.getY()) { //check each tile in the diagonal for a collision before the end is reached
           
           Tile[][] tiles = Board.tileBoard; //retrieve tileBoard from the Board class
           
           if (start.getX() < end.getX()) { //we know we increment x for diagonal
            tempX++; 
           } else { //we decrement x for diagonal
              tempX--;  
           }
          if (start.getY() < end.getY()) { //we know we inrement y for diagonal 
            tempY++;  
          } else { //we decrement y for diagonal 
            tempY--;  
          }
           if (tempX != end.getX() && tempY != end.getY() && tiles[tempX][tempY].getPiece() != null) { // if we haven't completed our diagonal check and we found a piece in the way
             return false;
           }
         } // diagonal check completed
-        return true; 
      } else { //move not diagonal 
        return false;  
      }
    } else {
      return false;  
    }
    
    
    
    
  }
}
