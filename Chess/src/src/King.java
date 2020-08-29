package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class King extends Piece {
	
	private HashMap<Piece, Tile> checkingPieces;
	
	public King(boolean isWhite) {
		super(isWhite, 0); 	
		checkingPieces = new HashMap<Piece, Tile>();
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
				if(this.getColor()) {
					Board.whiteKCastle = false;
					Board.whiteQCastle = false;
				}
				else {
					Board.blackKCastle = false;
					Board.blackQCastle = false;
				}
				return true; //the move does not put the king in check, and it is otherwise allowed
			}
		}
		else if(diffX == 2 && diffY == 0 && this.getColor() ? Board.whiteKCastle || 
			Board.whiteQCastle : Board.blackKCastle || Board.blackQCastle) {
			int row = this.getColor() ? 0 : 7;
			Tile[][] board = Board.tileBoard;
			if(start.getX() > end.getX() && (this.getColor() ? Board.whiteQCastle :
				Board.blackQCastle)) { //Queen castle
				for(int f = 2; f < 5; f++) {
					if(isInCheck(board[row][f])) {
						return false;
					}
				}
				for(int g = 1; g < 4; g++) {
					if(board[row][g].getPiece() != null) {
						return false;
					}
				}
				if(this.getColor()) {
					Board.whiteKCastle = false;
					Board.whiteQCastle = false;
				}
				else {
					Board.blackKCastle = false;
					Board.blackQCastle = false;
				}
				board[row][0].setPiece(null);
				board[row][2].setPiece(new Rook(this.getColor()));
				return true;
			}
			else if(start.getX() < end.getX() && (this.getColor() ? Board.whiteKCastle : 
				Board.blackKCastle)) { //King Castle
				for(int f = 4; f < 7; f++) {
					if(isInCheck(board[row][f])) {
						return false;
					}
				}
				for(int g = 5; g < 7; g++) {
					if(board[row][g].getPiece() != null) {
						return false;
					}
				}
				if(this.getColor()) {
					Board.whiteKCastle = false;
					Board.whiteQCastle = false;
				}
				else {
					Board.blackKCastle = false;
					Board.blackQCastle = false;
				}
				board[row][7].setPiece(null);
				board[row][5].setPiece(new Rook(this.getColor()));
				return true;
			}
			else {
				return false;
			}
		}
		return false; // move was not legal in terms of direction/# of tiles	
	}
	
	public boolean isKing() {
		return true;
	}
	
	public boolean isInCheck(Tile kingTile) {
		Tile[][] tiles = Board.tileBoard; 
		for(Tile[] r: tiles) {
			for(Tile t: r) {
				Piece enemy = t.getPiece();
				if (enemy != null && enemy.getColor() != this.getColor()) {
					if(enemy.canMove(t, kingTile)) {
						return true; 	
					}
				}
			}
		}
		return false; 
	}

	@Override
	public String toString() {
		return (this.getColor() ? "W" : "B") + "Ki";
	}

	@Override
	public boolean checkingPiece(Tile start) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void updateCheckingPieces(Tile curr) {
		checkingPieces.clear();
		Tile[][] board = Board.tileBoard;
		for(Tile[] row : board) {
			for(Tile t : row) {
				Piece p = t.getPiece();
				if(p != null && p.getColor() != this.getColor() && p.checkingPiece(t)) {
					checkingPieces.put(p, t);
				}
			}
		}
	}
	
	public boolean cantMove(Tile curr) {
		int currX = curr.getX(), currY = curr.getY();
		for(int c = currX - 1; c < currX + 2; c++) {
			for(int r = currY - 1; r < currY + 2; r++) {
				if(r >= 0 && c >= 0 && r < 8 && c < 8 && !(c == currX && r == currY) && canMove(curr,
						Board.tileBoard[r][c])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean noBlock(Tile curr) {
		TreeSet<Tile> tiles = new TreeSet<Tile>();
		Tile[][] board = Board.tileBoard;
		for(Map.Entry<Piece, Tile> e : checkingPieces.entrySet()) {
			for(Tile t : e.getKey().getPath(e.getValue(), curr)) {
				tiles.add(t);
			}
		}
		for(Tile part : tiles) {
			for(Tile[] row : board) {
				for(Tile t : row) {
					Piece p = t.getPiece();
					if(p != null && p.getColor() == this.getColor() && p.canMove(t, part)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean noCapture(Tile curr) {
		
		return true;
	}
}
