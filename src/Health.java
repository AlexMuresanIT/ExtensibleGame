public enum Health {
    SNACK(5),
    APPLES(15),
    HAMBURGER(24);
    private final int health;
    Health(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
}
