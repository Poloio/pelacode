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

	/**
	 * Metodo publico para pedir una numero con decimales controlando las
	 * excepciones que puedan ocurrir.
	 * <br><br>
	 * <b>Precondiciones: </b>Ninguna.
	 * <br>
	 * <b>Postcondiciones: </b>Devolvera un double.
	 *
	 * @return Un entero numero.
	 */
	public static double pedirDoble() {
		//Declaracion de variables
		Scanner teclado = new Scanner(System.in);
		double numero = 0;
		boolean error;

		do {//Bucle para introducir un numero.

			try {
				numero = Double.parseDouble(teclado.nextLine());
				error = false;
			} catch (Exception e) {//Excepcion capturada
				System.out.println("Entrada de numero erronea, prueba otra vez.");//Mensaje de error
				error = true;//Actualizacion de la variable error en caso de excepcion.
			}

		} while (error);//Repeticion del bucle mientras que la variable error sea true.

		return numero;
	}

	/**
	 * Metodo publico para pedir una numero en un rango indicado controlando las
	 * excepciones que puedan ocurrir.
	 * <br><br>
	 * <b>Precondiciones: ninguna</b>Ninguna.
	 * <br>
	 * <b>Postcondiciones: </b>Devolvera un entero validando que esté en el rango indicado.
	 *
	 * @return Un entero numero.
	 */
	public static int pedirEnteroConRango(int n1, int n2) {
		//Declaracion de variables
		Scanner teclado = new Scanner(System.in);
		int numero = 0;
		boolean error;
		int menor = 0;
		int mayor = 0;

		if (n1 < n2) {
			menor = n1;
			mayor = n2;
		} else {
			mayor = n1;
			menor = n2;
		}

		do {//Bucle para introducir un numero.

			try {
				numero = Integer.parseInt(teclado.nextLine());
				error = false;

				if (numero < menor || numero > mayor) {
					System.out.println("El número debe estar entre "+menor+" y "+mayor);
				}

				if (menor == mayor) {
					System.out.println("ERROR: en la obtencion de datos, avisa administrador.");
				}

			} catch (Exception e) {//Excepcion capturada
				System.out.println("Entrada de numero erronea, prueba otra vez.");//Mensaje de error
				error = true;//Actualizacion de la variable error en caso de excepcion.
			}

		} while (error);//Repeticion del bucle mientras que la variable error sea true.

		return numero;//Devuelve el entero numero.
	}
}
