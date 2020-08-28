package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
			if (id.substring(1,2).equals("P") && this.canPromote(end)) { //check to see if this is a pawn
					end.setPiece(new Queen(isWhite));
					/*Popup promoPage = new Popup();
					promoPage.setAutoHide(true);					
					//buttons for each option
					Button queen = new Button(); 
					Button bishop = new Button(); 
					Button rook = new Button(); 
					Image qImg, bImg, rImg;
					if (this.isWhite) {
						qImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/wqueen");
						bImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/wbishop");
						rImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/wrook"); 
					} else {
						qImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/bqueen");
						bImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/bbishop");
						rImg = new Image("https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/brook"); 
					}
					ImageView qView = new ImageView(qImg); 
					ImageView bView = new ImageView(bImg);
					ImageView rView = new ImageView(rImg); 
					
					queen.setGraphic(qView); 
					bishop.setGraphic(bView); 
					rook.setGraphic(rView); 
					
					queen.setMinSize(100, 100);
					bishop.setMinSize(100, 100);
					rook.setMinSize(100, 100);
					//if pressed, change to queen... close window
					queen.setOnAction(new EventHandler<ActionEvent>() {
    						@Override public void handle(ActionEvent e) {
        						end.setPiece(new Queen(isWhite)); 
        						promoPage.hide();
        						//ChessGUI.stage.close();
    						}
					});
					//if pressed, change to bishop...close window
					queen.setOnAction(new EventHandler<ActionEvent>() {
    						@Override public void handle(ActionEvent e) {
        						end.setPiece(new Bishop(isWhite)); 
        						promoPage.hide();
    						}
					});
					//if pressed, change to rook...close window
					queen.setOnAction(new EventHandler<ActionEvent>() {
    						@Override public void handle(ActionEvent e) {
        						end.setPiece(new Rook(isWhite)); 
        						promoPage.hide();
    						}
					});
					promoPage.getContent().addAll(queen, bishop, rook);
					//promoPage.setAllignment(Pos.CENTER); 
					promoPage.show(ChessGUI.stage);*/ 
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public Tile[] getPath(Tile start, Tile end) { //returns an array of tiles representing the path from start to end 
		return null; 
	}
	
	public boolean checkingPiece() { //a piece is a checking piece if it has the enemy king in check 
		return false; 
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
	public abstract boolean checkingPiece(Tile start);
}
