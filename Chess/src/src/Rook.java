package src;

public class Rook extends Piece {

	public Rook(boolean isWhite) {
		super(isWhite, 5);
	}

	@Override
	public boolean canMove(Tile start, Tile end) {
		Piece destPiece = end.getPiece();
		int startX = start.getX(), endX = end.getX(), startY = start.getY(), endY = end.getY();
		boolean diffX = startX != endX, diffY = startY != endY;
		if((destPiece != null && destPiece.getColor() == this.getColor()) || start.equals(end) || 
				diffX && diffY) { /*Condition ensures that the piece wouldn't be capturing another 
				piece on its team, that the 2 tiles are different, and that the 2 tiles share a row 
				or column*/
			return false;
		}
		Tile boardArr[][] = Board.tileBoard;
		if(diffX) { /*Cycles through X positions and returns false if there's any collisions*/
			for(int f=Math.min(startX, endX); f<Math.max(startX, endX); f++) {
				if(boardArr[startY][f].getPiece() != null) {
					return false;
				}
			}
		}
		else { /*Same as above, but with Y positions*/
			for(int f=Math.min(startY, endY); f<Math.max(startY, endY); f++) {
				if(boardArr[f][startX].getPiece() != null) {
					return false;
				}
			}
		}
		return true;
	}

}
