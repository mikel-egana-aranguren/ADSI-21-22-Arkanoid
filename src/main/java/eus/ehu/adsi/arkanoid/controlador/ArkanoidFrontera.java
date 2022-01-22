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
    private String correoArkanoid = "arkanoidrecovery@gmail.com";
    private String contrasenaArkanoid = "ARKpassword";

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
     * Verificar el estado del inicio de sesión (Vista de Iniciar Sesión)
     * @param nombreUsuario el usuario que intenta iniciar sesión
     * @param contrasena contraseña con la intenta iniciar sesión
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarInicio(String nombreUsuario, String contrasena) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Buscar el usuario por su nombre de usuario
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        //Comprobar si el usuario existe
        if (U != null) {

            //Comprobar si la contraseña que ha introducido es la correcta para su cuenta
            correcto = GestorUsuarios.getGestorUsuarios().esContrasena(U, contrasena);

            if (!correcto) {
                //Es incorrecta, guardar un mensaje indicando la situación
                resultado.put("mensaje","Contraseña incorrecta.");
            } else {
                //Es correcta, inicio correcto, guardar el nombre de usuario
                resultado.put("mensaje", nombreUsuario);
            }
        } else {
            //No existe, guardar un mensaje indicando la situación
            resultado.put("mensaje", "El usuario no está registrado.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar recuperación de contraseña (Vista de Recuperar Contraseña | 1ª pantalla)
     * @param correo correo de la cuenta que se quiere recuperar la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = código que se ha enviado al correo
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject recuperarContrasena(String correo) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que el campo no esté vacío
        if (!correo.equals("")) {

            //Comprobar que el email tenga un formato válido
            if (correoValido(correo)) {

                //Buscar el usuario por su correo
                Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                //Comprobar si el usuario existe
                if (U != null) {

                    //El usuario existe, mandar el código de recuperación al correo y guardar el código
                    resultado.put("mensaje", enviarEmail(correo));
                    //Indicar estado de la recuperación
                    correcto = true;

                } else {
                    //No existe, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "El usuario no está registrado.");
                }
            } else {
                //No lo tiene, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Correo no válido.");
            }
        } else {
            //Está vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Introduce un correo.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar el formato del correo
     * @param correo correo al que verificar formato
     * @return True si válido | False sino
     */
    private boolean correoValido(String correo) {

        //Expresión regular del correo electrónico
        String regex = "^(.+)@(.+)$";
        //Compilar el patrón que se busca (expresión regular)
        Pattern pattern = Pattern.compile(regex);
        //Comparar patrón con el correo
        Matcher matcher = pattern.matcher(correo);

        //Devolver si cumple o no
        return matcher.matches();
    }

    /**
     * Enviar un email con un código de recuperación de 6 dígitos
     * @param correo correo destino
     * @return código que se ha enviado
     */
    public String enviarEmail(String correo) {

        //Fuente: https://www.youtube.com/watch?v=Dj1t53SH7nk&t=703s

        //Comprobar que se haya inicializado la variable de propiedades
        if (propiedad == null) {
            //No se ha hecho, crear nuevo objeto
            propiedad = new Properties();

            //Añadir propiedades necesarias
            propiedad.put("mail.smtp.host", "smtp.gmail.com");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.port", "587");
            propiedad.put("mail.smtp.auth", "true");

            //Iniciar la sesión
            sesion = Session.getDefaultInstance(propiedad);
        }

        //Iniciar un nuevo mensaje en la sesión
        MimeMessage mail = new MimeMessage(sesion);
        //Inicializar la variable del código
        String codigo = "";

        //Intentar enviar email
        try {
            //Definir elementos del email
            //Emisor
            mail.setFrom(new InternetAddress(contrasenaArkanoid));
            //Receptor
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            //Sujeto
            mail.setSubject("[Arkanoid] Recuperar Contraseña");
            //Obtener código
            codigo = String.format("%06d", generarNumeroAleatorio(999999,100000));
            //Cuerpo (texto)
            mail.setText("El código de recuperación es: " + codigo);

            //Iniciar transporte SMTP
            Transport transporte = sesion.getTransport("smtp");
            //Conectar con cuenta de Arkanoid
            transporte.connect(correoArkanoid, contrasenaArkanoid);
            //Mandar mensaje
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            //Cerrar transporte
            transporte.close();

        //Capturar excepciones
        } catch (AddressException e) {
            return "";
        } catch (MessagingException e) {
            return "";
        }
        return codigo;
    }

    /**
     * Verificar recuperación de contraseña (Vista de Recuperar Contraseña | 2ª pantalla)
     * @param correo correo de la cuenta que quiere recuperar la contraseña
     * @param codigo código que se ha enviado a su correo (CORRECTO)
     * @param codigoIntroducido código que ha introducido el usuario
     * @param cNueva1 contraseña nueva que quiere el usuario
     * @param cNueva2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = vacío
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(correo.equals("") || codigo.equals("") || codigoIntroducido.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            //Buscar el usuario por su correo
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

            //Comprobar si el usuario existe
            if (U != null) {

                //Comprobar si el código enviado y el introducido conciden
                if (codigo.equals(codigoIntroducido)) {

                    //Comprobar si las dos contraseñas conciden
                    if (cNueva1.equals(cNueva2)) {

                        //Comprobar que la contraseña tenga un formato válido
                        if (contrasenaValida(cNueva1)) {

                            //Comprobar que la contraseña que quiere usar no es la misma que la que tenía
                            if (!GestorUsuarios.getGestorUsuarios().esContrasena(U, cNueva1)) {
                                //Cambiar la contraseña
                                GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                                //Indicar estado de la recuperación
                                correcto = true;
                            } else {
                                //Es la misma, guardar un mensaje indicando la situación
                                resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                            }
                        } else {
                            //No lo tiene, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //Son distintas, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    //Son distintos, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "Código incorrecto.");
                }
            } else {
                //No existe, guardar un mensaje indicando la situación
                resultado.put("mensaje", "El usuario no está registrado.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar el registro de una cuenta usuario
     * @param nombreUsuario nombre del usuario
     * @param correo correo electrónico
     * @param contrasena1 contraseña
     * @param contrasena2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(nombreUsuario.equals("") || correo.equals("") || contrasena1.equals("") || contrasena2.equals(""))) {
            //Buscar el usuario por su nombre de usuario
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

            //Comprobar que el usuario no exista
            if (U == null) {
                //Buscar el usuario por su correo
                U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

                //Comprobar que el usuario no exista
                if (U == null) {

                    //Comprobar que el correo tenga un formato válido
                    if (correoValido(correo)) {

                        //Comprobar que el nombre de usuario tenga un formato válido
                        if (nombreUsuario.length() <= 20) {

                            //Comprobar si las dos contraseñas conciden
                            if (contrasena1.equals(contrasena2)) {

                                //Comprobar que la contraseña tenga un formato válido
                                if (contrasenaValida(contrasena1)) {
                                    //Registrar el usuario
                                    GestorUsuarios.getGestorUsuarios().registrarUsuario(nombreUsuario, correo, contrasena1);
                                    //Indicar estado de la recuperación
                                    correcto = true;
                                    //Guardar el nombre de usuario
                                    resultado.put("mensaje", nombreUsuario);
                                } else {
                                    //No tiene formato válido, guardar un mensaje indicando la situación
                                    resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                                }
                            } else {
                                //No coinciden, guardar un mensaje indicando la situación
                                resultado.put("mensaje", "Las contraseñas no coinciden.");
                            }
                        } else {
                            //No tiene formato válido, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de nombre de usuario no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //No tiene formato válido, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Correo no válido.");
                    }
                } else {
                    //Sí existe, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "Correo no disponible.");
                }
            } else {
                //Sí existe, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Nombre de usuario no disponible.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar cambio de contraseña
     * @param nombreUsuario nombre de usuario de la cuenta que quiere cambiar de contraseña
     * @param cAnterior contraseña que tiene actualmente la cuenta
     * @param cNueva1 contraseña nueva que se quiere poner
     * @param cNueva2 confirmación de la contraseña
     * @return un JSON con la forma:
     *  { estado : boolean, mensaje : String }
     *  Si es True, String = nombreUsuario
     *  Si es False, String = mensaje de error correspondiente
     */
    public JSONObject comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2) {

        //Inicializar variables
        //Estado del inicio (por defecto false)
        boolean correcto = false;
        //JSON a devolver
        JSONObject resultado = new JSONObject();

        //Comprobar que todos los campos estén rellenos
        if (!(nombreUsuario.equals("") || cAnterior.equals("") || cNueva1.equals("") || cNueva2.equals(""))) {
            //Buscar el usuario por su nombre de usuario
            Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

            //Comprobar si la contrasena anterior es correcta
            if (GestorUsuarios.getGestorUsuarios().esContrasena(U, cAnterior)) {

                //Comprobar que la contraseña que quiere usar no es la misma que la que tenía
                if (!cAnterior.equals(cNueva1)) {

                    //Comprobar si las dos contraseñas conciden
                    if (cNueva1.equals(cNueva2)) {

                        //Comprobar que la contraseña tenga un formato válido
                        if (contrasenaValida(cNueva1)) {
                            //Cambiar la contraseña
                            GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                            //Indicar estado de la recuperación
                            correcto = true;
                            //Guardar el nombre de usuario
                            resultado.put("mensaje", nombreUsuario);
                        } else {
                            //No tiene formato válido, guardar un mensaje indicando la situación
                            resultado.put("mensaje", "Formato de contraseña no válido (longitud máxima 20 caracteres).");
                        }
                    } else {
                        //No coinciden, guardar un mensaje indicando la situación
                        resultado.put("mensaje", "Las contraseñas no coinciden.");
                    }
                } else {
                    //Es la misma, guardar un mensaje indicando la situación
                    resultado.put("mensaje", "La contraseña nueva no puede ser igual a la anterior.");
                }
            } else {
                //No es correcta, guardar un mensaje indicando la situación
                resultado.put("mensaje", "Contraseña incorrecta.");
            }
        } else {
            //Hay alguno vacío, guardar un mensaje indicando la situación
            resultado.put("mensaje", "Rellena todos los campos.");
        }
        //Poner el estado del inicio en el JSON
        resultado.put("estado", correcto);
        //Devolver JSON
        return resultado;
    }

    /**
     * Verificar formatode la contraseña
     * @param contrasena contraseña a verificar
     * @return True si válido | False sino
     */
    private boolean contrasenaValida(String contrasena) {
        return contrasena.length() <= 20;
    }

    // MÉTODOS PARA PRUEBAS

    /**
     * Borrar todos los usuarios
     */
    public void borrarUsuarios() {
        GestorUsuarios.getGestorUsuarios().borrarUsuarios();
    }
}
