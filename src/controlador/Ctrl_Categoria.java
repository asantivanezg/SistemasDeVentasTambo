package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Categoria;

/**
 *
 * @author Eguiguren
 */
public class Ctrl_Categoria {

    //Metodo para registrar categorias
    public boolean guardar(Categoria objeto) {

        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }
        return respuesta;
    }

    //Metodo para consultar si existe la categoria
    public boolean existeCategoria(String categoria) {

        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }

    //Metodo de actualizar una categoria
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "update tb_categoria set descripcion=? where idCategoria ='" + idCategoria + "'");//Actualizar la categoria que seleccione
            consulta.setString(1, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {

                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }
        return respuesta;
    }

    //Metodo de elimina una categoria
    public boolean eliminar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_categoria where idCategoria ='" + idCategoria + "'");//Eliminar la categoria que seleccione
            consulta.executeUpdate();

            if (consulta.executeUpdate() > 0) {

                respuesta = true;
            }
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e);
        }
        return respuesta;
    }
}
