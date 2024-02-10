public enum Weapon {

    HAND(5,0),
    SWORD(15,2),
    PISTOL(30,4),
    RIFLE(50,7),
    MACHINE_GUN(100,15);
    private final int damage;
    private final int level;
    Weapon(int damage, int level) {
        this.damage = damage;
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }
}
