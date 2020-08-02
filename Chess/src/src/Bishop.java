package src;
public class Bishop extends Piece {

  public Bishop(boolean isWhite, int points) {
    super(isWhite, points); 
  }
  
  public boolean canMove(Tile start, Tile end) {
    // can only move to a tile with a different color piece or an empty tile (start/end are different) 
    if (end.getPiece() == null || this.getColor() != end.getPiece().getColor() && !start.equals(end)) { 
    	int diffX = Math.abs(start.getX() - end.getX()); 
    	int diffY = Math.abs(start.getY() - end.getY()); 
        int tempX = start.getX(); //used to check 
        int tempY = start.getY(); 
        if (diffX == diffY) { //the proposed move is diagonal, now check for collisions before move can be completed 
        	Tile[][] tiles = Board.tileBoard; //retrieve tileBoard from the Board class
    		while (tempX != end.getX() && tempY != end.getY()) { //check each tile in the diagonal for a collision before the end is reached
        		if (start.getX() < end.getX()) { //we know we increment x for diagonal
            		tempX++; 
           		} else { //we decrement x for diagonal
            		tempX--;  
           		}
          		if (start.getY() < end.getY()) { //we know we increment y for diagonal 
           	 		tempY++;  
          		} else { //we decrement y for diagonal 
            		tempY--;  
          		}
				// if we haven't completed our diagonal check and we found a piece in the way
           		if (tempX != end.getX() && tempY != end.getY() && tiles[tempY][tempX].getPiece() != null) { 
             		return false;
           		}
         	} // diagonal check completed
      		return true; 
      } else { //move not diagonal 
        return false;  
      }
    } else {
      return false;  
    }  
  }
}
