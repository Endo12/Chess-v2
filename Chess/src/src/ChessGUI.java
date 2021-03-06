package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ChessGUI extends Application {
	public static GridPane gPane = new GridPane();
	public boolean whitesTurn = true, setStart = true, madeLabels = false; /*setStart tells you if you
	 	are setting the start tile*/
	private Tile start, end;
	public static Stage stage;
	private static Scene scene;
	private ColorAdjust filter = new ColorAdjust(1, 1, .5, -.5);
	Label labels[] = {new Label("White's Move"), 
			new Label("Select Start Tile"),
    		new Label("White's Points: 0"), 
    		new Label("Black's Points: 0")};
	public void displayError(String s) {
		String message = "Error: Invalid " + s + " Tile";
		Popup bothError = new Popup();
		bothError.setAutoHide(true);
		Label bothErrorLabel = new Label(message);
		bothErrorLabel.setStyle(" -fx-background-color: white; -fx-border-color:black; "
				+ "-fx-padding:3px;");
		bothErrorLabel.setTextAlignment(TextAlignment.CENTER);
		bothErrorLabel.setMinSize(230, 50);
		bothError.getContent().add(bothErrorLabel);
		bothError.show(stage);
	}
    public void start(Stage primaryStage) {
    	Board b = new Board();
    	ChessGUI.stage = primaryStage;
        updateLabels();
        updateBoard();
        primaryStage.setTitle("Chess");
        ChessGUI.scene = new Scene(gPane, 1000, 850);
        primaryStage.setScene(ChessGUI.scene);
        primaryStage.show();
    }
    public void updateBoard() { //Creates visual chessboard and places pieces
		Tile[][] board = Board.tileBoard;
    	for(int r=0; r<8; r++) {
    		for(int c=0; c<8; c++) {
    			updateTile(board, r, c);
    		}
    	}
    }
    public void updateTile(Tile[][] board, int r, int c) {
    	Rectangle tile = new Rectangle();
        Color color = r % 2 == c % 2 ? Color.WHITE : Color.DARKGRAY;
        tile.setFill(color);
        tile.widthProperty().bind(gPane.widthProperty().divide(8).subtract(20));
        tile.heightProperty().bind(gPane.heightProperty().divide(8));
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1.5);
        tile.setOnMouseClicked(new MoveHandler(r,c));
        StackPane stack = new StackPane();
        stack.getChildren().add(tile);
		Piece p = board[c][r].getPiece();
		if(p != null) {
			String link = "https://raw.githubusercontent.com/jlundstedt/chess-java/master/resources/", 
					pStr = p.toString();
			if(pStr.equals("WP")) {
				link += "wpawn";	
			}
			else if(pStr.equals("BP")) {
				link += "bpawn";
			}
			else if(pStr.equals("WKn")) {
				link += "wknight";	
			}
			else if(pStr.equals("BKn")) {
				link += "bknight";
			}
			else if(pStr.equals("WR")) {
				link += "wrook";	
			}
			else if(pStr.equals("BR")) {
				link += "brook";
			}
			else if(pStr.equals("WB")) {
				link += "wbishop";	
			}
			else if(pStr.equals("BB")) {
				link += "bbishop";
			}
			else if(pStr.equals("WKi")) {
				link += "wking";	
			}
			else if(pStr.equals("BKi")) {
				link += "bking";
			}
			else if(pStr.equals("WQ")) {
				link += "wqueen";	
			}
			else if(pStr.equals("BQ")) {
				link += "bqueen";
			}
			link += ".png";
			ImageView imv = new ImageView(new Image(link));
			imv.setFitHeight(95);
			imv.setFitWidth(95);
			imv.setOnMouseClicked(new MoveHandler(r,c));
			if(start != null && start.getX() == r && start.getY() == c) {
				imv.setEffect(filter);
			}
			stack.getChildren().add(imv);
		}
        gPane.add(stack, r, c);
    }
    public void updateLabels() {
    	if(!madeLabels) {
            for(int f=0; f<labels.length; f++) {
            	GridPane.setHalignment(labels[f], HPos.CENTER);
            	GridPane.setValignment(labels[f], VPos.CENTER);
            	labels[f].setFont(new Font("Times New Roman", 20));
            	gPane.add(labels[f], 8, f);
            }
            madeLabels = true;
    	}
    	else {
    		labels[0].setText((whitesTurn ? "White" : "Black") + "'s Move");
    		labels[1].setText("Select " + (setStart ? "Start" : "End") + " Tile");
    		labels[2].setText("White's Points: " + Board.pointsForWhite);
    		labels[3].setText("Black's Points: " + Board.pointsForBlack);
    	}
        
    }
    public static void main(String[] args) {
    	Application.launch(args);
	}
    private class MoveHandler implements EventHandler<MouseEvent> {
		private int row, col;
		public MoveHandler(int col, int row) {
			this.row = row;
			this.col = col;
		}
    	@Override
		public void handle(MouseEvent arg0) {
			Tile allyKingTile; 
			for(Tile[] row: Board.tileBoard) {
				for(Tile t: row) {
					if (t.getPiece() != null && t.getPiece() instanceof King) { //its a king
						if (whitesTurn) {//if it's whites turn, locate white king
							if (t.getPiece().getColor()) {
								allyKingTile = t; //current player's king tile 
								break;
							}
						}
						else { //else locate black king
							if (!t.getPiece().getColor()) {
								allyKingTile = t; //current players king tile 
								break;
							}
						}
					} // The tile of the current player's king is in allyKingTile 
				}
			}
			King king = (King) allyKingTile.getPiece(); // current player's king
			if (allyKing.isInCheck()) {
			/*for each piece, see if it a checking piece. If checking piece, see that it can/can't 
			* be captured. */
				
			/*consider creating a set of every checking piece that noBlock and noCapture can share to 
				decrease runtime*/
				king.updateCheckingPieces(allyKingTile);
				if (king.cantMove(allyKingTile) && king.noBlock(allyKingTile) && king.noCapture()) { 
					checkmate; //GAME ENDS
				} 
				else {
					check; 	
				}
			} 
			else { //check for stalemate
				boolean canMove = false; //assume no piece can move 
				for (Tile[] row: tileBoard) {
					for(Tile t: row) { // for each tile we are going to test if there's a piece 
						while (canMove == false) {
							if (t.getPiece() != null) { //confirm there's a piece 
								if (whitesTurn && t.getPiece().getColor()) { //we will test all of white's potential moves
									for(Tile[] row2: tileBoard) {
										for(Tile t2: row2) {
											if (!t2.getPiece() || !t2.getPiece().getColor()) { 
												if (t.getPiece().canMove(t,t2)) { //if white can move to any enemy or empty tiles
													canMove = true; //then they can move
												}
											}
										}
									}
								} else { // we will test all of black's potential moves
									if (!t.getPiece().getColor()) { //if black piece
										for(Tile[] row2: tileBoard) {
											for(Tile t2: row2) {
												if (!t2.getPiece() || t2.getPiece().getColor()) { //if black can move to any enemy or empty tiles 
													if (t.getPiece().canMove(t,t2)) { 
														canMove = true; //then they can move
													}
												}
											}
										}
									}
								} //BLACK CHECK
							} //NULL CHECK
						} //canMove == false 	
					}//nested loop
				}//first loop
				
				if (!canMove) {
				//RUN STALEMATE CODE		
			}
		}
				
    		if(setStart) {
    			Tile temp = Board.tileBoard[row][col];
    			Piece myPiece = temp.getPiece();
    			if(myPiece != null && myPiece.getColor() == whitesTurn) {
    				start = temp;
    				setStart = !setStart;
    				updateTile(Board.tileBoard, start.getX(), start.getY());
    			}
    			else {
    				displayError("Start");
    			}
    		}
    		else {
    			end = Board.tileBoard[row][col];
    			setStart = !setStart;
				Piece myPiece = start.getPiece();
				if(myPiece.moveTo(start, end)) {
					whitesTurn = !whitesTurn;
				}
				else {
					displayError("End");
				}
				int startX = start.getX(), startY = start.getY(), endX = end.getX(), endY = end.getY();
				start = null;
				end = null;
				updateTile(Board.tileBoard, startX, startY);
				updateTile(Board.tileBoard, endX, endY);
				
			}
			updateBoard();
    		updateLabels();
    		if(Board.whiteAlive != Board.blackAlive) {
    			System.out.println((Board.whiteAlive ? "White" : "Black") + " wins!\nWhite points: " + 
    				Board.pointsForWhite + "\nBlack points: " + Board.pointsForBlack);
    			System.exit(0);
    		}
		}
    }
}


