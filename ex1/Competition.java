import java.util.Scanner;
/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition
		extends java.lang.Object {

    /** The Player objects representing the first player. */
    Player player1;

    /** The Player objects representing the first player. */
    Player player2;

    /** a flag indicating whether game play messages should be printed to the console. */
    boolean displayMessage;

    /** number of wins for player one. */
    private int player1Wins = 0;

    /** number of wins for player two. */
    private int player2Wins = 0;


    /** MAGIC NUMBERS: */
    private static final int SYMBOLIZE_PLAYER_1 = 1;
    private static final int SYMBOLIZE_PLAYER_2 = 2;
    private static final int NON_VALID = -1;


    /**
     * Receives two Player objects, representing the two competing opponents, and a flag determining whether
     * messages should be displayed.
     * @param player1 The Player objects representing the first player.
     * @param player2 The Player objects representing the second player.
     * @param displayMessage a flag indicating whether game play messages should be printed to the console.
     */
    public Competition(Player player1, Player player2, boolean displayMessage) {
        this.player1 = player1;
        this.player2 = player2;
        this.displayMessage = displayMessage;
        player1Wins = 0;
        player2Wins = 0;
    }

    /**
     * If playerPosition = 1, the results of the first player is returned. If playerPosition = 2, the result
     * of the second player is returned. If playerPosition equals neiter, -1 is returned.
     * @param playerPosition the position of the player.
     * @return the number of victories of a player.
     */
    public int getPlayerScore(int playerPosition) { // // TODO: 07/03/2016 use this func to present the wins of a player
        if (playerPosition == SYMBOLIZE_PLAYER_1) {
            return player1Wins;
        } else if (playerPosition == SYMBOLIZE_PLAYER_2) {
            return player2Wins;
        } else {
            return NON_VALID;
        }
    }
    
    /**
	 * Returns the integer representing the type of player 1; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer1Type(String[] args){
		try{
			return Integer.parseInt(args[0]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/**
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer2Type(String[] args){
		try{
			return Integer.parseInt(args[1]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/**
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}

    /**
     * Run the game for the given number of rounds.
     * @param numberOfRounds number of rounds to play.
     */
    public void playMultipleRounds(int numberOfRounds) {
        int counter = 0; //count number of rounds already played
        while (numberOfRounds > counter) { // loop ends when the given number of round were played
            Player nextPlayer = player1;
            Board board = new Board();
            if (displayMessage) {
                System.out.println("Welcome to the sticks game!");
            }
            while (board.getNumberOfUnmarkedSticks() != 0) { // loop ends when the board is fully marked
                if (displayMessage) {
                    System.out.println("Player " + nextPlayer.getPlayerId() + ", it is now your turn!");
                }
                Move playerMoves = nextPlayer.produceMove(board);
                while (board.markStickSequence(playerMoves) != 0) { // loop ends when the move is valid
                    System.out.println("Invalid move. Enter another:");
                    playerMoves = nextPlayer.produceMove(board);
                }
                board.markStickSequence(playerMoves);
                if (displayMessage) {
                    System.out.println("Player " + nextPlayer.getPlayerId() + " made the move: " + playerMoves
                            .toString());
                }
                if (nextPlayer.getPlayerId() == SYMBOLIZE_PLAYER_1) {
                    nextPlayer = player2;
                } else if (nextPlayer.getPlayerId() == SYMBOLIZE_PLAYER_2) {
                    nextPlayer = player1;
                }
            }
            if (displayMessage) {
                System.out.println("Player " + nextPlayer.getPlayerId() + " won!");
            }
            if (nextPlayer.getPlayerId() == 1) {
                player1Wins++;
            } else if (nextPlayer.getPlayerId() == 2) {
                player2Wins++;
            }
            counter++;
        }
        System.out.println("The results are " + getPlayerScore(player1.getPlayerId()) + ":" +
                getPlayerScore(player2.getPlayerId()));
        }


    /**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {

        int p1Type = parsePlayer1Type(args);
		int p2Type = parsePlayer2Type(args);
		int numGames = parseNumberOfGames(args);
        Scanner scanner = new Scanner(System.in);
        boolean displayMessage = false;
        Player player1 = new Player(p1Type, 1, scanner);
        Player player2 = new Player(p2Type, 2, scanner);
        if (player1.getPlayerType() == Player.HUMAN || player2.getPlayerType() == Player.HUMAN) {
            displayMessage = true; // TODO: see if this var is valid... not validd!!!!!!!
        }
        String stringRepOfPlayer1Type = player1.getTypeName();
        String stringRepOfPlayer2Type = player2.getTypeName();
        System.out.println("Starting a Nim competition of " + numGames  + " rounds between a " +
                stringRepOfPlayer1Type +
                " player and a " + stringRepOfPlayer2Type + " player.");
        Competition competition = new Competition(player1, player2, displayMessage);
        competition.playMultipleRounds(numGames); //todo see  if this is the right implement...
        scanner.close();


		
		/* You need to implement this method */
		
	}

}
