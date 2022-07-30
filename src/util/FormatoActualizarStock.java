package util;

import vista.InterActualizarStock;

/**
 *
 * @author Axel
 */
public class FormatoActualizarStock {

    public static void limpiar(InterActualizarStock f) {
        f.txt_cantidad_actual.setText("");
        f.txt_cantidad_nueva.setText("");
        f.jComboBox_producto.setSelectedIndex(0);
        f.txt_cantidad_nueva.requestFocus();
    }
}
