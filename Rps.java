public enum Rps {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);

    private final int order;

    private Rps(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
