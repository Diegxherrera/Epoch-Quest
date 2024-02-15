import gameframe.World;

import java.sql.*;

public class DatabaseManager {

    /**
     * This function runs the query to the SQL database using the requestedContent and
     * then returns it in a String. Setting up the query requires of url, username and
     * password to effectively run the Connection making an instance of connection from
     * the JDBC connector
     * @param requestedContent
     * @return Data retrieved in String type
     * @throws SQLException
     */
    public String retrieveDataFromDatabase(String requestedContent, DataSource requestedSource)  {
        String url = "jdbc:mysql://localhost:3306/EpochQuest";
        String username = "root";
        String password = "rootpassword";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT " + requestedContent + " FROM " + requestedSource.toString() +
                    " WHERE currentMap='" + World.getCurrentMap().toString() + "'";
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
                logger.error(e);
            }

            DBFrame.showStoryFrame();
        }
        return "resultSet is not working as expected";
    }
}

enum DataSource {
    ENEMIES,
    PLAYERS,
    MAPS,
    WORLDS
}
