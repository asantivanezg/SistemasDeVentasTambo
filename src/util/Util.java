package util;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Axel
 */
public class Util {

    public static void CentrarPanel(JDesktopPane pan, JInternalFrame inf) {
        Dimension desktopSize = pan.getSize();
        Dimension jifSize = inf.getSize();
        inf.setLocation((desktopSize.width - jifSize.width) / 2,
                (desktopSize.height - jifSize.height) / 2 - 80);
    }
    
    
    public static boolean validarNumero(String valor) {
        int num;

        try {
            num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
