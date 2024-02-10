import java.util.Random;

public class FinalBoss implements Player{

    private final String name;

    public FinalBoss() {
        this.name = "Final boss";
    }
    public int getDamage(){
        return new Random().nextInt(1,71);
    }
    @Override
    public String name() {
        return name;
    }
}
