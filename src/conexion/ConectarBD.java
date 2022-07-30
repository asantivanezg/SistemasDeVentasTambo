package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.Mensajes;

/**
 *
 * @author Axel
 */
public class ConectarBD implements Parametros {
public Connection conexion;
    public Statement st;
    public ResultSet rs;
    public PreparedStatement ps;
    public ConectarBD(){
        try{
            Class.forName(DRIVER);
            conexion =  DriverManager.getConnection(RUTA,USUARIO,CLAVE);
            st = conexion.createStatement();            
        }catch(Exception ex){
            Mensajes.M1("ERROR: no se puede conectar a la BD..."+ex);
        }        
    }          
}
