package src;

public class Rook extends Piece {

	public Rook(boolean isWhite) {
		super(isWhite, 5);
	}

	@Override
	public boolean canMove(Tile start, Tile end) {
		Piece destPiece = end.getPiece();
		boolean diffX = start.getX() != end.getX(), diffY = start.getY() != end.getY();
		if((destPiece != null && destPiece.getColor() == this.getColor()) || start.equals(end) || 
				diffX && diffY) { /*Condition ensures that the piece wouldn't be capturing another 
				piece on its team, that the 2 tiles are different, and that the 2 tiles share a row 
				or column*/
			return false;
		}
		Tile boardArr[][] = Board.tileBoard;
		
	}

}
