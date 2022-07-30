package dao;

import conexion.ConectarBD;
import modelo.Cliente;
import java.sql.SQLException;

/**
 *
 * @author Axel
 */
public class ClienteDao extends ConectarBD {

    public ClienteDao() {
    }

    public boolean insertarCliente(Cliente cliente) {
        boolean respuesta = false;
        try {

            ps = conexion.prepareStatement("insert into tb_cliente(nombre, apellido, dni, telefono, direccion, estado)  values(?,?,?,?,?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, 1);

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e);
        }
        return respuesta;
    }

    public boolean existeCliente(String dni) {
        boolean respuesta = false;
        String sql = "select dni from tb_cliente where nombre = '" + dni + "';";

        try {
            rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar cliente: " + e);
        }
        return respuesta;
    }

    public boolean actualizarCliente(Cliente cliente) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("update  tb_cliente set nombre = ?, apellido = ?, dni = ?, telefono = ?, direccion = ?, estado = ?  where idCliente =?;");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setInt(6, cliente.getEstado());
            ps.setInt(7, cliente.getIdCliente());

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e);
        }
        return respuesta;
    }

    public boolean eliminarCliente(int idCliente) {
        boolean respuesta = false;
        try {
            ps = conexion.prepareStatement("delete from tb_cliente where idCliente =?;");
            ps.setInt(1, idCliente);

            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
        return respuesta;
    }

}
