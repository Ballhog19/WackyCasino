
/**
 * Write a description of WackyCasino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.Locale;

public class WackyCasino {

    public WackyCasino() {};
    
    public void run() throws IOException {
        System.out.println("Welcome to...");
        StartUp startUp = new StartUp();
        startUp.printCasino();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome! What is your name?");
        String userName = reader.readLine();

        Player player = new Player(userName);
        System.out.println("Hello, " + player.getName() + ". You start with " + player.getMoneyInPocket() + " dollars!");

        while (player.getIsPlaying()) {
            System.out.println("Please choose a game to play:");
            System.out.println(""
                                + "1) Pick A Number\n"
                                + "2) Flip a Coin\n"
                                + "3) Rock, Paper, Scissors\n"
                                + "4) Pick A Hand\n"
                                + "5) War\n"
                                +"\n");
            int pick = Integer.parseInt(reader.readLine());

            switch(pick) {
                case 1:
                    PickANumber pickANumber = new PickANumber(player);
                    pickANumber.playGame();
                    break;
                case 2:
                    FlipACoin flipACoin = new FlipACoin(player);
                    flipACoin.playGame();
                    break;
                case 3:
                    RockPaperScissors rps = new RockPaperScissors(player);
                    rps.playGame();
                    break;
                case 4:
                    PickAHand pickAHand = new PickAHand(player);
                    pickAHand.playGame();
                    break;
                case 5:
                    War war = new War(player);
                    war.playGame();
                    break;
            }

            if (player.getMoneyInPocket() == 0) {
                System.out.println("You've lost all your money! Better luck next time!");
                player.isNotPlaying();
                break;
            }
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
