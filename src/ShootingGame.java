import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class ShootingGame extends Game<Shooter>{

    public ShootingGame(String gameName) {
        super(gameName);
    }
    @Override
    public Shooter createNewPlayer(String name) {
        return new Shooter(name,100,0);
    }

    @Override
    public Map<Character, GameAction> getGameActions(int index) {
        loadingData(index);
        Map<Character,GameAction> actionMap;
        actionMap=getActions();
        actionMap.put('S',new GameAction('S',"Shooting ",s->this.Shoot(index)));
        actionMap.put('W',new GameAction('W',"Weapon ",s->this.Weapon(index)));
        actionMap.put('L',new GameAction('L',"Current location ",s->this.currentLocation(index)));
        actionMap.put('F',new GameAction('F',"Fighting with zombie ",s->this.Fight(index)));
        actionMap.put('H',new GameAction('H',"Getting health ",s->this.Health(index)));
        actionMap.put('B',new GameAction('B',"Fighting final boss ",s->this.FinalBoss(index)));
        actionMap.put('P',new GameAction('P',"Save your points and quit ",s->this.savePoints(index)));
        return actionMap;
    }

    private boolean savePoints(int index){
        int points = getPlayers(index).getPoints();
        putInFile("leaderboard.txt",points,getPlayers(index).name());
        return true;
    }
    public boolean FinalBoss(int index){
        return getPlayers(index).FightFinalBoss();
    }

    public boolean Health(int index){
        return getPlayers(index).getHealth();
    }

    public boolean Fight(int index){
        return getPlayers(index).Fight();
    }

    private void loadingData(int player){
        getPlayers(player).loadingData();
    }

    public boolean Shoot(int index){
        return getPlayers(index).Shoot();
    }

    public boolean Weapon(int index){
        return getPlayers(index).useWeapon();
    }

    public boolean currentLocation(int index){
        return getPlayers(index).currentLocation();
    }

    private static void putInFile(String fileName, int points, String name) {

        File log = new File(fileName);

        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("-------- " + name+": "+points+" ---------- " + "\n");
            bufferedWriter.close();

            System.out.println("Done");
        } catch(IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

}
