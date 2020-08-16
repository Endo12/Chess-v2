package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessGUI extends Application{
	GridPane gPane = new GridPane();
    public void start(Stage primaryStage) {
        Board b = new Board();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Rectangle tile = new Rectangle();
                Color color = r % 2 == c % 2 ? Color.WHITE : Color.BLACK;
                tile.setFill(color);
                tile.widthProperty().bind(gPane.widthProperty().divide(8));
                tile.heightProperty().bind(gPane.heightProperty().divide(8));
                StackPane stack = new StackPane();
                ImageView imv = new ImageView(new 
                		Image("https://static.thenounproject.com/png/722241-200.png"));
                imv.setFitHeight(100);
				imv.setFitWidth(100);
                stack.getChildren().addAll(tile, imv);
                gPane.add(stack, r, c);
                
            }
        }
        //updateBoard(gPane);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(gPane, 815, 815));
        primaryStage.show();
    }
    public void updateBoard(GridPane gPane) {
		Tile[][] board = Board.tileBoard;
    	for(int r=0; r<8; r++) {
    		for(int c=0; c<8; c++) {
    			Piece p = board[r][c].getPiece();
    			if(p != null) {
    				String link = "";
    				Group g = null;
    				if(p.toString().equals("WP")) {
    					link = "https://static.thenounproject.com/png/722241-200.png";
    				}
    				else {
    					continue;
    				}
    				ImageView imv = new ImageView(new Image(link));
    				imv.setFitHeight(100);
    				imv.setFitWidth(100);
					g = new Group(new HBox(imv));
    				gPane.add(g, c, r);
    			}
    		}
    	}
    }
    public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
	}
}


