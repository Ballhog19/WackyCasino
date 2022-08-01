import java.io.*;
import java.util.Locale;

public class WackyCasino {

    public WackyCasino() {
    }

    ;

    public void run() throws IOException {
        System.out.println("Welcome to...");
        StartUp startUp = new StartUp();
        startUp.printCasino();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Get player's name
        System.out.println("Welcome! What is your name?");
        String userName = reader.readLine();

        Player player = new Player(userName);
        System.out.println(
            "Hello, " + player.getName() + ". You start with " + player.getMoneyInPocket() + " dollars!");

        while (player.getIsPlaying()) {

            //Choose a game to play
            System.out.println("Please choose a game to play:");
            System.out.println("""
                1) Pick A Number
                2) Flip a Coin
                3) Rock, Paper, Scissors
                4) Pick A Hand
                5) War
                """);
            int pick = Integer.parseInt(reader.readLine());

            //Start game of choice
            switch (pick) {
                case 1 -> {
                    PickANumber pickANumber = new PickANumber(player);
                    pickANumber.playGame();
                }
                case 2 -> {
                    FlipACoin flipACoin = new FlipACoin(player);
                    flipACoin.playGame();
                }
                case 3 -> {
                    RockPaperScissors rps = new RockPaperScissors(player);
                    rps.playGame();
                }
                case 4 -> {
                    PickAHand pickAHand = new PickAHand(player);
                    pickAHand.playGame();
                }
                case 5 -> {
                    War war = new War(player);
                    war.playGame();
                }
            }

            //Determine if player still has money to keep playing
            if (player.getMoneyInPocket() == 0) {
                System.out.println("You've lost all your money! Better luck next time!");
                player.isNotPlaying();
                break;
            }

            //Does the player want to play again?
            System.out.println("Would you like to play another game? (Y/N)");
            String answer = reader.readLine();
            if (answer.toUpperCase(Locale.ROOT).equals("Y") || answer.toUpperCase(Locale.ROOT).equals("YES")) {
                continue;
            } else if (answer.toUpperCase(Locale.ROOT).equals("N") || answer.toUpperCase(Locale.ROOT).equals("NO")) {
                System.out.println("Thanks for Playing. Come again soon!");
                player.isNotPlaying();
            }
        }
    }
}
