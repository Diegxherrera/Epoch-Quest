package utils;

import gui.GameFrame;
import gui.Map;
import model.Enemy;
import model.Player;
import model.Potion;
import model.Weapon;

public class GameController {
    private final DIContainer container;
    private DatabaseManager controllerDB;
    private GameFrame controllerFrame;
    private Player currentPlayer;
    private Enemy currentEnemy;
    private Map currentMap;
    private Map[] availableMaps;
    Weapon[] weapons;
    Potion[] potions;


    public GameController(DIContainer container) {
        this.container = container;
        this.controllerDB = container.getDatabaseManager();
    }
    //TODO Research and add GameState to the utils.GameController.

    public void startGame() {
        loadGame();
    }

    public void endGame() {

    }

    private void loadGame() {
        //TODO Load all the assets, weapons, potions and characters from DB before starting game.
    }

    private void saveGame() {

    }

    private void handleInput() {
        //TODO Investigate on how to handle user input and classes inputs.
    }

    public void setDependencies(DIContainer diContainer) {

    }


    public void calculateInteraction(){
        //el daño del arma mas el daño de la persona
    }

}
