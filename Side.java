public enum Side {
    HEADS (0),
    TAILS (1);

    private int order;

    private Side(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
