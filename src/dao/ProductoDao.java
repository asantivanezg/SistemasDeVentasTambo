package dao;

import conexion.ConectarBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Producto;
import static util.Mensajes.M1;

/**
 *
 * @author Axel
 */
public class ProductoDao extends ConectarBD {

    public ProductoDao() {
    }

    //Metodo para insertarProducto un nuevo producto
    public boolean insertarProducto(Producto producto) {
        boolean respuesta = false;
        try {

            ps = conexion.prepareStatement("insert into tb_producto(nombre, cantidad, precio, descripcion, porcentajeIgv, idCategoria, estado) values(?,?,?,?,?,?,?)");
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getPorcentajeIgv());
            ps.setInt(6, producto.getCategoria().getIdCategoria());
            ps.setInt(7, 1);

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e);
        }
        return respuesta;
    }

    //Metodo para consultar si el producto ya esta registrado en la base de datos
    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from tb_producto where nombre = '" + producto + "';";

        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }

    //Metodo de actualizar un producto
    public boolean actualizarProducto(Producto producto) {
        boolean respuesta = false;
        try {

            ps = conexion.prepareStatement("update  tb_producto set nombre = ?, cantidad = ?, precio = ?, "
                    + "descripcion = ?, porcentajeIgv = ?,idCategoria = ?, "
                    + "estado = ?  where idCategoria =?;");
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getPorcentajeIgv());
            ps.setInt(6, producto.getCategoria().getIdCategoria());
            ps.setInt(7, producto.getEstado());
            ps.setInt(8, producto.getIdProducto());

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e);
        }
        return respuesta;
    }

    //Metodo de elimina un producto
    public boolean eliminarProducto(int idProducto) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("delete from tb_producto where idProducto =?;");
            ps.setInt(1, idProducto);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
        return respuesta;
    }

    //Metodo para actualizar Stock del producto
    public boolean actualizarStock(Producto producto) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("update tb_producto set cantidad=? where idProducto =?;");
            ps.setInt(1, producto.getCantidad());
            ps.setInt(2, producto.getIdProducto());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto: " + e);
        }
        return respuesta;
    }

    public DefaultComboBoxModel cargarCombo() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Producto> list = new ArrayList();
        try {
            rs = st.executeQuery("select idProducto, nombre from tb_producto order by nombre asc;");
            while (rs.next()) {
                list.add(new Producto(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar productos: " + e);
        }
        List<Producto> pre = new ArrayList<>();
        pre.add(new Producto(-1, "Seleccione producto:"));
        pre.addAll(list);
        for (Producto producto : pre) {
            items.addElement(producto);
        }
        return items;
    }

    public Producto buscarPorId(int idProducto) {
        Producto pro = new Producto();
        try {
            rs = st.executeQuery("select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIgv, c.idCategoria, c.descripcion from tb_producto p inner join tb_categoria c on p.idcategoria = c.idCategoria where idProducto ='" + idProducto + "';");
            while (rs.next()) {
                pro.setIdProducto(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setCantidad(rs.getInt(3));
                pro.setPrecio(rs.getDouble(4));
                pro.setDescripcion(rs.getString(5));
                pro.setPorcentajeIgv(rs.getInt(6));
                Categoria cate = new Categoria(rs.getInt(7), rs.getString(8));
                pro.setCategoria(cate);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al buscar el producto: " + e);
        }
        return pro;
    }

    public DefaultTableModel mostrarProductoEnTabla(JTable tabla) {

        String[] titulo = {"IdProducto", "Nombre", "Cantidad", "Precio", "Descripcion", "IGV", "Categoría", "estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulo);
        tabla.setModel(modelo);

        try {
            rs = st.executeQuery("select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.porcentajeIgv, c.idCategoria, c.descripcion, p.estado from tb_producto As p, tb_categoria As c where p.idcategoria = c.idCategoria;");
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setCantidad(rs.getInt(3));
                prod.setPrecio(rs.getDouble(4));
                prod.setDescripcion(rs.getString(5));
                prod.setPorcentajeIgv(rs.getInt(6));
                Categoria cat = new Categoria(rs.getInt(7), rs.getString(8));
                prod.setCategoria(cat);
                prod.setEstado(rs.getInt(9));
                modelo.addRow(prod.registroProducto());
            }
            rs.close();
        } catch (Exception e) {
            M1("ERROR no se puede recuperar los registros de la tabla categorias.." + e);
        }
        return modelo;
    }
}
