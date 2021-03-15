package menu;

import java.util.Scanner;

/**
 * Clase publica de utilidades.
 *
 * @author Carlos Aragon Pelayo
 */
public class Utilidades {

	/**
	 * Metodo publico para pedir una cadena de caracteres
	 * controlando las excepciones que puedan ocurrir.
	 * <br><br>
	 * <b>Precondiciones: </b>Ninguna.
	 * <br>
	 * <b>Postcondiciones: </b>Devolvera un String.
	 *
	 * @return Un String cadena.
	 */
	public static String pedirCadena() {
		//Declaracion de variables.
		Scanner teclado = new Scanner(System.in);
		String cadena = "";
		boolean error;

		do {

			try {
				cadena = teclado.nextLine();
				error = false;
			} catch (Exception e) {//Excepcion capturada
				System.out.println("Entrada de palabras erronea, prueba otra vez.");//Mensaje de error
				error = true;//Actualizacion de la variable error en caso de excepcion.
			}

		} while (error);//Repeticion del bucle mientras que la variable error sea true.

		return cadena;//Devuelve el String cadena.
	}

	/**
	 * Metodo publico para pedir una numero controlando las
	 * excepciones que puedan ocurrir.
	 * <br><br>
	 * <b>Precondiciones: </b>Ninguna.
	 * <br>
	 * <b>Postcondiciones: </b>Devolvera un entero.
	 *
	 * @return Un entero numero.
	 */
	public static int pedirEntero() {
		//Declaracion de variables
		Scanner teclado = new Scanner(System.in);
		int numero = 0;
		boolean error;

		do {//Bucle para introducir un numero.

			try {
				numero = Integer.parseInt(teclado.nextLine());
				error = false;
			} catch (Exception e) {//Excepcion capturada
				System.out.println("Entrada de numero erronea, prueba otra vez.");//Mensaje de error
				error = true;//Actualizacion de la variable error en caso de excepcion.
			}

		} while (error);//Repeticion del bucle mientras que la variable error sea true.

		return numero;//Devuelve el entero numero.
	}
}
