package src;

import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Game {
	public static boolean whitesTurn = true, setStart = true;;
	private static Tile start, end;
	public static void playGame(Stage stage) {
		while(Board.whiteAlive && Board.blackAlive) {
			boolean invalidMove = true;
			while(invalidMove) {
				if(start != null && end != null) {
					Piece myPiece = start.getPiece();
					if(myPiece.moveTo(start, end)) {
						invalidMove = false;
					}
					else {
						displayError("End", stage);
					}
				}
			}
			whitesTurn = !whitesTurn;
		}
		System.out.println((Board.whiteAlive ? "White" : "Black") + " wins!\nWhite points: " + 
				Board.pointsForWhite + "\nBlack points: " + Board.pointsForBlack);
	}
	public static void setTile(int row, int col, Stage s) {
		if(setStart) {
			Tile temp = Board.tileBoard[row][col];
			Piece myPiece = temp.getPiece();
			if(myPiece != null && myPiece.getColor() == whitesTurn) {
				start = temp;
			}
			else {
				displayError("Start", s);
			}
			setStart = !setStart;
		}
		else {
			end = Board.tileBoard[row][col];
		}
	}
	public static void displayError(String s, Stage stage) {
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
}
