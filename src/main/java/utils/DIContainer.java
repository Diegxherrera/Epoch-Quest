package utils;

import gui.GameFrame;

/**
 * The utils.DIContainer class serves as a Dependency Injection container for the application.
 * It initializes and manages the dependencies between various components of the application,
 * such as the utils.DatabaseManager, utils.GameController, and gameframe.GameFrame. This approach facilitates
 * decoupling between components, making the system more modular and testable.
 */
public class DIContainer {
    // Member variables holding the instances of various components
    private final DatabaseManager databaseManager;
    private final GameController gameController;
    private final GameFrame gameFrame;

    /**
     * Constructs a new utils.DIContainer.
     * This constructor initializes the various components required for the application,
     * namely utils.DatabaseManager, utils.GameController, and gameframe.GameFrame. It also sets the dependencies
     * for each of these components, linking them together for coordinated functioning.
     */
    public DIContainer() {
        this.gameController = new GameController(this);
        this.databaseManager = new DatabaseManager(this);
        this.gameFrame = new GameFrame(this);

        this.gameController.setDependencies(this);
        this.databaseManager.setDependencies(this);
        this.gameFrame.setDependencies(this);
    }

    /**
     * Retrieves the utils.DatabaseManager instance.
     *
     * @return The current instance of utils.DatabaseManager.
     */
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    /**
     * Retrieves the utils.GameController instance.
     *
     * @return The current instance of utils.GameController.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Retrieves the gameframe.GameFrame instance.
     *
     * @return The current instance of gameframe.GameFrame.
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }
}