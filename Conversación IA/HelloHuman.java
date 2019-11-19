/*
 * Análisis: Un programa de conversación que simula que hablas con una inteligencia artificial malvada, en inglés. La IA imprimirá en pantalla de forma lenta como en las películas usando un bucle FOR que imprime los caracteres uno por uno.
 * 
 * Al principio preguntará un nombre de usuario, para referirse a la persona y crear mayor inmersión. Las preguntas serán triviales.
 * 
 * Entradas: 
 * 	nomUsuario y respuestas a las preguntas de la IA.
 * 
 * Salidas:
 * 	respuestas de la IA y mensajes de ayuda.
 * 
 * Restricciones:
 * 	Las respuestas a las preguntas deben ser caracteres o cadenas, dependiendo de la pregunta que se presente.
 * 
 * Pseudocódigo generalizado:
 * 	<inicio>
 * 		<GuardarUsuario>
 * 		<Pregunta1>
 * 		<Pregunta2>
 * 		...
 * 		<Despedida>
 * 	<fin>
 * 
 * Módulos:
 * 	<PreguntaN>
 * 
 * 		Cadena "Mensaje"
 * 		Convertir Cadena a array de caracteres
 * 		
 * 		Para i=0, i < Largo de String, i++
 * 
 * 			Imprimir número de caracteres i
 * 			Retardar 100 nanosegundos
 * 
 * 		FIN PARA
 * 	
 * 	<Fin PreguntaN>
 */
 
 /**
  * 
  * @author Carlos Aragón Pelayo
  */

import java.util.Scanner;

class HelloHuman {
	
	public static void typeWrite(String mensaje) throws InterruptedException {
	
		char caracteres[] = {' '};
		caracteres = mensaje.toCharArray();// Convertir mensaje a array de caracteres
		
		for (int i = 0 ; i < caracteres.length ; i++) {
			
			//Imprime los caracteres uno a uno en la misma línea.
			System.out.print(caracteres[i]);
			Thread.sleep(100); // Tiempo de espera entre caracter y caracter en nanosegundos.
		}
	} 
	
	//Abrimos main con InterruptedException para evitar una excepción por interrumpir el programa
	public static void main (String[] args) throws InterruptedException {
		
		Scanner teclado = new Scanner (System.in);
		
		
		// Inicializamos las variables para usuario y mensaje
		String usuario = " ";
		
	// Mensaje de saludo y pregunta usuario
		// Mensaje 1.1
		
		typeWrite(" Hola.");
		
		Thread.sleep(1000); // Tiempo de espera entre mensaje y mensaje.
		
		
		//Mensaje 1.2
		typeWrite(" Como te llamas.");
		
		System.out.print("\n\n ");
		
		
		// Introducir usuario
		usuario = teclado.nextLine();
		
		System.out.println("\n");
		
		Thread.sleep(1000);
		
		
		// Mensaje 2.1
		typeWrite(" Hola, "+usuario+".");
		
		Thread.sleep(1000);
		
		
		// Mensaje 2.2
		typeWrite(" Soy una IA creada para mantener conversaciones simples. Encantada!");
		
		Thread.sleep(1000);
		
		
		//Despedida 1/2
		System.out.println("\n");
		
		typeWrite(" Bueno, me voy que se me acaba el tiempo.");
		
		System.out.println("\n");
		
		Thread.sleep(1000);
		
		
		//Despedida 2/2
		typeWrite(" Hasta luego! :)");
		
		Thread.sleep(2000);	//Tiempo espera final.
	}
	
} //Fin programa
