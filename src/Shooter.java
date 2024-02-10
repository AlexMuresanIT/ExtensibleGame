import java.time.LocalDateTime;
import java.util.*;

public class Shooter implements Player {

    private final String name;
    private int life;
    private int points;
    private Weapon weapon;
    private int level;
    private final Map<Integer,String> locations = new LinkedHashMap<>();
    private final List<Health> consumables = new ArrayList<>();

    private void addingConsumables(){
        consumables.add(Health.SNACK);
        consumables.add(Health.APPLES);
        consumables.add(Health.HAMBURGER);
    }
    public void loadingData(){
        System.out.println("Loading data...");
        locations.put(0,"Loading dock");
        locations.put(1,"Police");
        locations.put(2,"Hospital");
        locations.put(3,"Park");
        locations.put(4,"TownHall");
        locations.put(5,"Helipad (Final boss)");
        System.out.println("Initial location: "+locations.get(0));
    }

    public Shooter(String name, int life, int points) {
        this.name = name;
        this.life = life;
        this.points = points;
        this.weapon= Weapon.HAND;
        this.level=0;
    }

    public int getLife() {
        return life;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "%s \nlife: %d \nlevel: %d \nweapon: %s \ndamage: %d".formatted(name,life,level,weapon,points);
    }

    boolean Shoot(){

            Random random = new Random();
            int target = random.nextInt(1,4);

            if(target==1){
                int damage = weapon.getDamage()+20;
                System.out.println("You shoot the opponent in the head. "+ damage +" damage");
                points+=weapon.getDamage()+20;
            } else if (target==2) {
                int damage = weapon.getDamage()+10;
                System.out.println("You shoot the opponent in the body. "+ damage +" damage");
                points+=weapon.getDamage()+10;
            } else {
                int damage = weapon.getDamage()+5;
                System.out.println("You shoot the opponent in the foot. "+ damage +" damage");
                points+=weapon.getDamage()+5;
            }


            if(points>=100 && points<250){
                level=1;
                weapon=Weapon.SWORD;
                System.out.println("Congratulations! You move to level 1. Your new weapon is: "+weapon);
                System.out.println("Current location: "+locations.get(level));
            }
            else if(points>=250 && points<500){
                level=2;
                weapon=Weapon.PISTOL;
                System.out.println("Congratulations! You move to level 2. Your new weapon is: "+weapon);
                System.out.println("Current location: "+locations.get(level));
            } else if (points>=500 && points<900) {
                level=3;
                weapon=Weapon.RIFLE;
                System.out.println("Congratulations! You move to level 3. Your new weapon is: "+weapon);
                System.out.println("Current location: "+locations.get(level));
            } else if (points>=900 && points<1500) {
                level=4;
                weapon=Weapon.RIFLE;
                System.out.println("Congratulations! You move to level 4. Your new weapon is: "+weapon);
                System.out.println("Current location: "+locations.get(level));
            } else if (points>=1500 && points<2500) {
                level=5;
                weapon=Weapon.MACHINE_GUN;
                System.out.println("Congratulations! You move to level 5. Your new weapon is: "+weapon);
                System.out.println("Current location: "+locations.get(level));
            } else if (points>=5000) {
                System.out.println("You finish the game! Start again the game!");
                System.out.println("Date and time of finish the game: "+ LocalDateTime.now());
                return true;
            }
        return false;
    }

    boolean FightFinalBoss(){

        if(level!=5){
            System.out.println("You are not at level 5. You have to fight with zombies to reach here.");
            return false;
        }
        else {
            FinalBoss fb = new FinalBoss();
            int damageTaken = fb.getDamage();
            System.out.println("Damage received from Final Boss: "+damageTaken);
            life-=damageTaken;
            if(life>0){
                System.out.println("Life: "+life);
                return false;
            }
            else {
                System.out.println("You are dead! You lose! Quitting game...");
                return true;
            }
        }
    }
    boolean getHealth(){
        addingConsumables();
        Health consumable= consumables.get(new Random().nextInt(1,4));
        System.out.println("You eat a "+consumable+". Your health increased with "+consumable.getHealth());
        life+= consumable.getHealth();
        if(life>100)
            life=100;
        return false;
    }

    boolean Fight(){
        Zombie z = new Zombie();
        int takenDamage;
        takenDamage=z.fight();
        System.out.println("Damage received from Zombie: "+takenDamage);
        life-=takenDamage;
        if(life>0){
            System.out.println("Life: "+life);
            return false;
        }
        else {
          System.out.println("You are dead! You lose! Quitting game...");
          return true;
        }
    }
    boolean useWeapon(){
        System.out.println("Your current weapon: "+weapon);
        return false;
    }

    boolean currentLocation(){
        System.out.println("Current location: "+locations.get(level));
        return false;
    }


}
