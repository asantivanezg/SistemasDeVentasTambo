package util;

import java.awt.Color;
import modelo.Cliente;
import vista.InterCliente;

/**
 *
 * @author Axel
 */
public class FormatoCliente {

    public static void limpiar(InterCliente f) {
        f.txt_nombre.setText("");
        f.txt_apellido.setText("");
        f.txt_dni.setText("");
        f.txt_telefono.setText("");
        f.txt_direccion.setText("");
        f.txt_nombre.requestFocus();
    }

    public static void camposRequeridos(InterCliente f) {
        f.txt_nombre.setBackground(Color.red);
        f.txt_apellido.setBackground(Color.red);
        f.txt_dni.setBackground(Color.red);
        f.txt_telefono.setBackground(Color.red);
        f.txt_direccion.setBackground(Color.red);
    }

    public static void camposNoRequeridos(InterCliente f) {
        f.txt_nombre.setBackground(Color.white);
        f.txt_apellido.setBackground(Color.white);
        f.txt_dni.setBackground(Color.white);
        f.txt_telefono.setBackground(Color.white);
        f.txt_direccion.setBackground(Color.white);
    }

    public static void camposGuardados(InterCliente f) {
        f.txt_nombre.setBackground(Color.green);
        f.txt_apellido.setBackground(Color.green);
        f.txt_dni.setBackground(Color.green);
        f.txt_telefono.setBackground(Color.green);
        f.txt_direccion.setBackground(Color.green);
    }

    public static Cliente leerCliente(InterCliente f) {
        Cliente cli = new Cliente();
        cli.setNombre(f.txt_nombre.getText().trim());
        cli.setApellido(f.txt_apellido.getText().trim());
        cli.setDni(f.txt_dni.getText().trim());
        cli.setTelefono(f.txt_telefono.getText().trim());
        cli.setDireccion(f.txt_direccion.getText().trim());
        return cli;
    }
}
