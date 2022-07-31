import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class PickAHand {

    private Hand handPick;
    private Player player;
    private boolean keepPlaying = true;

    public PickAHand(Player player) {
        this.player = player;
    }

    public void playGame() throws IOException {
        //Start of a new game
        while (keepPlaying) {

            //Determine the hand the dealer has behind its back
            int flip = (int) (Math.random() * 2);
            for (Hand hand : Hand.values()) {
                if (flip == hand.getOrder()) {
                    this.handPick = hand;
                }
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Let's Play Pick A Hand!\n");

            //Get the player's bet
            System.out.println("You have " + player.getMoneyInPocket() + " dollars. How much would you like to bet?");
            int bet = Integer.parseInt(reader.readLine());
            while (bet > player.getMoneyInPocket()) {
                System.out.println("You don't have that much in your pocket, bet a lower amount");
                bet = Integer.parseInt(reader.readLine());
            }
            player.betMoney(bet);
            System.out.println("The dealer matches your bet and places the money in a hand behind their back.");

            //Get the player's guess
            System.out.println("What is your guess?\n"
                + "1) Left\n"
                + "2) Right");
            int guess = Integer.parseInt(reader.readLine());

            //Determine if the player's guess matches the dealer's hand
            for (Hand hand : Hand.values()) {
                if (guess == hand.getOrder()) {
                    System.out.println("Your guess was: " + hand);
                }
            }

            //Display results
            System.out.println("The dealer reveals the correct hand...");
            System.out.println("It's " + handPick);

            if (guess == handPick.getOrder() + 1) {
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
                System.out.println("Thanks for Playing Pick a Number. Come again soon!");
                keepPlaying = false;
            }
        }
    }
}
