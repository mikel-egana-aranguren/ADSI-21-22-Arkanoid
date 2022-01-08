package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

public class MensajeError extends JFrame {

    boolean resp=false;

    public MensajeError(String mensaje, boolean pConResp) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (pConResp) {
            initializeResp(mensaje);
        } else {
            initialize(mensaje);
        }
    }

    public boolean getResp() {
        return this.resp;
    }


    private void initialize(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void initializeResp(String mensaje) {

        int valor=JOptionPane.showConfirmDialog(null,mensaje,"AVISO",JOptionPane.YES_NO_OPTION);
        if(valor == JOptionPane.YES_OPTION) {
            resp=true;
        }
        if(valor == JOptionPane.NO_OPTION) {
            resp=false;
        }
    }
}
