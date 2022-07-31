
/**
 * Write a description of Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player {

    private String name;
    private int moneyInPocket;
    private boolean isPlaying;

    public Player(String name) {
        this.isPlaying = true;
        this.name = name;
        this.moneyInPocket = 100;
    }

    public Player(String name, int money) {
        this.isPlaying = true;

    }

    public String getName() {
        return name;
    }

    public int getMoneyInPocket() {
        return moneyInPocket;
    }

    public void winMoney(int amount) {
        moneyInPocket += amount;
    }

    public void betMoney(int amount) {
        moneyInPocket -= amount;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void isNotPlaying() {
        isPlaying = false;
    }
}
