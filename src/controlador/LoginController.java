package controlador;

import dao.UsuarioDao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.Usuario;
import principal.Main;
import util.FormatoLogin;
import static util.Mensajes.M1;
import vista.FrmLogin;
import vista.FrmMenu;

/**
 *
 * @author Axel
 */
public class LoginController implements ActionListener, KeyListener {

    FrmLogin vista;
    UsuarioDao dao;
    Usuario usuario;

    public LoginController(FrmLogin vista) {
        this.vista = vista;
        this.vista.jButton_IniciarSesion.addActionListener(this);
        this.vista.txt_password.addKeyListener(this);
        this.vista.txt_usuario.addKeyListener(this);
        vista.setResizable(false);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.setTitle("Login - SISTEMA DE VENTAS - TAMBO");
        vista.setSize(new Dimension(700, 500));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_IniciarSesion) {
            doLogin();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {

        if (evt.getSource() == vista.txt_usuario) {
            if (evt.getKeyCode() == evt.VK_ENTER) {
                vista.txt_password.requestFocus();
            }
        }

        if (evt.getSource() == vista.txt_password) {
            if (evt.getKeyCode() == evt.VK_ENTER) {
                doLogin();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void doLogin() {
        dao = new UsuarioDao();
        usuario = FormatoLogin.leerUsuario(vista);
        if (!usuario.getUsuario().isEmpty() && !usuario.getPassword().isEmpty()) {
            if (dao.loginUser(usuario)) {
                FrmMenu menu = new FrmMenu();
                MenuController menuController = new MenuController(menu);
                vista.dispose();
            } else {
                M1("Usuario o Clave Incorrectos");
            }
        } else {
            M1("Ingrese el usuario y contraseña");
        }

    }
}
