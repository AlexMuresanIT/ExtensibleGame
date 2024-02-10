public class Main {

    public static void main(String[] args) {

        ShootingGame game1 = new ShootingGame("Shooting Game");
        GameConsole<ShootingGame> console1 = new GameConsole<>(game1);
        int index = console1.addPlayer();
        console1.displayOptions(index);

    }
}