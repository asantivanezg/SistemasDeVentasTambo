package principal;

import controlador.LoginController;
import vista.FrmLogin;

/**
 *
 * @author Axel
 */
public class Main {

    public static FrmLogin fl;
    public static LoginController loginController;
    
    public static void main(String[] args) {

        fl = new FrmLogin();
        loginController = new LoginController(fl);
    }
}
