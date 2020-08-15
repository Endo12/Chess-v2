import javafx.application.Application;

import javafx.scene.*;
import javafx.stage.Stage;

public class ChessGUI {
    GridPane gPane = new GridPane();

    public void start(Stage primaryStage) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Rectangle tile = new Rectangle();
                Color color;
                if ((r) % 2 == 0) color = Color.WHITE;
                else color = Color.BLACK;
                square.setFill(color);
                root.add(tile, c, r);
                tile.widthProperty().bind(gPane.widthProperty().divide(8));
                tile.heightProperty().bind(gPane.heightProperty().divide(8));
            }
        }
        primaryStage.setScene(new Scene(gPane, 600, 600));
        primaryStage.show();
    }













}
