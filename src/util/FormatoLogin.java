package util;

import modelo.Usuario;
import vista.FrmLogin;

/**
 *
 * @author Axel
 */
public class FormatoLogin {

    public static Usuario leerUsuario(FrmLogin f) {
        Usuario usu = new Usuario();
        usu.setUsuario(f.txt_usuario.getText());
        usu.setPassword(f.txt_password.getText());
        return usu;
    }
}
