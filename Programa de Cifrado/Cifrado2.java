/*
 * Cifrado.java
 * 
 * Análisis: 
 * 	Este programa será capaz de cifrar y descifrar mensajes.
 * 	Para ello utilizará el método César de mover un carácter a la
 * 	derecha o a la izquierda en el abecedario de manera aleatoria.
 * 	
 * 	--> abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .,;() <--
 * 
 * 	Cuando cifra, genera un código que el receptor necesitará para
 * 	resolver el mensaje.
 * 
 * Entradas:
 * 	El mensaje a cifrar o descifrar, una cadena.
 * 	El código de descifrado al descifrar, una cadena.
 * 
 * Salidas:
 * 	El mensaje descifrado, una cadena.
 * 	El código de descifrado obtenido al cifrar, una cadena.
 * 
 * Restricciones: 
 * 	La selección de modo debe ser un número entero (1 o 2)
 * 
 * Pseusocódigo generalizado:
 * 
 * 	<inicio>
 * 		HACER
 * 			<ElegirPrograma>
 * 				<Cifrar>
 * 				<Descifrar>
 * 			<Fin ElegirPrograma>
 * 			<Repetir>
 * 		MIENTRAS elija repetir
 * 	<fin>
 * 
 * Módulos:
 * 
 * 	<Cifrar>
 * 		Leer mensaje introducido por el usuario
 * 
 * 		PARA i < longitud del mensaje
 * 
 * 			Generar número entre 0 y 1 - elegir dirección de movimiento
 * 			Guardar dirección en un string codigo
 * 
 *EJ.		SEGUN (direccion)
 * 				CASO 0
 * 					SI la posición del carácter i de mensaje en abc es 67
 * 						El caracter se cifra con la posicion 0 de abc
 * 					SINO	
 *						El caracter se cifra con el de su izquierda
 * 					FIN SI
 * 				CASO 1
 *					SI la posición del carácter i de mensaje en abc es 0
 * 						El caracter se cifra con la posicion 67 de abc
 * 					SINO	
 *						El caracter se cifra con el de su derecha
 * 					FIN SI
 * 			FIN SEGUN
 * 		FIN PARA
 * 
 * 		Imprimir mensajeCifrado
 * 		Imprimir codigo
 *	<Fin Cifrar>
 * 
 * 
 * 	<Descifrar>
 * 		Leer codigo
 * 		Leer mensajeCifrado
 * 	
 * 		PARA i < longitud del codigo
 * 			SEGUN (caracter i del código)
 * 				invenrtimos la dirección 
 * 				caracter a caracter siguiendo el código
 * 			FIN SEGUN
 * 		FIN PARA
 * 		
 * 		Imprimir mensaje
 * 	<Fin Descifrar>
 */

import java.util.Scanner;
import java.util.Random;

public class Cifrado2 {
	
	public static void main (String[] args) {
		
		//Inicializamos las variables
		String mensaje = "";
		String mensajeCifrado = "";
		String codigo = "";
		int eleccion = 0;
		int direccion = 0;
		char reintentar = ' ';
		String abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .,:;()";
		
		Scanner teclado = new Scanner(System.in);
		Random rnd = new Random();
		
		do {// <Elegir  Programa>
			
			mensaje="";
			mensajeCifrado = "";
			codigo = "";
			reintentar = 's';
			
			System.out.println("\n **********************************\n"
								+" *                                *\n"
								+" *   Cifraneitor 3000 4.0         *\n"
								+" *    & Knuckles                  *\n"
								+" *                                *\n"
								+" **********************************\n"
								+"\n"
							+" Quieres cifrar o descifrar un mensaje?\n\n"
							+" [1] - Cifrar\n"
							+" [2] - Descifrar\n");
			
			//Leer y validar elección de programa
			do {
				
				while (!teclado.hasNextInt()) {
				
					System.out.println(" Debes introducir una de las opciones\n");
					teclado.next();
				}
				
				eleccion = teclado.nextInt();
				teclado.nextLine();
				
				if (eleccion < 1 || eleccion > 2) {
					
					System.out.println("\n Debes introducir una de las opciones\n");
				}
			} while (eleccion < 1 || eleccion > 2);
			
			switch (eleccion) {
			
			case 1: //<Cifrar>
				
				System.out.println("\n Introduce el mensaje a cifrar. No puede contener"
				+" caracteres que no sean estandar para la consola.\n");
				
				mensaje = teclado.nextLine();
				
				
				
				for (int i = 0; i < mensaje.length(); i++) {
					
					direccion = rnd.nextInt(2);
					codigo += direccion;
					
					switch (direccion) {
					
					case 0: // Movemos a la derecha
						if ( (abc.indexOf(mensaje.charAt(i)) + 1) > 67) {
						
							mensajeCifrado += abc.charAt(0);
						} else {
							mensajeCifrado += abc.charAt(abc.indexOf(mensaje.charAt(i)) + 1);
						}
						break;
						
					case 1: // Movemos a la izqauierda
						if ( (abc.indexOf(mensaje.charAt(i)) - 1) < 0) {
						
							mensajeCifrado += abc.charAt(67);
						} else {
							mensajeCifrado += abc.charAt(abc.indexOf(mensaje.charAt(i)) - 1);
						}
					}
				}
				
				System.out.println("\n******************************\n\n"
				+" Mensaje cifrado:\n\n"
				+mensajeCifrado+"\n\n"
				+" Codigo de descifrado:\n\n"
				+codigo);
				
				break;// Fin de caso Cifrar
				
			case 2:// Caso descifrar
			
				System.out.println("\n Introduce el mensaje que has recibido: \n");//Leer mensajeCifrado
				mensajeCifrado = teclado.nextLine();
				
				System.out.println("\n Introduce el codigo: \n");//Leer código
				codigo = teclado.nextLine();
				
				for (int i = 0; i < codigo.length(); i++) { 
				
					switch (codigo.charAt(i)) {
					
					case '0':
						if ( abc.indexOf(mensajeCifrado.charAt(i)) == 0 ) {
						
							mensaje += abc.charAt(67);
						} else {
							mensaje += abc.charAt(abc.indexOf(mensajeCifrado.charAt(i)) - 1);//Invertimos al caracter de la izquierda
						}
						break;
					
					case '1':
						if ( abc.indexOf(mensajeCifrado.charAt(i)) == 67 ) {
						
							mensaje += abc.charAt(0);
						} else {
							mensaje += abc.charAt(abc.indexOf(mensajeCifrado.charAt(i)) + 1);//Invertimos al caracter de la derecha
						}
					}
				}
				
				System.out.println("\n*************************************\n"
				+"Mensaje descifrado:\n\n"
				+mensaje);
			}
			
			do {
				System.out.println("\n Quieres volver a cifrar o descifrar? [S/N]\n");
				
				reintentar = teclado.nextLine().toUpperCase().charAt(0);
				
				if (reintentar != 'S' && reintentar != 'N') {
					System.out.println("\n Debes introducir una de las opciones.\n");
				}	
			} while (reintentar != 'S' && reintentar != 'N');
			
		} while (reintentar == 'S');
		teclado.close();
	}
}

