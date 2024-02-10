import java.util.Map;
import java.util.Scanner;

public class GameConsole<T extends Game<? extends Player>>{

    private static Scanner scanner = new Scanner(System.in);

    private T game;

    public GameConsole(T game) {
        this.game = game;
    }

    public T getGame() {
        return game;
    }

    public int addPlayer(){
        System.out.println("Add your name:");
        String name = scanner.nextLine();
        System.out.println("Welcome to the game "+name);
        return game.addPlayer(name);
    }

    public void displayOptions(int player){

        game.createNewPlayer(game.getGameName());
        Map<Character,GameAction> standardActions = game.getGameActions(player);
        int points = 0;

        boolean playing=true;
        while(playing){

            System.out.println("Game options:");
            System.out.println("----------------------------");
            for(var ac:standardActions.keySet()){
                System.out.println("["+ac+"]"+":"+standardActions.get(ac).prompt());
            }
            System.out.println("----------------------------");
            System.out.println("Choose one option:");
            char op = scanner.nextLine().toUpperCase().charAt(0);
            GameAction action = standardActions.get(op);
            if(game.executeGameAction(player, action)){
                playing=false;
            }
        }
    }






}
