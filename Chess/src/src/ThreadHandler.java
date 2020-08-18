public class ThreadHandler extends Thread {
    	private boolean frontend;
    	private String[] args;
    	private ChessGUI chess;
    	public ThreadHandler (boolean frontend, String[] args, ChessGUI chess) {
    		this.frontend = frontend;
    		this.args = args;
    		this.chess = chess;
    	}
		@Override
		public void run() {
			if(frontend) {
				Application.launch(args);
			}
			else {
				chess.playGame();
			}
		}
    	
    }
