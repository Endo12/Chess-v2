package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class ChessGUI extends Application{
	GridPane gPane = new GridPane();
    public void start(Stage primaryStage) {
        Board b = new Board();
        updateBoard(gPane);
        Label move = new Label("White's Move");
        gPane.add(move, 8, 0);
        Label wpoints = new Label("White's Points:");
        gPane.add(wpoints, 8, 1);
        Label bpoints = new Label("Black's Points");
        gPane.add(bpoints, 8, 2);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(gPane, 1000, 850));
        primaryStage.show();
    }
    public void updateBoard(GridPane gPane) { //Creates visual chessboard and places pieces
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
    				imv.setOnMouseClicked(e -> {
    					
    				});
    				stack.getChildren().add(imv);
    			}
                gPane.add(stack, r, c);
    		}
    	}
    }
    public static void main(String[] args) 
	{
		Application.launch(args);
	}
}


