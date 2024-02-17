import gameframe.Map;
import gameframe.World;

public class GameController {
    private Player currentPlayer;
    private Enemy currentEnemy;
    private Map currentMap;
    private World availableMaps;
    //TODO Research and add GameState to the GameController.

    public void startGame() {
        loadGame();
    }

    public void endGame() {

    }

    private void loadGame() {

    }

    private void saveGame() {

    }

    private void handleInput() {
        //TODO Investigate on how to handle user input and classes inputs.
    }

    public void setDependencies(DIContainer diContainer) {

    }
}
