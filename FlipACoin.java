import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class FlipACoin {

    private Side coinFlip;
    private Player player;
    private boolean keepPlaying = true;

    public FlipACoin(Player player) {
        this.player = player;
    }

    public void playGame() throws IOException {
        //Start of a new game
        while (keepPlaying) {

            //Generate the dealer's coin flip result
            int flip = (int) (Math.random() * 2);
            for (Side side : Side.values()) {
                if (side.getOrder() == flip) {
                    this.coinFlip = side;
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Let's Play Flip a Coin!\n");
            System.out.println("You have " + player.getMoneyInPocket() + " dollars. How much would you like to bet?");
            int bet = Integer.parseInt(reader.readLine());
            while (bet > player.getMoneyInPocket()) {
                System.out.println("You don't have that much in your pocket, bet a lower amount");
                bet = Integer.parseInt(reader.readLine());
            }
            player.betMoney(bet);

            //Get players guess
            System.out.println("What is your guess?\n"
                + "1) Heads\n"
                + "2) Tails");
            int guess = Integer.parseInt(reader.readLine());

            //Determine if player's guess matches dealer's flip
            for (Side side : Side.values()) {
                if (guess == side.getOrder()) {
                    System.out.println("Your guess was: " + side);
                }
            }
            System.out.println("The dealer flips the coin...");
            System.out.println("It's " + coinFlip);

            //Display results
            if (guess == coinFlip.getOrder() + 1) {
                System.out.println("You Won!");
                int winnings = bet * 2;
                player.winMoney(winnings);
                System.out.println(
                    "You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() + " dollars.");
            } else {
                System.out.println("You Lost!");
                System.out.println("You now have " + player.getMoneyInPocket());
            }

            if (player.getMoneyInPocket() == 0) {
                break;
            }

            //Does the player want to play again?
            System.out.println("Would you like to play again? (Y/N)");
            String answer = reader.readLine();
            if (answer.toUpperCase(Locale.ROOT).equals("Y") || answer.toUpperCase(Locale.ROOT).equals("YES")) {
                continue;
            } else if (answer.toUpperCase(Locale.ROOT).equals("N") || answer.toUpperCase(Locale.ROOT).equals("NO")) {
                System.out.println("Thanks for Playing Flip a Coin. Come again soon!");
                keepPlaying = false;
            }
        }
    }
}
