package gui;

import utils.DIContainer;
import utils.DatabaseManager;
import utils.GameController;

public class GameFrame {
    private final DIContainer container;
    private GameController FrameController;
    private DatabaseManager FrameDB;

    public GameFrame(DIContainer container) {
        this.container = container;
        this.FrameController = container.getGameController();
        this.FrameDB = container.getDatabaseManager();
    }

    public void setDependencies(DIContainer container) {
        this.FrameController = container.getGameController();
        this.FrameDB = container.getDatabaseManager();
    }
}
