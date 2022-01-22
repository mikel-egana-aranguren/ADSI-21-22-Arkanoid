package eus.ehu.adsi.arkanoid.controlador;

import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ArkanoidFrontera {

    private static ArkanoidFrontera mArkanoidFrontera = null;
    private Properties propiedad = null;
    private Session sesion = null;
    //Datos para JavaxMail
    private String enviador = "arkanoidrecovery@gmail.com";
    private String contra = "ARKpassword";

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        System.out.println(random);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i - j + 1) + j;
    }

    /**
     * Verificar el estado del inicio de sesión
     * @param nombreUsuario el usuario que intenta iniciar sesión
     * @param contrasena contraseña con la intenta iniciar sesión
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarInicio(String nombreUsuario, String contrasena) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (U != null) {
            correcto = GestorUsuarios.getGestorUsuarios().esContrasena(U, contrasena);

            if (!correcto) {
                resultado.put("mensaje","Contraseña incorrecta.");
            } else {
                resultado.put("mensaje", nombreUsuario);
            }
        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject recuperarContrasena(String correo) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        if (!correo.equals("")) {

            if (emailValido(correo)) {

                Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                if (U != null) {

                    resultado.put("mensaje", enviarEmail(correo));
                    correcto = true;

                } else {
                    resultado.put("mensaje", "El usuario no está registrado.");
                }
            } else {
                resultado.put("mensaje", "Correo no válido.");
            }
        } else {
            resultado.put("mensaje", "Introduce un correo.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    private boolean emailValido(String correo) {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public String enviarEmail(String correo) {

        //Fuente: https://www.youtube.com/watch?v=Dj1t53SH7nk&t=703s

        if (propiedad == null) {
            propiedad = new Properties();
            propiedad.put("mail.smtp.host", "smtp.gmail.com");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.port", "587");
            propiedad.put("mail.smtp.auth", "true");

            sesion = Session.getDefaultInstance(propiedad);
        }

        MimeMessage mail = new MimeMessage(sesion);
        String codigo = "";

        try {
            mail.setFrom(new InternetAddress(enviador));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject("[Arkanoid] Recuperar Contraseña");
            codigo = generarCodigo();
            mail.setText("El código de recuperación es: " + codigo);

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(enviador, contra);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return codigo;
    }

    private String generarCodigo() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public JSONObject comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        if (!(correo.equals("") || codigo.equals("") || codigoIntroducido.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

            if (U != null) {

                if (codigo.equals(codigoIntroducido)) {

                    if (cNueva1.equals(cNueva2)) {

                        if (contrasenaValida(cNueva1)) {

                            if (!GestorUsuarios.getGestorUsuarios().esContrasena(U, cNueva1)) {
                                GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                                correcto = true;
                            } else {
                                resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                            }
                        } else {
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    resultado.put("mensaje", "Código incorrecto.");
                }
            } else {
                resultado.put("mensaje", "El usuario no está registrado.");
            }
        } else {
            resultado.put("mensaje", "Rellena todos los campos.");
        }

        resultado.put("estado", correcto);
        return resultado;
    }

    public JSONObject comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        if (!(nombreUsuario.equals("") || correo.equals("") || contrasena1.equals("") || contrasena2.equals(""))) {
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

            if (U == null) {

                U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                if (U == null) {

                    if (emailValido(correo)) {

                        if (nombreUsuario.length() <= 20) {

                            if (contrasena1.equals(contrasena2)) {

                                if (contrasenaValida(contrasena1)) {
                                    GestorUsuarios.getGestorUsuarios().registrarUsuario(nombreUsuario, correo, contrasena1);
                                    correcto = true;
                                    resultado.put("mensaje", nombreUsuario);
                                } else {
                                    resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                                }
                            } else {
                                resultado.put("mensaje", "Las contraseñas no coinciden.");
                            }
                        } else {
                            resultado.put("mensaje", "Formato de nombre de usuario no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        resultado.put("mensaje", "Correo no válido.");
                    }
                } else {
                    resultado.put("mensaje", "Correo no disponible.");
                }
            } else {
                resultado.put("mensaje", "Nombre de usuario no disponible.");
            }
        } else {
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        if (!(nombreUsuario.equals("") || cAnterior.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

            if (GestorUsuarios.getGestorUsuarios().esContrasena(U, cAnterior)) {

                if (!cAnterior.equals(cNueva1)) {

                    if (cNueva1.equals(cNueva2)) {

                        if (contrasenaValida(cNueva1)) {
                            GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                            correcto = true;
                            resultado.put("mensaje", nombreUsuario);
                        } else {
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                }
            } else {
                resultado.put("mensaje", "Contraseña incorrecta.");
            }
        } else {
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    private boolean contrasenaValida(String contrasena) {
        return contrasena.length() <= 20;
    }

    /*MÉTODOS PARA PRUEBAS*/
    public void borrarUsuarios() {
        GestorUsuarios.getGestorUsuarios().borrarUsuarios();
    }
}
