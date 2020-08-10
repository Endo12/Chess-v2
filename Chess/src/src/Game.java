package src;

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
				System.out.println("Please pick starting position/piece");
			}
		}
	}
}
