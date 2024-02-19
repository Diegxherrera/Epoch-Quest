package gui;

public class World {
    Map[] maps;
    private Map currentMap;
    private HistoryEra historyLine;

    public World() {
        //TODO Constructor to run a new world.
    }

    public Map nextMap() {
        return new Map();
    }

    public Map getCurrentMap() {
        return  currentMap;
    }
}

enum HistoryEra {
    WORLD_WAR_II,
    SPANISH_CIVIL_WAR,
    USA_CIVIL_WAR,
}

