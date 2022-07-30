package controlador;

import dao.ClienteDao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Cliente;
import util.FormatoCliente;
import static util.Mensajes.M1;
import vista.InterCliente;

/**
 *
 * @author Axel
 */
public class ClienteController implements ActionListener {

    InterCliente vista;
    ClienteDao dao;
    Cliente cliente;

    public ClienteController(InterCliente vista) {
        this.vista = vista;
        this.vista.jButton_guardar.addActionListener(this);
        vista.setSize(new Dimension(400, 300));
        vista.setTitle("Nuevo Cliente");
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_guardar) {
            dao = new ClienteDao();
            cliente = FormatoCliente.leerCliente(vista);

            if (!cliente.getNombre().isEmpty() && !cliente.getApellido().isEmpty() && !cliente.getDni().isEmpty()) {
                if (!dao.existeCliente(cliente.getDni())) {
                    if (dao.insertarCliente(cliente)) {
                        M1("Registro guardado");
                        FormatoCliente.camposGuardados(vista);
                    } else {
                        M1("Error al guardar");
                    }
                } else {
                    M1("El cliente ya esta registrado en la Base de Datos");
                    FormatoCliente.camposNoRequeridos(vista);
                }

            } else {
                M1("Complete todos los campos");
                FormatoCliente.camposRequeridos(vista);
            }
            //Metodo limpiar
            FormatoCliente.limpiar(vista);
        }
    }

}
