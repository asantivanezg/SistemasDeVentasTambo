package controlador;

import dao.CategoriasDao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Categoria;
import util.FormatoCategoria;
import static util.Mensajes.M1;
import vista.InterCategoria;

/**
 *
 * @author Axel
 */
public class CategoriaController implements ActionListener {

    InterCategoria vista;
    Categoria categoria;
    CategoriasDao dao;

    public CategoriaController(InterCategoria vista) {
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        vista.setSize(new Dimension(400, 200));//Ancho de la ventana
        vista.setTitle("Nueva Categoria");
        vista.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            dao = new CategoriasDao();
            categoria = new Categoria();
            String descripcion = vista.txt_descripcion.getText().trim();
            categoria.setDescripcion(descripcion);
            if (!categoria.getDescripcion().isEmpty()) {
                if (!dao.existeCategoria(categoria.getDescripcion())) {
                    if (dao.insertarCategoria(categoria)) {
                        M1("Registro exitoso");
                        FormatoCategoria.limpiar(vista);
                    } else {
                        M1("Error al guardar");
                    }
                } else {
                    M1("La categoria ya esta registrada en la base de datos");
                }
            } else {
                M1("Complete todos los campos");
            }
        }
    }

}
