package dao;

import conexion.ConectarBD;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import modelo.Categoria;

/**
 *
 * @author Axel
 */
public class CategoriasDao extends ConectarBD {

    public CategoriasDao() {
    }

    public boolean insertarCategoria(Categoria categoria) {

        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("insert into tb_categoria(descripcion, estado) values(?,?);");
            ps.setString(1, categoria.getDescripcion());
            ps.setInt(2, 1);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoria: " + e);
        }
        return respuesta;
    }

    public boolean existeCategoria(String descripcion) {
        boolean respuesta = false;

        try {
            rs = st.executeQuery("select descripcion from tb_categoria where descripcion = '" + descripcion + "';");
            while (rs.next()) {
                respuesta = true;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }

    public boolean actualizarCategoria(Categoria categoria) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("update tb_categoria set descripcion=? where idCategoria =?;");
            ps.setString(1, categoria.getDescripcion());
            ps.setInt(2, categoria.getIdCategoria());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }
        return respuesta;
    }

    public boolean eliminarCategoria(int idCategoria) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("delete from tb_categoria where idCategoria =?;");
            ps.setInt(1, idCategoria);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e);
        }
        return respuesta;
    }

    public DefaultComboBoxModel cargarCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Categoria> list = new ArrayList();
        try {
            rs = st.executeQuery("select idCategoria, descripcion from tb_categoria order by descripcion asc;");
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt(1));
                cat.setDescripcion(rs.getString(2));
                list.add(cat);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar categorias: " + e);
        }
        List<Categoria> pre = new ArrayList<>();
        pre.add(new Categoria(-1, "Seleccione categoria:"));
        pre.addAll(list);
        for (Categoria categoria : pre) {
            items.addElement(categoria);
        }
        return items;
    }
}
