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
	private GridPane gPane = new GridPane();
	public boolean whitesTurn = true, setStart = true, madeLabels = false;
	private Tile start, end;
	private Stage stage;
	Label labels[] = {new Label((whitesTurn ? "White" : "Black") + "'s Move"), 
    		new Label("White's Points: " + Board.pointsForWhite), 
    		new Label("Black's Points: " + Board.pointsForBlack)};
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
    	this.stage = primaryStage;
        updateLabels();
        updateBoard(primaryStage);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(gPane, 1000, 850));
        primaryStage.show();
    }
    public void updateBoard(Stage s) { //Creates visual chessboard and places pieces
		Tile[][] board = Board.tileBoard;
    	for(int r=0; r<8; r++) {
    		for(int c=0; c<8; c++) {
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
    				stack.getChildren().add(imv);
    			}
                gPane.add(stack, r, c);
    		}
    	}
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
    		labels[1].setText("White's Points: " + Board.pointsForWhite);
    		labels[2].setText("Black's Points: " + Board.pointsForBlack);
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
		public void handle(MouseEvent arg0) { //note: rook movement doesn't work
    		if(setStart) {
    			Tile temp = Board.tileBoard[row][col];
    			Piece myPiece = temp.getPiece();
    			if(myPiece != null && myPiece.getColor() == whitesTurn) {
    				start = temp;
    			}
    			else {
    				displayError("Start");
    			}
    		}
    		else {
    			end = Board.tileBoard[row][col];
    		}
			setStart = !setStart;
    		if(start != null && end != null) {
				Piece myPiece = start.getPiece();
				if(myPiece.moveTo(start, end)) {
					updateBoard(stage);
					whitesTurn = !whitesTurn;
					updateLabels();
				}
				else {
					displayError("End");
				}
				start = null;
				end = null;
			}
    		if(Board.whiteAlive != Board.blackAlive) {
    			System.out.println((Board.whiteAlive ? "White" : "Black") + " wins!\nWhite points: " + 
    				Board.pointsForWhite + "\nBlack points: " + Board.pointsForBlack);
    			System.exit(0);
    		}
		}
    }
}


