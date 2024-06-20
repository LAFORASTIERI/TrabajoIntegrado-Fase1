package util;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	static Connection con = null; 
    
    public static Connection conectar () {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", "root", "");

        } catch (Exception e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
        return con;
}
}
