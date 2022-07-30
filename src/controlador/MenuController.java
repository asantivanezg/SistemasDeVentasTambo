package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static util.Mensajes.M1;
import util.Util;
import vista.FrmMenu;
import vista.InterActualizarStock;
import vista.InterCategoria;
import vista.InterCliente;
import vista.InterFacturacion;
import vista.InterGestionarCategoria;
import vista.InterGestionarCliente;
import vista.InterGestionarProducto;
import vista.InterGestionarUsuario;
import vista.InterProducto;
import vista.InterUsuario;

/**
 *
 * @author Axel
 */
public class MenuController implements ActionListener {

    FrmMenu vista;

    public MenuController(FrmMenu vista) {
        this.vista = vista;
        this.vista.jMenuItem_actualizar_stock.addActionListener(this);
        this.vista.jMenuItem_nueva_categoria.addActionListener(this);
        this.vista.jMenuItem_nuevo_cliente.addActionListener(this);
        this.vista.jMenuItem_nueva_venta.addActionListener(this);
        this.vista.jMenuItem_gestionar_categorias.addActionListener(this);
        this.vista.jMenuItem_gestionar_cliente.addActionListener(this);
        this.vista.jMenuItem_gestionar_producto.addActionListener(this);
        this.vista.jMenuItem_nuevo_producto.addActionListener(this);
        this.vista.jMenuItem_gestionar_usuario.addActionListener(this);
        this.vista.jMenuItem_cerrar_sesion.addActionListener(this);
        this.vista.jMenuItem_nuevo_usuario.addActionListener(this);
        //this.vista.

//        vista.setSize(new Dimension(1200, 700));
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);//Ajustar el formulario a las dimensiones de nuestra pantalla
        vista.setDefaultCloseOperation(vista.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);//para que aparezca centrado
        vista.setTitle("Sistema de ventas - Tambo");//Nombre de la ventana
        vista.setVisible(true);

        //vista.setLayout(null);
        //jDesktopPane_menu = new JDesktopPane();
        //int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        //int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        // vista.jDesktopPane_menu.setBounds(0, 0, ancho, (alto - 110));//el 0, 0 son las coordenadas de donde inicia la pantalla y el 110 es el ancho de la barra de tarea
        // vista.add(jDesktopPane_menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jMenuItem_actualizar_stock) {
            InterActualizarStock f = new InterActualizarStock();
            ActualizarStockController c = new ActualizarStockController(f);
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_nueva_categoria) {
            InterCategoria f = new InterCategoria();
            CategoriaController c = new CategoriaController(f);
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_nuevo_cliente) {
            InterCliente f = new InterCliente();
            ClienteController c = new ClienteController(f);
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_nueva_venta) {
            InterFacturacion f = new InterFacturacion();
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_gestionar_categorias) {
            InterGestionarCategoria f = new InterGestionarCategoria();
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_gestionar_cliente) {
            InterGestionarCliente f = new InterGestionarCliente();
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_gestionar_producto) {
            InterGestionarProducto f = new InterGestionarProducto();
            GestionarProductoController c = new GestionarProductoController(f);
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_gestionar_usuario) {
            InterGestionarUsuario f = new InterGestionarUsuario();
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_nuevo_producto) {
            InterProducto f = new InterProducto();
            ProductoController c = new ProductoController(f);
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_nuevo_usuario) {
            InterUsuario f = new InterUsuario();
            this.vista.jdpnContenedor.add(f);
            Util.CentrarPanel(vista.jdpnContenedor, f);
        }

        if (e.getSource() == vista.jMenuItem_cerrar_sesion) {
            System.exit(0);
        }
    }

}
