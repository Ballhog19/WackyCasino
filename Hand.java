public enum Hand {
    LEFT(0),
    RIGHT(1);

    private final int order;

    private Hand(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
