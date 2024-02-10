import java.util.Random;

public class Zombie implements Player{

    private final String name;
    public Zombie() {
        this.name = "Zombie";
    }
    @Override
    public String name() {
        return name;
    }
    int fight(){
       return new Random().nextInt(10,31);
    }


}
