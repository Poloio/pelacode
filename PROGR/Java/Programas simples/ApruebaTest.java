/*
 * Análisis:
 * 		El programa lee la asignatura, un número de preguntas y un número de opciones y devuelve una respuesta generada aleatoriamente para cada una de ellas, resultando en un 5 pelado casi seguro en un examen.
 * 
 * Si el número de preguntas es 10 y el número de opciones es 3, imprimirá 10 respuestas enumeradas con una letra aleatoria de la A a la C.
 * 
 * Entradas: 
 * 		La asignatura, una cadena.
 * 		El número de preguntas, entero.
 * 		El número de opciones, entero.
 * 
 * Salidas:
 * 		Se muestra en la pantalla una respuesta aleatoria entre las opciones dadas a cada una de las preguntas del número de preguntas dado.
 * 
 * Restricciones:
 * 		El número de preguntas es un número entre 5 y 30.
 * 		El número de opciones es un número del 3 al 5.
 * 
 * Pseudocódigo generalizado:
 * 
 * 		<inicio>
 * 			<Leer asignatura, NumPreguntas y NumOpciones>
 * 			<Generar las respuestas e imprimir en pantalla>
 * 			<Dar opción a repetir el programa>
 * 		<fin>
 * 
 * Módulos:
 * 		<Generar las respuestas e imprimir en pantalla>
 * 		Inicio
 * 			PARA ( i <= qntPreguntas )
 * 				Generar respuesta.
 * 				Imprimir pregunta con su respuesta.
 * 			FIN_PARA
 * 		Fin
 * 
 */
 
import java.util.Scanner;
import java.util.Random;
 
class ApruebaTest {
	 
	public static void main(String args[]) {
		
		Scanner teclado = new Scanner(System.in);
		Random r = new Random();
		String asignatura = " ";
		int qntPreguntas = 0;
		int qntOpcion = 0;
		char[] opcion={'A','B','C','D','E'};
		char reintentar = ' ';
		int respuesta = 0;
		
	// Obtener y validar asignatura, cantidad de preguntas y cantidad de opciones
		do {
			//Guardar Asignatura
			System.out.println("Enter exam's subject:\n");
			asignatura = teclado.nextLine();
			
			// Validar y guardar Cantidad de preguntas
			System.out.println("How many questions? (Between 5 and 30)\n");
			
			while (!teclado.hasNextInt()) {
				System.out.println("Number must be integer.\n");
				teclado.next();
			}
				
			do	{
				
				qntPreguntas = teclado.nextInt();
				
				if (qntPreguntas < 5 || qntPreguntas > 30) {
					
					System.out.println("The number of questions must be between 5 and 30.\n");
					}
					
			}while (qntPreguntas < 5 || qntPreguntas > 30);
			
			// Validar y guardar Canridad de opciones
			System.out.println("How many options for each questions? (Between 3 and 5)\n");
			
			while (!teclado.hasNextInt()) {
				System.out.println("The number must be integer.\n");
				teclado.next();
			}
			
			do {
				
				qntOpcion = teclado.nextInt();
				
				if (qntOpcion < 3 || qntOpcion > 5) {
					
					System.out.println("The number of options must be between 3 and 5.\n");
				}
			} while (qntOpcion < 3 || qntOpcion > 5);
			 
		// Generar respuestas e imprimir en pantalla
			System.out.println("Asignatura:  "+asignatura+"\n");
			
			
			//Bucle contador
			for (int i = 1; i <= qntPreguntas; i++) { //Se repite +qntPreguntas+ veces
				 
				 respuesta = r.nextInt(qntOpcion);
				 
				 if (i < 10) {
					System.out.println(i+".---------- "+opcion[respuesta]);
				} else {
				 System.out.println(i+".--------- "+opcion[respuesta]);
				}
			}//Fin for
				
				
			System.out.println("Done! Want to start over? --- Y/N\n");
			do{
				reintentar = teclado.next().charAt(0);
				reintentar = Character.toUpperCase(reintentar);
				
			
				if (reintentar != 'Y' && reintentar != 'N') {
					System.out.println("Invalid entry\n");
				}
					
			} while (reintentar != 'Y' && reintentar != 'N'); // Fin DO
		} while	(reintentar == 'Y'); //Fin DO
			teclado.close();
	}//Fin main
}//Fin de clase
