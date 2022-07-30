package util;

import vista.InterProducto;

/**
 *
 * @author Axel
 */
public class FormatoProducto {

    public static void limpiar(InterProducto f) {
        f.txt_nombre.setText("");
        f.txt_cantidad.setText("");
        f.txt_precio.setText("");
        f.txt_descripcion.setText("");
    }
}
