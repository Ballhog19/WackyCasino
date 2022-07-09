import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class RockPaperScissors {

    private Rps dealerPick;
    private Player player;
    private boolean keepPlaying = true;

    public RockPaperScissors(Player player) {
        this.player = player;

    }

    public void playGame() throws IOException {
        while (keepPlaying) {
            int num = (int) (Math.random() * 3);
            switch (num) {
                case 0:
                    this.dealerPick = Rps.ROCK;
                    break;
                case 1:
                    this.dealerPick = Rps.PAPER;
                    break;
                case 2:
                    this.dealerPick = Rps.SCISSORS;
                    break;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Let's Play Rock, Paper, Scissors!\n");
            System.out.println("You have " + player.getMoneyInPocket() + " dollars. How much would you like to bet?");
            int bet = Integer.parseInt(reader.readLine());
            while (bet > player.getMoneyInPocket()) {
                System.out.println("You don't have that much in your pocket, bet a lower amount");
                bet = Integer.parseInt(reader.readLine());
            }
            player.betMoney(bet);
            System.out.println("Choose your weapon:\n"
                    + "1) Rock\n"
                    + "2) Paper\n"
                    + "3) Scissors");
            int weapon = Integer.parseInt(reader.readLine());
            System.out.println("1, 2, 3, SHOOT!");
            switch (weapon) {
                case 1:
                    System.out.println("You chose Rock");
                    System.out.println("Dealer chose " + dealerPick);
                    if (dealerPick.getOrder() == 0) {
                        System.out.println("You Tied. You get your bet back!");
                        player.winMoney(bet);
                    } else if (dealerPick.getOrder() == 1) {
                        System.out.println("You Lose!");
                    } else if (dealerPick.getOrder() == 2) {
                        System.out.println("You Win!");
                        int winnings = bet * 3;
                        player.winMoney(winnings);
                        System.out.println("You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() + " dollars.");
                    }
                    break;
                case 2:
                    System.out.println("You chose Paper");
                    System.out.println("Dealer chose " + dealerPick);
                    if (dealerPick.getOrder() == 1) {
                        System.out.println("You Tied. You get your bet back!");
                        player.winMoney(bet);
                    } else if (dealerPick.getOrder() == 2) {
                        System.out.println("You Lose!");
                    } else if (dealerPick.getOrder() == 0) {
                        System.out.println("You Win!");
                        int winnings = bet * 3;
                        player.winMoney(winnings);
                        System.out.println("You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() + " dollars.");
                    }
                    break;
                case 3:
                    System.out.println("You chose Scissors");
                    System.out.println("Dealer chose " + dealerPick);
                    if (dealerPick.getOrder() == 2) {
                        System.out.println("You Tied. You get your bet back!");
                        player.winMoney(bet);
                    } else if (dealerPick.getOrder() == 0) {
                        System.out.println("You Lose!");
                    } else if (dealerPick.getOrder() == 1) {
                        System.out.println("You Win!");
                        int winnings = bet * 3;
                        player.winMoney(winnings);
                        System.out.println("You've received " + winnings + " dollars and now have " + player.getMoneyInPocket() + " dollars.");
                    }
                    break;
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
