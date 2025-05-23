package gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //driver JDBC
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //direccion de la BBDD MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/mini_steam";
    //usuario y contraseña de acceso a la BD
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
}
