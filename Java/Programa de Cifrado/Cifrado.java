/*
 * Cifrado.java
 * 
 * Análisis: 
 * 	Este programa será capaz de cifrar y descifrar mensajes.
 * 	Para ello utilizará el método César de mover un carácter a la
 * 	derecha o a la izquierda en el abecedario de manera aleatoria.
 * 	
 * 	--> ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 <--
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
 * 		PARA i < longitud del mensaje
 * 			
 * 			Leer mensaje introducido por el usuario
 * 
 * 			Generar número entre 0 y 1 - elegir dirección de movimiento
 * 			Guardar dirección en un string codigo
 * 			
 * 			//Switch MUY largo para mover cada carácter a un lado u otro
 * 
 *EJ.		SEGUN (direccion)
 * 				CASO 0
 * 					SEGUN (caracter de mensaje(i))
 * 						CASO A
 * 							Guardamos caracter cifrado con...
 * 							mensajeCifrado =+ "b"
 * 						CASO B
 * 							...
 * 					FIN SEGUN
 * 				CASO 1
 *					SEGUN (caracter de mensaje(i))
 * 						CASO A
 * 							Guardamos caracter cifrado con...
 * 							mensajeCifrado =+ "9"
 * 						CASO B
 * 							...
 * 					FIN SEGUN
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
 * 				Invertimos el switch de cifrado para revertir 
 * 				caracter a caracter siguiendo el código
 * 			FIN SEGUN
 * 		FIN PARA
 * 		
 * 		Imprimir mensaje
 * 	<Fin Descifrar>
 */

import java.util.Scanner;
import java.util.Random;

public class Cifrado {
	
	public static void main (String[] args) {
		
		//Inicializamos las variables
		String mensaje = "";
		String mensajeCifrado = "";
		String codigo = "";
		int eleccion = 0;
		int direccion = 0;
		char reintentar = ' ';
		
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
				
				mensaje = teclado.nextLine().toUpperCase();
				
				for (int i = 0; i < mensaje.length(); i++) {
				
					direccion = rnd.nextInt(2);
					codigo += direccion;
					
					switch (direccion) {
					
					case 0:// Se mueve a la derecha
						switch (mensaje.charAt(i)) {
			
						case 'A':
							mensajeCifrado += 'B';
							break;
						case 'B':
							mensajeCifrado += 'C';
							break;
						case 'C':
							mensajeCifrado += 'D';
							break;
						case 'D':
							mensajeCifrado += 'E';
							break;
						case 'E':
							mensajeCifrado += 'F';
							break;
						case 'F':
							mensajeCifrado += 'G';
							break;
						case 'G':
							mensajeCifrado += 'H';
							break;
						case 'H':
							mensajeCifrado += 'I';
							break;
						case 'I':
							mensajeCifrado += 'J';
							break;
						case 'J':
							mensajeCifrado += 'K';
							break;
						case 'K':
							mensajeCifrado += 'L';
							break;
						case 'L':
							mensajeCifrado += 'M';
							break;
						case 'M':
							mensajeCifrado += 'N';
							break;
						case 'N':
							mensajeCifrado += 'O';
							break;
						case 'O':
							mensajeCifrado += 'P';
							break;
						case 'P':
							mensajeCifrado += 'Q';
							break;
						case 'Q':
							mensajeCifrado += 'R';
							break;
						case 'R':
							mensajeCifrado += 'S';
							break;
						case 'S':
							mensajeCifrado += 'T';
							break;
						case 'T':
							mensajeCifrado += 'U';
							break;
						case 'U':
							mensajeCifrado += 'V';
							break;
						case 'V':
							mensajeCifrado += 'W';
							break;
						case 'W':
							mensajeCifrado += 'X';
							break;
						case 'X':
							mensajeCifrado += 'Y';
							break;
						case 'Y':
							mensajeCifrado += 'Z';
							break;
						case 'Z':
							mensajeCifrado += '0';
							break;
						case '0':
							mensajeCifrado += '1';
							break;
						case '1':
							mensajeCifrado += '2';
							break;
						case '2':
							mensajeCifrado += '3';
							break;
						case '3':
							mensajeCifrado += '4';
							break;
						case '4':
							mensajeCifrado += '5';
							break;
						case '5':
							mensajeCifrado += '6';
							break;
						case '6':
							mensajeCifrado += '7';
							break;
						case '7':
							mensajeCifrado += '8';
							break;
						case '8':
							mensajeCifrado += '9';
							break;
						case '9':
							mensajeCifrado += 'A';
							break;
						default:
							mensajeCifrado += mensaje.charAt(i);
						}
						
						break;//Fin caso direccion = derecha
					
					case 1:// Se mueve a la izquierda
						switch (mensaje.charAt(i)) {
					
						case 'A':
							mensajeCifrado += '9';
							break;
						case 'B':
							mensajeCifrado += 'A';
							break;
						case 'C':
							mensajeCifrado += 'B';
							break;
						case 'D':
							mensajeCifrado += 'C';
							break;
						case 'E':
							mensajeCifrado += 'D';
							break;
						case 'F':
							mensajeCifrado += 'E';
							break;
						case 'G':
							mensajeCifrado += 'F';
							break;
						case 'H':
							mensajeCifrado += 'G';
							break;
						case 'I':
							mensajeCifrado += 'H';
							break;
						case 'J':
							mensajeCifrado += 'I';
							break;
						case 'K':
							mensajeCifrado += 'J';
							break;
						case 'L':
							mensajeCifrado += 'K';
							break;
						case 'M':
							mensajeCifrado += 'L';
							break;
						case 'N':
							mensajeCifrado += 'M';
							break;
						case 'O':
							mensajeCifrado += 'N';
							break;
						case 'P':
							mensajeCifrado += 'O';
							break;
						case 'Q':
							mensajeCifrado += 'P';
							break;
						case 'R':
							mensajeCifrado += 'Q';
							break;
						case 'S':
							mensajeCifrado += 'R';
							break;
						case 'T':
							mensajeCifrado += 'S';
							break;
						case 'U':
							mensajeCifrado += 'T';
							break;
						case 'V':
							mensajeCifrado += 'U';
							break;
						case 'W':
							mensajeCifrado += 'V';
							break;
						case 'X':
							mensajeCifrado += 'W';
							break;
						case 'Y':
							mensajeCifrado += 'X';
							break;
						case 'Z':
							mensajeCifrado += 'Y';
							break;
						case '0':
							mensajeCifrado += 'Z';
							break;
						case '1':
							mensajeCifrado += '0';
							break;
						case '2':
							mensajeCifrado += '1';
							break;
						case '3':
							mensajeCifrado += '2';
							break;
						case '4':
							mensajeCifrado += '3';
							break;
						case '5':
							mensajeCifrado += '4';
							break;
						case '6':
							mensajeCifrado += '5';
							break;
						case '7':
							mensajeCifrado += '6';
							break;
						case '8':
							mensajeCifrado += '7';
							break;
						case '9':
							mensajeCifrado += '8';
							break;
						default:
							mensajeCifrado += mensaje.charAt(i);
						}
						
						break; //Fin de caso direccion izquierda
					}
				}
				
				System.out.println("\n******************************\n\n"
				+" Mensaje cifrado:\n\n"
				+mensajeCifrado+"\n\n"
				+" Codigo de descifrado:\n\n"
				+codigo);
				
				break;// Fin de caso Cifrar
				
			case 2:// Caso descifrar
				System.out.println("\n Introduce el mensaje que has recibido: \n");
				mensajeCifrado = teclado.nextLine();
				
				System.out.println("\n Introduce el codigo: \n");
				codigo = teclado.nextLine();
				for (int i = 0; i < codigo.length(); i++) {
					
					switch (codigo.charAt(i)) {
					
					case '1':// Se ha movido a la derecha
						switch (mensajeCifrado.charAt(i)) {
						
						case ' ':
							mensaje += ' ';
							break;
						case 'A':
							mensaje += 'B';
							break;
						case 'B':
							mensaje += 'C';
							break;
						case 'C':
							mensaje += 'D';
							break;
						case 'D':
							mensaje += 'E';
							break;
						case 'E':
							mensaje += 'F';
							break;
						case 'F':
							mensaje += 'G';
							break;
						case 'G':
							mensaje += 'H';
							break;
						case 'H':
							mensaje += 'I';
							break;
						case 'I':
							mensaje += 'J';
							break;
						case 'J':
							mensaje += 'K';
							break;
						case 'K':
							mensaje += 'L';
							break;
						case 'L':
							mensaje += 'M';
							break;
						case 'M':
							mensaje += 'N';
							break;
						case 'N':
							mensaje += 'O';
							break;
						case 'O':
							mensaje += 'P';
							break;
						case 'P':
							mensaje += 'Q';
							break;
						case 'Q':
							mensaje += 'R';
							break;
						case 'R':
							mensaje += 'S';
							break;
						case 'S':
							mensaje += 'T';
							break;
						case 'T':
							mensaje += 'U';
							break;
						case 'U':
							mensaje += 'V';
							break;
						case 'V':
							mensaje += 'W';
							break;
						case 'W':
							mensaje += 'X';
							break;
						case 'X':
							mensaje += 'Y';
							break;
						case 'Y':
							mensaje += 'Z';
							break;
						case 'Z':
							mensaje += '0';
							break;
						case '0':
							mensaje += '1';
							break;
						case '1':
							mensaje += '2';
							break;
						case '2':
							mensaje += '3';
							break;
						case '3':
							mensaje += '4';
							break;
						case '4':
							mensaje += '5';
							break;
						case '5':
							mensaje += '6';
							break;
						case '6':
							mensaje += '7';
							break;
						case '7':
							mensaje += '8';
							break;
						case '8':
							mensaje += '9';
							break;
						case '9':
							mensaje += 'A';
							break;
						default:
							mensaje += mensajeCifrado.charAt(i);
						}
						
						break;//Fin caso se ha movido a la derecha
					
					case '0':// Se ha movido a la izquierda
						switch (mensajeCifrado.charAt(i)) {
						
						case ' ':
							mensaje += ' ';
							break;
						case 'A':
							mensaje += '9';
							break;
						case 'B':
							mensaje += 'A';
							break;
						case 'C':
							mensaje += 'B';
							break;
						case 'D':
							mensaje += 'C';
							break;
						case 'E':
							mensaje += 'D';
							break;
						case 'F':
							mensaje += 'E';
							break;
						case 'G':
							mensaje += 'F';
							break;
						case 'H':
							mensaje += 'G';
							break;
						case 'I':
							mensaje += 'H';
							break;
						case 'J':
							mensaje += 'I';
							break;
						case 'K':
							mensaje += 'J';
							break;
						case 'L':
							mensaje += 'K';
							break;
						case 'M':
							mensaje += 'L';
							break;
						case 'N':
							mensaje += 'M';
							break;
						case 'O':
							mensaje += 'N';
							break;
						case 'P':
							mensaje += 'O';
							break;
						case 'Q':
							mensaje += 'P';
							break;
						case 'R':
							mensaje += 'Q';
							break;
						case 'S':
							mensaje += 'R';
							break;
						case 'T':
							mensaje += 'S';
							break;
						case 'U':
							mensaje += 'T';
							break;
						case 'V':
							mensaje += 'U';
							break;
						case 'W':
							mensaje += 'V';
							break;
						case 'X':
							mensaje += 'W';
							break;
						case 'Y':
							mensaje += 'X';
							break;
						case 'Z':
							mensaje += 'Y';
							break;
						case '0':
							mensaje += 'Z';
							break;
						case '1':
							mensaje += '0';
							break;
						case '2':
							mensaje += '1';
							break;
						case '3':
							mensaje += '2';
							break;
						case '4':
							mensaje += '3';
							break;
						case '5':
							mensaje += '4';
							break;
						case '6':
							mensaje += '5';
							break;
						case '7':
							mensaje += '6';
							break;
						case '8':
							mensaje += '7';
							break;
						case '9':
							mensaje += '8';
							break;
						default:
							mensaje += mensajeCifrado.charAt(i);
						}
						//Fin de caso se ha movido a la derecha
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

