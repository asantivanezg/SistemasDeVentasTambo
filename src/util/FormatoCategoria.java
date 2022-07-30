package util;

import vista.InterCategoria;

/**
 *
 * @author Axel
 */
public class FormatoCategoria {
    
    public static void limpiar(InterCategoria f) {
        f.txt_descripcion.setText("");
        f.txt_descripcion.requestFocus();
    }

}
