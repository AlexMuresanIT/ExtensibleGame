import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Game<T extends Player>  {

    private final String gameName;
    private final List<T> players;
    private Map<Character,GameAction> actions;

    public Game(String gameName) {
        this.gameName = gameName;
        this.players=new ArrayList<>();
        this.actions=new LinkedHashMap<>();
    }

    public String getGameName() {
        return gameName;
    }

    public T getPlayers(int index) {
        return players.get(index);
    }

    public Map<Character, GameAction> getActions() {

        actions.put('I',new GameAction('I',"Player information",this::printPlayer));
        actions.put('Q',new GameAction('Q',"Quit game",this::quitGame));
        return actions;
    }

    public abstract T createNewPlayer(String name);
    public abstract Map<Character,GameAction> getGameActions(int index);
    public int addPlayer(String name){
        T player = createNewPlayer(name);
        players.add(player);
        return players.size()-1;
    }
    public boolean executeGameAction(int index,GameAction action){
        return action.action().test(index);
    }

    public boolean printPlayer(int player){
        Player player1 = players.get(player);
        System.out.println(player1);
        return false;
    }

    public boolean quitGame(int player){
        System.out.println("The game for player "+players.get(player).name()+" is ending. Thank you for playing.");
        return true;
    }
}
