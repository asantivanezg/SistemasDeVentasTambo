
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;
import modelo.Producto;


/**
 *
 * @author Eguiguren
 */
public class Ctrl_Cliente {
    
    //Metodo para guardar un nuevo cliente
    
    public boolean guardar(Cliente objeto){
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            
            PreparedStatement consulta = cn.prepareStatement("insert into tb_cliente values(?,?,?,?,?,?,?)");//? son los numeros de atributos que cubre
            consulta.setInt(1, 0);//id
            consulta.setString(2, objeto.getNombre());
            consulta.setString(3, objeto.getApellido());
            consulta.setString(4, objeto.getDni());
            consulta.setString(5, objeto.getTelefono());
            consulta.setString(6, objeto.getDireccion());
            consulta.setInt(7, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0 ) {
                
                respuesta =true;
            }
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        return respuesta;
    }
    
    //Metodo para consultar si el producto ya esta registrado en la base de datos
    
    public boolean existeCliente(String dni){
        boolean respuesta = false;
        String sql = "select dni from tb_cliente where nombre = '" +dni+ "';";
        Statement st;
        
        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                respuesta = true;
            }
           
            
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }
    
    //Metodo de actualizar un cliente
    
    public boolean actualizar(Cliente objeto, int idCliente){
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {
            
            PreparedStatement consulta = cn.prepareStatement(
                    "update  tb_cliente set nombre = ?, apellido = ?, dni = ?, telefono = ?, direccion = ?,estado = ?  where idCliente ='" +idCliente + "'");//Actualizar la categoria que seleccione
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2, objeto.getApellido());
            consulta.setString(3, objeto.getDni());
            consulta.setString(4, objeto.getTelefono());
            consulta.setString(5, objeto.getDireccion());
            consulta.setInt(6, objeto.getEstado());
            
            if (consulta.executeUpdate() > 0 ) {
                
                respuesta =true;
            }
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    }
    
    //Metodo de elimina un cliente
    
    public boolean eliminar(int idCliente){
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            
            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_cliente where idCliente ='" +idCliente + "'");//Eliminar la categoria que seleccione
            consulta.executeUpdate();
            
            if (consulta.executeUpdate() > 0 ) {
                
                respuesta =true;
            }
            cn.close();
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }
    
}
