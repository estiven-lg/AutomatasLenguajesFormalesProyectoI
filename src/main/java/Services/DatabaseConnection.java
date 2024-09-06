package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    // Instancia única de la clase
    private static DatabaseConnection instance;
    // Objeto de conexión
    private Connection connection;
    private Statement statement;

    private String username = "sa";
    private String password = "Abcd-1234";
    private String host = "localhost:1433;";
    private String database = "ProyectoI";

    String connectionUrl = String.format(
            "jdbc:sqlserver://%s;database=%s;user=%s;password=%s;trustServerCertificate=true;loginTimeout=10;",
            host, database, username, password
    );

    private DatabaseConnection() throws SQLException {

        try {
            this.connection = DriverManager.getConnection(connectionUrl);

            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static ResultSet executeQuery(String querie) throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();

        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance.statement.executeQuery(querie);
    }

    public Connection getConnection() {
        return connection;
    }

}
