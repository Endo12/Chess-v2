package src;

public class King extends Piece {
	
	public King(boolean isWhite) {
		super(isWhite, 0); 	
	}
	
	public boolean canMove(Tile start, Tile end) {
		int diffX = Math.abs(start.getX() - end.getX()); 
		int diffY = Math.abs(start.getY() - end.getY()); 
		if (diffX + diffY <= 2 && diffX < 2 && diffY < 2) { // can only move one space in vertical direction or horizontal direction, or one in both directions
			if (end.getPiece() == null || this.getColor() != end.getPiece().getColor()) { // check for empty space or occupied by enemy piece
				Tile[][] tiles = Board.tileBoard; 
				for(Tile[] r: tiles) { // check every tile on the board...
					for(Tile t: r) {
						Piece enemy = t.getPiece();
						//if the tile has an enemy piece
						if(enemy != null && enemy.getColor() != this.getColor()) {  
							if (enemy.canMove(t, end)) { // and the piece can move to "Tile end"
								return false; // the move would put king in check, can't move to "Tile end"
							}
						}
					}
				} 
				return true; //the move does not put the king in check, and it is otherwise allowed
			}	
		}	
		return false; // move was not legal in terms of direction/# of tiles	
	}
	
	public boolean isKing() {
		return true;
	}

	@Override
	public String toString() {
		return (this.getColor() ? "W" : "B") + "Ki";
	}
}
