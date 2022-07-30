package controlador;

import dao.CategoriasDao;
import dao.ProductoDao;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Producto;
import util.FormatoGestionarProducto;
import static util.Mensajes.M1;
import vista.InterGestionarProducto;
import static vista.InterGestionarProducto.jTable_productos;

/**
 *
 * @author Axel
 */
public class GestionarProductoController implements ActionListener {

    InterGestionarProducto vista;
    ProductoDao productoDao;
    CategoriasDao categoriaDao;
    Producto producto;
    int idProducto = 0;
    DefaultTableModel modelo;

    public GestionarProductoController(InterGestionarProducto vista) {
        this.vista = vista;
        vista.setSize(new Dimension(900, 500));
        vista.setTitle("Gestionar Productos");
        vista.setVisible(true);

        this.vista.jButton_actualizar.addActionListener(this);
        this.vista.jButton_eliminar.addActionListener(this);
        this.vista.jComboBox_categoria.addActionListener(this);
        this.vista.jComboBox_igv.addActionListener(this);

        productoDao = new ProductoDao();
        categoriaDao = new CategoriasDao();

        //Cargar tabla
        cargarTablaProducto();

        this.vista.jTable_productos.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {

                int fila_point = jTable_productos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idProducto = (int) modelo.getValueAt(fila_point, columna_point);
                    producto = productoDao.buscarPorId(idProducto);
                    FormatoGestionarProducto.completarCampos(vista, producto);
                }
            }

        });

        // cargar combo
        cargarComboCategoria();

        // insertar imagen en nuestro JLabel
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(900, 500, WIDTH));
        vista.jLabel_wallpaper.setIcon(icono);
        vista.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_actualizar) {
            producto = new Producto();
            productoDao = new ProductoDao();
            categoriaDao = new CategoriasDao();
            String igv = "";
            String categoria = "";
            igv = vista.jComboBox_igv.getSelectedItem().toString().trim();
            categoria = vista.jComboBox_categoria.getSelectedItem().toString().trim();

            //Validar los campos 
            if (vista.txt_nombre.getText().isEmpty() || vista.txt_cantidad.getText().isEmpty() || vista.txt_precio.getText().isEmpty()) {
                M1("Complete todos los campos");
            } else {

                if (igv.equalsIgnoreCase("Seleccione Igv:")) {
                    M1("Seleccione Igv");
                } else {
                    if (categoria.equalsIgnoreCase("Seleccione categoria:")) {
                        M1("Seleccione categoria");
                    } else {

                        try {

                            producto.setNombre(vista.txt_nombre.getText().trim());
                            producto.setCantidad(Integer.parseInt(vista.txt_cantidad.getText().trim()));
                            String precioTXT = "";
                            double Precio = 0.0;
                            precioTXT = vista.txt_precio.getText().trim();
                            boolean aux = false;

                            //Si el usuario ingresa , (coma) como punto decimal, lo transformamos a punto (.)
                            for (int i = 0; i < precioTXT.length(); i++) {
                                if (precioTXT.charAt(i) == ',') {
                                    String precioNuevo = precioTXT.replace(",", ".");
                                    Precio = Double.parseDouble(precioNuevo);
                                    aux = true;
                                }
                            }
                            //evaluamos las condiciones
                            if (aux == true) {
                                producto.setPrecio(Precio);
                            } else {
                                Precio = Double.parseDouble(precioTXT);
                                producto.setPrecio(Precio);
                            }

                            producto.setDescripcion(vista.txt_descripcion.getText().trim());

                            //Porcentaje de IGV
                            if (igv.equalsIgnoreCase("Sin Igv")) {
                                producto.setPorcentajeIgv(0);
                            } else if (igv.equalsIgnoreCase("18%")) {
                                producto.setPorcentajeIgv(18);
                            }

                            //idCategoria - Cargar metodo que obtiene la id de categoria 
                            Categoria cat = (Categoria) vista.jComboBox_categoria.getSelectedItem();
                            producto.setCategoria(cat);
                            producto.setEstado(1);

                            if (productoDao.actualizarProducto(producto)) {

                                M1("Registro actualizado");

                                cargarComboCategoria();
                                cargarTablaProducto();
                                vista.jComboBox_igv.setSelectedItem("Seleccione Igv:");
                                FormatoGestionarProducto.limpiar(vista);

                            } else {

                                M1("Error al actualizar");
                            }

                        } catch (HeadlessException | NumberFormatException ex) {
                            M1("Error en: " + ex);
                        }

                    }
                }
            }
        }

        if (e.getSource() == vista.jButton_eliminar) {

            productoDao = new ProductoDao();
            if (idProducto == 0) {
                M1("Seleccione producto");
            } else {
                if (productoDao.eliminarProducto(idProducto)) {
                    M1("Producto Eliminado");
                    cargarTablaProducto();
                    cargarComboCategoria();
                    FormatoGestionarProducto.limpiar(vista);
                } else {
                    M1("Error al eliminar producto");
                }
            }
        }

        if (e.getSource() == vista.jComboBox_categoria) {

        }

        if (e.getSource() == vista.jComboBox_igv) {

        }
    }

    private void cargarTablaProducto() {
        productoDao = new ProductoDao();
        modelo = productoDao.mostrarProductoEnTabla(vista.jTable_productos);
    }

    private void cargarComboCategoria() {
        DefaultComboBoxModel items = categoriaDao.cargarCombo();
        vista.jComboBox_categoria.setModel(items);
    }
}
