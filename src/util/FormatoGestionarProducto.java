package util;

import modelo.Categoria;
import modelo.Producto;
import vista.InterGestionarProducto;

/**
 *
 * @author Axel
 */
public class FormatoGestionarProducto {

    public static void limpiar(InterGestionarProducto f) {
        f.txt_nombre.setText("");
        f.txt_cantidad.setText("");
        f.txt_precio.setText("");
        f.txt_descripcion.setText("");
        f.jComboBox_igv.setSelectedItem("Seleccione Igv:");
        f.jComboBox_categoria.setSelectedItem("Seleccione categoria:");
    }

    public static void completarCampos(InterGestionarProducto f, Producto producto) {
        f.txt_nombre.setText(producto.getNombre());
        f.txt_cantidad.setText(String.valueOf(producto.getCantidad()));
        f.txt_precio.setText(String.valueOf(producto.getPrecio()));
        f.txt_descripcion.setText(producto.getDescripcion());

        int igv = producto.getPorcentajeIgv();

        switch (igv) {
            case 0:
                f.jComboBox_igv.setSelectedItem("Sin Igv");
                break;
            case 18:
                f.jComboBox_igv.setSelectedItem("18%");
                break;
            default:
                f.jComboBox_igv.setSelectedItem("Seleccione Igv:");
                break;
        }
        Categoria cate = producto.getCategoria();
        f.jComboBox_categoria.setSelectedItem(cate);
    }
}
