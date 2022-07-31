import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class War {

    private Card dealerCard;
    private Card playerCard;
    private Player player;
    private boolean keepPlaying;
    private int totalBet;

    public War(Player player) {
        this.player = player;
        this.totalBet = 0;
        this.keepPlaying = true;
    }

    public void playGame() throws IOException {
        //Start of a new game
        while (keepPlaying) {
            totalBet = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Let's Play War!\n");

            //Get player's bet
            int bet = this.getBet();
            player.betMoney(bet);
            totalBet += bet;


            //play the card game
            do {
                this.drawCards();
                System.out.println("The dealer draws a " + dealerCard);
                System.out.println("You draw a " + playerCard);

                if (playerCard.getOrder() > dealerCard.getOrder()) {
                    System.out.println("You Win!");
                    int winnings = totalBet * 2;
                    player.winMoney(winnings);
                    System.out.println(
                        "You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() +
                            " dollars.");
                } else if (playerCard.getOrder() < dealerCard.getOrder()) {
                    System.out.println("You Lose!");
                } else {
                    System.out.println("It's a Tie! WAR!!!");
                    if (player.getMoneyInPocket() == 0) {
                        System.out.println(
                            "You don't have enough money to continue. The dealer laughs and ends the game.");
                        break;
                    } else {
                        bet = this.getBet();
                        player.betMoney(bet);
                        totalBet += bet;
                    }
                }
            } while (dealerCard.getOrder() == playerCard.getOrder());


            //Check if player still has money
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

    //Draws cards for the dealer and player
    private void drawCards() {
        int dealerCardNum = (int) (Math.random() * 52) % 13;
        for (Card card : Card.values()) {
            if (dealerCardNum == card.getOrder()) {
                this.dealerCard = card;
            }
        }

        int playerCardNum = (int) (Math.random() * 52) % 13;
        for (Card card : Card.values()) {
            if (playerCardNum == card.getOrder()) {
                this.playerCard = card;
            }
        }
    }

    //Gets bet from player
    private int getBet() throws IOException {
        System.out.println("You have " + player.getMoneyInPocket() + " dollars. How much would you like to bet?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int bet = Integer.parseInt(reader.readLine());

        while (bet > player.getMoneyInPocket()) {
            System.out.println("You don't have that much in your pocket, bet a lower amount");
            bet = Integer.parseInt(reader.readLine());
        }
        return bet;
    }
}
