package utils;

import gui.GameFrame;

import java.sql.*;

public class DatabaseManager {
    private final DIContainer container;
    private GameController DBController;
    private GameFrame DBFrame;

    /**
     * This function runs the query to the SQL database using the requestedContent and
     * then returns it in a String. Setting up the query requires of url, username and
     * password to effectively run the Connection making an instance of connection from
     * the JDBC connector
     * @param requestedContent Content requested to run the query
     * @return Data retrieved in String type
     * @throws SQLException Exception thrown whenever the DB is malfunctioning.
     */
    public String retrieveDataFromDatabase(String requestedContent, DataSource requestedSource)  {
        String url = "jdbc:mysql://localhost:3306/EpochQuest";
        String username = "root";
        String password = "rootpassword";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT " + requestedContent + " FROM " + requestedSource.toString() +
                    " WHERE currentMap='" + "'";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            String data;
            if (resultSet.next()) {
                data = resultSet.getString(requestedContent);
                if ("Body".equals(requestedContent)) {
                    data = "<HTML>" + data + "</HTML>";
                }
                connection.close();
                return data;
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("No suitable driver was found.")) {
                //TODO Add Log4J to manage errors: logger.error(e);
            }
            //TODO
        }
        return "resultSet is not working as expected";
    }

    public DatabaseManager(DIContainer container) {
        this.container = container;
        this.DBController = container.getGameController();
        this.DBFrame = container.getGameFrame();
    }

    public void setDependencies(DIContainer container) {
        this.DBFrame = container.getGameFrame();
        this.DBController = container.getGameController();
    }
}

enum DataSource {
    ENEMIES,
    PLAYERS,
    MAPS,
    WORLDS
}
