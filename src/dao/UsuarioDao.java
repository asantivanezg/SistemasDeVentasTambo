package dao;

import conexion.ConectarBD;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Usuario;
import static util.Mensajes.M1;

/**
 *
 * @author Axel
 */
public class UsuarioDao extends ConectarBD {

    public UsuarioDao() {
    }

    public boolean guardar(Usuario usuario) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {
            ps = conexion.prepareStatement("insert into tb_usuario values(?,?,?,?,?,?,?)");

            ps.setInt(1, 0);//id
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getTelefono());
            ps.setInt(7, usuario.getEstado());

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e);
        }
        return respuesta;
    }

    public boolean existeUsuario(String usuario) {
        boolean respuesta = false;
        String sql = "select usuario from tb_usuario where usuario = " + usuario;

        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e);
        }
        return respuesta;
    }

    //Metodo para iniciar session
    public boolean loginUser(Usuario usuario) {

        boolean respuesta = false;

        String sql = "select usuario, password from tb_usuario where usuario = '"
                + usuario.getUsuario() + "'and password = '" + usuario.getPassword() + "' ";

        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion");
            M1("Error al Iniciar Sesion");
        }

        return respuesta;
    }

    //Metodo de actualizar un Usuario
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        try {

            ps = conexion.prepareStatement("update  tb_usuario set nombre = ?, apellido = ?, usuario = ?, password = ?, telefono = ?,"
                    + "estado = ?  where idUsuario ='" + idUsuario + "'");
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getApellido());
            ps.setString(3, objeto.getUsuario());
            ps.setString(4, objeto.getPassword());
            ps.setString(5, objeto.getTelefono());
            ps.setInt(6, objeto.getEstado());

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            M1("Error al actualizar usuario: " + e);
        }
        return respuesta;
    }

    //Metodo de elimina un usuario
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("delete from tb_usuario where idUsuario ='" + idUsuario + "'");
            ps.setInt(1, idUsuario);

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            M1("Error al eliminar usuario: " + e);
        }
        return respuesta;
    }
}
