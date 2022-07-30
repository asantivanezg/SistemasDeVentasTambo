
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Eguiguren
 */
public class Conexion {
    public static Connection conectar(){
        try {
            //Miguel etc prueba de git
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas","root","admin");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
}
