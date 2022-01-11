package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import org.json.JSONObject;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IdentificacionTests {

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		ArkanoidFrontera.getArkanoidFrontera().borrarUsuarios();
	}

	//Caso de uso Iniciar Sesión
	// Antes de empezar las pruebas nos encontramos en la página de inicio de sesión.

	@Test
	public void F2P1() {
		//Descripción: Nombre de usuario correcto y contraseña correcta, se pulsa “Iniciar sesión”
		//Resultado Esperado: Inicio de sesión correcto, se accede al Menú Principal
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("usuario", "contrasena");
		assertTrue(resultado.getBoolean("estado"));

	}

	@Test
	public void F2P2() {
		//Descripción: Nombre de usuario correcto y contraseña incorrecta o vacía, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("usuario", "incorrecto");
		assertEquals("Contraseña incorrecta.", resultado.get("mensaje"));
	}

	@Test
	public void F2P3() {
		//Descripción: Nombre de usuario incorrecto o vacío y contraseña correcta, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("incorrecto", "contrasena");
		assertEquals("El usuario no está registrado.", resultado.get("mensaje"));
	}

	@Test
	public void F2P4() {
		//Descripción: Nombre de usuario incorrecto o vacío y contraseña incorrecta o vacía, se pulsa “Iniciar sesión”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio("incorrecto", "incorrecto");
		assertFalse(resultado.getBoolean("estado"));
	}

	@Test
	public void F2P5() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de Inicio
		fail();
	}

	@Test
	public void F2P6() {
		//Descripción: Se pulsa “He olvidado mi contraseña”
		//Resultado Esperado: Se pasa a la página para introducir el correo de recuperación
		fail();
	}

	//Subcaso de uso Recuperar Contraseña
	// Antes de empezar las pruebas nos encontramos en la página para introducir el correo de recuperación.

	@Test
	public void F2P7() {
		//Descripción: En la página para introducir el correo se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de inicio de sesión
		fail();
	}

	@Test
	public void F2P8() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” sin haber puesto ningún correo
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("");
		assertEquals("Introduce un correo.", resultado.get("mensaje"));
	}

	@Test
	public void F2P9() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” habiendo puesto un correo no válido
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("correo");
		assertEquals("Correo no válido.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10() {
		//Descripción: En la página para introducir el correo se pulsa “Enviar correo” habiendo puesto un correo válido
		//Resultado Esperado: Se envía al correo un código de recuperación y se pasa a la página de recuperación de
		// contraseña
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena("correo@ehu.eus");
		assertTrue(resultado.getBoolean("estado"));
	}

	@Test
	public void F2P10_1() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página para introducir el correo de recuperación y el usuario puede
		// introducir otro correo
		fail();
	}

	@Test
	public void F2P10_2() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se pulsa
		// “Volver a enviar”
		//Resultado Esperado: Se envía al correo un nuevo código de recuperación
		fail();
	}

	@Test
	public void F2P10_3() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce un código incorrecto, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "000000", "contrasena", "contrasena");
		assertEquals("Código incorrecto.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_4() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y falta la contraseña en uno o dos de los campos, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "", "contrasena");
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_5() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados, pero la contraseña de cada
		// uno es distinta, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasena1", "contrasena2");
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_6() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña,
		// pero el formato de la contraseña no es válido, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_7() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña,
		// pero es igual a la contraseña anterior del usuario, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasena", "contrasena");
		assertEquals("La contraseña nueva no puede ser igual a la anterior.", resultado.get("mensaje"));
	}

	@Test
	public void F2P10_8() {
		//Descripción: Tras la prueba F2P10 (ahora nos encontramos en la página de recuperación de contraseña), se
		// introduce el código correcto y los dos campos de contraseña están completados y con la misma contraseña, con
		// formato adecuado, se pulsa “Aceptar”
		//Resultado Esperado: Se almacena la nueva contraseña del usuario. Se vuelve a la página de inicio de sesión
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo("correo@ehu.eus", "123456", "123456", "contrasenaNueva", "contrasenaNueva");
		assertTrue(resultado.getBoolean("estado"));
	}

	//Caso de uso Registrarse
	// Antes de empezar las pruebas nos encontramos en la página de creación de cuenta.

	@Test
	public void F2P11() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve a la página de Inicio
		fail();
	}

	@Test
	public void F2P12() {
		//Descripción: Hay algún campo sin completar, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("", "correo@ehu.eus", "contrasena", "contrasena");
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P13() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos, pero los dos campos de
		// contraseña no coinciden, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena1", "contrasena2");
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P14() {
		//Descripción: Todos los campos están completados, nombre de usuario válido y los dos campos de contraseña
		// coinciden, pero el correo no es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo", "contrasena", "contrasena");
		assertEquals("Correo no válido.", resultado.get("mensaje"));
	}

	@Test
	public void F2P15() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario no tiene un formato válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuarioDeMasDe20Caracteres", "correo@ehu.eus", "contrasena", "contrasena");
		assertEquals("Formato de nombre de usuario no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P16() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario ya está registrado, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo1@ehu.eus", "contrasena1", "contrasena1");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo2@ehu.eus", "contrasena2", "contrasena2");
		assertEquals("Nombre de usuario no disponible.", resultado.get("mensaje"));
	}

	@Test
	public void F2P17() {
		//Descripción: Todos los campos están completados, correo válido y los dos campos de contraseña coinciden, pero
		// el nombre de usuario no tiene un formato válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error

		//Ya se ha contemplado en F2P15
	}

	@Test
	public void F2P18() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos y los dos campos de
		// contraseña coinciden, pero el formato de la contraseña no es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P19() {
		//Descripción: Todos los campos están completados, nombre de usuario y correo válidos y los dos campos de
		// contraseña coinciden y su formato es válido, se pulsa “Crear cuenta”
		//Resultado Esperado: Se crea la cuenta y se almacena su información. El usuario pasa al Menú Principal
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		assertTrue(resultado.getBoolean("estado"));
	}

	//Caso de uso Cambiar Contraseña

	@Test
	public void F2P20() {
		//Descripción: Se pulsa “Cancelar”
		//Resultado Esperado: Se vuelve al Menú Principal
		fail();
	}

	@Test
	public void F2P21() {
		//Descripción: Hay algún campo sin completar, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("", "contrasena", "contrasenaNueva", "contrasenaNueva");
		assertEquals("Rellena todos los campos.", resultado.get("mensaje"));
	}

	@Test
	public void F2P22() {
		//Descripción: Todos los campos están completados, pero la contraseña actual no es correcta, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "incorrecto", "contrasenaNueva", "contrasenaNueva");
		assertEquals("Contraseña incorrecta.", resultado.get("mensaje"));
	}

	@Test
	public void F2P23() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, pero los dos campos de la
		// nueva contraseña no coinciden, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaNueva1", "contrasenaNueva2");
		assertEquals("Las contraseñas no coinciden.", resultado.get("mensaje"));
	}

	@Test
	public void F2P24() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden, pero su formato no es válido, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaMasDe20Caracteres", "contrasenaMasDe20Caracteres");
		assertEquals("Formato de contraseña no válido (longitud máxima 20 caracteres).", resultado.get("mensaje"));
	}

	@Test
	public void F2P25() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden, pero la nueva contraseña es igual a la actual, se pulsa “Aceptar”
		//Resultado Esperado: Mensaje de error
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasena", "contrasena");
		assertEquals("La contraseña nueva no puede ser igual a la anterior.", resultado.get("mensaje"));
	}

	@Test
	public void F2P26() {
		//Descripción: Todos los campos están completados, la contraseña actual es correcta, los dos campos de la nueva
		// contraseña coinciden y su formato es válido, se pulsa “Aceptar”
		//Resultado Esperado: Se cambia y se almacena la nueva contraseña del usuario. El usuario vuelve al Menú
		// Principal
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "correo@ehu.eus", "contrasena", "contrasena");
		JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio("usuario", "contrasena", "contrasenaNueva", "contrasenaNueva");
		assertTrue(resultado.getBoolean("estado"));
	}
}
