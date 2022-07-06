
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
            //Miguel etc
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_sistema_ventas","root","summer01");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
}
