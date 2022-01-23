package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCuenta17d extends JFrame {

    private JTextField nombre;
    private JTextField correo;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;
    private JButton cancelar;
    private JButton crear;
    private Font impact = AddFont.createFont();

    public CrearCuenta17d() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Registrarse");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);
    }

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {
        this.setLayout(new BorderLayout());

        JPanel north = new JPanel();
        north.setLayout(new GridLayout(2, 0));
        north.setBackground(Color.BLACK);
        north.setSize(new Dimension(Config.SCREEN_HEIGHT/3, Config.SCREEN_WIDTH));
        JLabel titulo = new JLabel("Introduce tus datos:", JLabel.CENTER);
        titulo.setFont(impact.deriveFont(50.0f));
        titulo.setForeground(Color.WHITE);
        JLabel titulo2 = new JLabel("Introduce tus datos:");
        titulo2.setFont(impact.deriveFont(30.0f));
        titulo2.setForeground(Color.BLACK);
        north.add(titulo);
        north.add(titulo2);
        this.add(north, BorderLayout.NORTH);
        
        JPanel campos = new JPanel();
        campos.setBackground(Color.BLACK);
        GridBagLayout gbl_campos = new GridBagLayout();
			gbl_campos.columnWidths = new int[]{20,100, 100,20};
			gbl_campos.rowHeights = new int[]{100, 100, 100, 100};
			gbl_campos.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_campos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        campos.setLayout(gbl_campos);

        JLabel textoNombre = new JLabel("Nombre:  ", JLabel.RIGHT);
        textoNombre.setForeground(Color.WHITE);
        textoNombre.setFont(impact.deriveFont(30.0f));
        GridBagConstraints gbc_lblTNombre = new GridBagConstraints();
		gbc_lblTNombre.anchor = GridBagConstraints.EAST;
		gbc_lblTNombre.insets = new Insets(0, 0, 0, 0);
		gbc_lblTNombre.gridx = 1;
		gbc_lblTNombre.gridy = 0;
		campos.add(textoNombre, gbc_lblTNombre);

        nombre = new JTextField("", 30);
        GridBagConstraints gbc_TFNombre = new GridBagConstraints();
		gbc_TFNombre.anchor = GridBagConstraints.WEST;
		gbc_TFNombre.insets = new Insets(0, 0, 0, 0);
		gbc_TFNombre.gridx = 2;
		gbc_TFNombre.gridy = 0;
		campos.add(nombre, gbc_TFNombre);

        JLabel textoCorreo = new JLabel("Correo:  ", JLabel.RIGHT);
        textoCorreo.setForeground(Color.WHITE);
        textoCorreo.setFont(impact.deriveFont(30.0f));
        GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.EAST;
		gbc_lblCorreo.insets = new Insets(0, 0, 0, 0);
		gbc_lblCorreo.gridx = 1;
		gbc_lblCorreo.gridy = 1;
		campos.add(textoCorreo, gbc_lblCorreo);

        correo = new JTextField("", 30);
        GridBagConstraints gbc_TFCorreo = new GridBagConstraints();
		gbc_TFCorreo.anchor = GridBagConstraints.WEST;
		gbc_TFCorreo.insets = new Insets(0, 0, 0, 0);
		gbc_TFCorreo.gridx = 2;
		gbc_TFCorreo.gridy = 1;
		campos.add(correo, gbc_TFCorreo);
    
        JLabel textoContrasena1 = new JLabel("Contraseña:  ", JLabel.RIGHT);
        textoContrasena1.setForeground(Color.WHITE);
        textoContrasena1.setFont(impact.deriveFont(30.0f));
        GridBagConstraints gbc_lblContra1 = new GridBagConstraints();
		gbc_lblContra1.anchor = GridBagConstraints.EAST;
		gbc_lblContra1.insets = new Insets(0, 0, 0, 0);
		gbc_lblContra1.gridx = 1;
		gbc_lblContra1.gridy = 2;
		campos.add(textoContrasena1, gbc_lblContra1);

        contrasena1 = new JPasswordField("", 30);
        GridBagConstraints gbc_PFContra1 = new GridBagConstraints();
		gbc_PFContra1.anchor = GridBagConstraints.WEST;
		gbc_PFContra1.insets = new Insets(0, 0, 0, 0);
		gbc_PFContra1.gridx = 2;
		gbc_PFContra1.gridy = 2;
		campos.add(contrasena1, gbc_PFContra1);
        

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:  ", JLabel.RIGHT);
        textoContrasena2.setForeground(Color.WHITE);
        textoContrasena2.setFont(impact.deriveFont(28.0f));
        GridBagConstraints gbc_lblContra2 = new GridBagConstraints();
		gbc_lblContra2.anchor = GridBagConstraints.EAST;
		gbc_lblContra2.insets = new Insets(0, 0, 0, 0);
		gbc_lblContra2.gridx = 1;
		gbc_lblContra2.gridy = 3;
		campos.add(textoContrasena2, gbc_lblContra2);

        contrasena2 = new JPasswordField("", 30);
        GridBagConstraints gbc_PFContra2 = new GridBagConstraints();
		gbc_PFContra2.anchor = GridBagConstraints.WEST;
		gbc_PFContra2.insets = new Insets(0, 0, 0, 0);
		gbc_PFContra2.gridx = 2;
		gbc_PFContra2.gridy = 3;
		campos.add(contrasena2, gbc_PFContra2);
     

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Color.BLACK);
        botones.add(botonCancelar());
        botones.add(botonCrear());
        this.add(botones, BorderLayout.PAGE_END);
    }

    //Método para crear el botón de cancelar, que abrirá la pantalla de Inicio
    private JButton botonCancelar() {
        if (cancelar == null) {
            cancelar = new JButton("Cancelar");
            cancelar.setFont(impact.deriveFont(20.0f));
            cancelar.setBorderPainted(false);
            cancelar.setFocusPainted(false);
            cancelar.setContentAreaFilled(false);
            cancelar.setForeground(Color.WHITE);

            cancelar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cerrarActual();
                    new Inicio16();
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    cancelar.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    cancelar.setForeground(Color.WHITE);
                }
            });
        }
        return cancelar;
    }

    //Método para crear el botón de crear cuenta, que mandará a comprobar los datos
    private JButton botonCrear() {
        if (crear == null) {
            crear = new JButton("Crear cuenta");
            crear.setFont(impact.deriveFont(20.0f));
            crear.setBorderPainted(false);
            crear.setFocusPainted(false);
            crear.setContentAreaFilled(false);
            crear.setForeground(Color.WHITE);

            crear.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Intentar crear una cuenta con los datos proporcionados
                    JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro(nombre.getText(), correo.getText(), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
                    //Comprobar el estado del registro
                    if (!resultado.getBoolean("estado")) {
                        //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                        new MensajeError((String) resultado.get("mensaje"));

                    } else {
                        //Si ha sido exitoso, cerrar pantalla actual
                        cerrarActual();
                        //Abrir pantalla de Menú Principal con el nombre de usuario del jugador
                        new MenuPrincipal18((String) resultado.get("mensaje"));
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    crear.setForeground(Color.RED);
                }
                @Override
                public void mouseExited(MouseEvent e){
                    crear.setForeground(Color.WHITE);
                }
            });
        }
        return crear;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
