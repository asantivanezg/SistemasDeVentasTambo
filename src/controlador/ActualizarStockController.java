package controlador;

import dao.ProductoDao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import modelo.Producto;
import util.FormatoActualizarStock;
import static util.Mensajes.M1;
import util.Util;
import vista.InterActualizarStock;

/**
 *
 * @author Axel
 */
public class ActualizarStockController implements ActionListener {

    InterActualizarStock vista;
    ProductoDao dao;
    Producto producto;

    public ActualizarStockController(InterActualizarStock vista) {
        this.vista = vista;
        this.vista.btn_actualizar.addActionListener(this);
        this.vista.jComboBox_producto.addActionListener(this);
        vista.setTitle("Actualizar Stock");
        vista.setSize(new Dimension(400, 300));
        vista.setVisible(true);

        dao = new ProductoDao();
        cargarComboProducto();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_actualizar) {
            dao = new ProductoDao();

            if (!vista.jComboBox_producto.getSelectedItem().equals("Seleccione producto:")) {
                //Validamos campos vacios
                if (!vista.txt_cantidad_nueva.getText().isEmpty()) {
                    //Validamos que el usuario no ingrese otros caracteres noo numericos 
                    boolean validacion = Util.validarNumero(vista.txt_cantidad_nueva.getText().trim());
                    if (validacion == true) {
                        //validamos que la cantidad sea mayor a cero

                        if (Integer.parseInt(vista.txt_cantidad_nueva.getText()) > 0) {

                            int stockActual = Integer.parseInt(vista.txt_cantidad_actual.getText().trim());
                            int stockNuevo = Integer.parseInt(vista.txt_cantidad_nueva.getText().trim());

                            //stockNuevo = stockActual + stockNuevo;
                            producto.setCantidad(stockNuevo);

                            if (dao.actualizarStock(producto)) {
                                M1("Stock actualizado");
                                vista.jComboBox_producto.setSelectedItem("Seleccione producto:");
                                FormatoActualizarStock.limpiar(vista);
                                this.cargarComboProducto();

                            } else {
                                M1("Error al actualizar");
                            }

                        } else {
                            M1("La cantidad no puede ser cero ni negativo");
                        }

                    } else {
                        M1("En la cantidad no se permite caracteres no numericos");
                    }

                } else {
                    M1("Ingrese una cantidad para sumar el stock del producto");
                }

            } else {
                M1("Seleccione un producto");
            }
        }

        if (e.getSource() == vista.jComboBox_producto) {
            dao = new ProductoDao();
            producto = (Producto) vista.jComboBox_producto.getSelectedItem();
            producto = dao.buscarPorId(producto.getIdProducto());
            vista.txt_cantidad_actual.setText(String.valueOf(producto.getCantidad()));
        }
    }
    
    
    private void cargarComboProducto() {
        DefaultComboBoxModel items = dao.cargarCombo();
        vista.jComboBox_producto.setModel(items);
    }
    
}
