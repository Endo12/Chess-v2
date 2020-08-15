package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessGUI extends Application{
	GridPane gPane = new GridPane();
    public void start(Stage primaryStage) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Rectangle tile = new Rectangle();
                Color color;
                if ((r) % 2 == 0) color = Color.WHITE;
                else color = Color.BLACK;
                tile.setFill(color);
                gPane.add(tile, c, r);
                tile.widthProperty().bind(gPane.widthProperty().divide(8));
                tile.heightProperty().bind(gPane.heightProperty().divide(8));
            }
        }
        primaryStage.setScene(new Scene(gPane, 600, 600));
        primaryStage.show();
    }
    public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Application.launch(args);
	}
}


