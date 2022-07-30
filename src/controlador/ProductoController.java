package controlador;

import dao.CategoriasDao;
import dao.ProductoDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Producto;
import util.FormatoProducto;
import static util.Mensajes.M1;
import vista.InterProducto;

/**
 *
 * @author Axel
 */
public class ProductoController implements ActionListener {
    
    InterProducto vista;
    ProductoDao productoDao;
    CategoriasDao categoriaDao;
    Producto producto;
    
    public ProductoController(InterProducto vista) {
        this.vista = vista;
        this.vista.jButton_guardar.addActionListener(this);
        vista.setSize(new Dimension(400, 300));
        vista.setTitle("Nuevo Producto");
        vista.setVisible(true);
        
        categoriaDao = new CategoriasDao();
        cargarComboCategoria();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_guardar) {
            producto = new Producto();
            productoDao = new ProductoDao();
            
            String igv = "";
            String categoria = "";
            igv = vista.jComboBox_igv.getSelectedItem().toString().trim();
            categoria = vista.jComboBox_categoria.getSelectedItem().toString().trim();

            //Validar los campos 
            if (vista.txt_nombre.getText().equals("") || vista.txt_cantidad.getText().equals("") || vista.txt_precio.getText().equals("")) {
                
                M1( "Complete todos los campos");

                //Darle color a los campos que faltan llenar
                vista.txt_nombre.setBackground(Color.red);
                vista.txt_cantidad.setBackground(Color.red);
                vista.txt_precio.setBackground(Color.red);
                
            } else {
                //Consulta para ver si el producto ya existe
                if (!productoDao.existeProducto(vista.txt_nombre.getText().trim())) {
                    
                    if (igv.equalsIgnoreCase("Seleccione Igv:")) {
                        
                        M1("Seleccione Igv");
                        
                    } else {
                        if (categoria.equalsIgnoreCase("Seleccione categoria:")) {//equalsIgnoreCase pregunta si una cadena es igual a la otra ignorando mayusculas y minusculas
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
                                
                                if (productoDao.insertarProducto(producto)) {
                                    
                                    M1("Registro guardado");

                                    //Darle color a los campos que faltan llenar
                                    vista.txt_nombre.setBackground(Color.green);
                                    vista.txt_cantidad.setBackground(Color.green);
                                    vista.txt_precio.setBackground(Color.green);
                                    vista.txt_descripcion.setBackground(Color.green);
                                    
                                    cargarComboCategoria();
                                    vista.jComboBox_igv.setSelectedItem("Seleccione Igv:");
                                    FormatoProducto.limpiar(vista);
                                    
                                } else {
                                    
                                    JOptionPane.showMessageDialog(null, "Error al guardar");
                                }
                                
                            } catch (HeadlessException | NumberFormatException ex) {
                                System.out.println("Error en: " + ex);
                            }
                        }
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "El producto ya existe en la Base de Datos");
                }
            }
        }
    }
    
    private void cargarComboCategoria() {
        DefaultComboBoxModel items = categoriaDao.cargarCombo();
        vista.jComboBox_categoria.addItem("Seleccione categoria:");
        vista.jComboBox_categoria.setModel(items);
    }
}
