package src;

import java.util.Scanner;

public class Game {

	public Game() {
	}

	public static void playGame() {
		Board myBoard = new Board();
		boolean isWhite = true;
		while(Board.whiteAlive && Board.blackAlive) {
			String currColor = isWhite ? "White" : "Black";
			System.out.println(currColor + "'s turn, has " + (isWhite ? Board.pointsForWhite : 
				Board.pointsForBlack) + " points");
			boolean invalidMove = true;
			Board.printBoard();
			while(invalidMove) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Please pick starting row position/piece");
				int startRow = scan.nextInt();
				System.out.println("Please pick starting column position/piece");
				int startCol = scan.nextInt();
				System.out.println("Please pick ending row position/piece");
				int endRow = scan.nextInt();
				System.out.println("Please pick ending column position/piece");
				int endCol = scan.nextInt();
				Tile start = Board.tileBoard[startRow][startCol], end = Board.tileBoard[startRow]
						[startCol];
				Piece myPiece = start.getPiece();
				if(myPiece != null && myPiece.getColor() == isWhite && myPiece.moveTo(start, end)) {
					System.out.println("Move completed successfully");
					invalidMove = false;
				}
				else {
					System.out.println("Error: move invalid");
				}
			}
			isWhite = !isWhite;
		}
		System.out.println((Board.whiteAlive ? "White" : "Black") + " wins!\nWhite points: " + 
				Board.pointsForWhite + "\nBlack points: " + Board.pointsForBlack);
	}
}
