
/**
 * Write a description of PickANumber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

public class PickANumber {

    private int dealerPick;
    private Player player;
    private boolean keepPlaying = true;

    public PickANumber(Player player) {
        this.player = player;
        this.dealerPick = (int) (Math.random() * 10 + 1);
    }

    public void playGame() throws IOException {
        while (keepPlaying) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Let's Play Pick A Number!\n"
                    + "The dealer is holding a number between 1 and 10 behind their back.\n");
            System.out.println("You have " + player.getMoneyInPocket() + " dollars. How much would you like to bet?");
            int bet = Integer.parseInt(reader.readLine());
            while (bet > player.getMoneyInPocket()) {
                System.out.println("You don't have that much in your pocket, bet a lower amount");
                bet = Integer.parseInt(reader.readLine());
            }
            player.betMoney(bet);
            System.out.println("What is your guess?");
            int guess = Integer.parseInt(reader.readLine());
            System.out.println("Your guess was: " + guess);
            System.out.println("The dealer was hiding: " + dealerPick);
            if (guess == dealerPick) {
                System.out.println("You Won!");
                int winnings = bet * 10;
                player.winMoney(winnings);
                System.out.println("You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() + " dollars.");
            } else {
                System.out.println("You Lost!");
                System.out.println("You now have " + player.getMoneyInPocket());
            }

            if (player.getMoneyInPocket() == 0) {
                break;
            }

            System.out.println("Would you like to play again? (Y/N)");
            String answer = reader.readLine();
            if (answer.toUpperCase(Locale.ROOT).equals("Y") || answer.toUpperCase(Locale.ROOT).equals("YES")) {
                continue;
            } else if (answer.toUpperCase(Locale.ROOT).equals("N") || answer.toUpperCase(Locale.ROOT).equals("NO")) {
                    System.out.println("Thanks for Playing Pick a Number. Come again soon!");
                    keepPlaying = false;
            }
        }
    }
}
