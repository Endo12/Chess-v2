package src;

public abstract class Piece {
	private boolean isWhite, isAlive; //maybe get rid of isAlive?
	private int points;
	public Piece(boolean isWhite, int points) {
		this.isWhite = isWhite;
		isAlive = true;
		this.points = points;
	}
	public abstract boolean canMove(Tile start, Tile end);
	public boolean moveTo(Tile start, Tile end) { //doesn't work for castling
		if(canMove(start, end)) {
			Piece captured = end.getPiece();
			if(captured != null) {
				captured.isAlive = false;
				if(captured.isKing()) {
					if(captured.getColor()) {
						Board.whiteAlive = false;
					}
					else {
						Board.blackAlive = false;
					}
				}
				else {
					if(captured.getColor()) {
						Board.pointsForBlack += captured.points;
					}
					else {
						Board.pointsForWhite += captured.points;
					}
				}
				captured = null;
			}
			
			end.setPiece(this);
			start.setPiece(null);
			
			/**
			 * Here we check to see if promotion of a pawn is necessary as a result of 
			 * the piece's movement. We then change the type of piece to Q, Knight, or R. 
			**/
			String id = this.toString(); 
			if (id.substring(1,2).equals("P")) { //check to see if this is a pawn
				if (this.canPromote(end)) {
					BorderPane borderPane = new BorderPane(); 
 					Scene promoPage = new Scene(borderPane, 300, 300); 
 					Stage stage2 = new Stage();
					stage2.setScene(promoPage); 	
					stage2.show();
				}
			}
			
			
			
			return true;
		}
		else {
			return false;
		}
	}
	public boolean getColor() {
		return isWhite;
	}
	public boolean isKing() {
		return false;
	}
	
	public boolean canPromote(Tile location) {
		return false; 
	}
	public abstract String toString();
}
