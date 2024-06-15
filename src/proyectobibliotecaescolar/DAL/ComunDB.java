package proyectobibliotecaescolar.DAL;
import java.sql.*;

public class ComunDB {
    class TipoDB{
        static final int SQLSERVER = 1;
        static final int MYSQL = 2;
    }

    static int TIPODB = TipoDB.SQLSERVER;
    static String connectionURL = "jdbc:sqlserver://localhost:1433;" +
            "database=RegistroBiblioteca;" +
            "user=sa;" +
            "password=123456;" +
            "loginTimeout=30;encrypt=false;trustServerCertificate=false;";

    public static Connection obtenerConexion() throws SQLException
    {
        DriverManager.registerDriver(
                new com.microsoft.sqlserver.jdbc.SQLServerDriver()
        );
        Connection connection = DriverManager.getConnection(connectionURL);
        return connection;
    }
}
