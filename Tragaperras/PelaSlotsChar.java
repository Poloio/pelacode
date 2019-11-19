/*
* Análisis: Er pogramah consiste en un juego de slots (o tragaperras), que genera una combinación aleatoria entre tres
* rodillos con 8 posibles elementos:[5-6-7-8-9-J-Q-K].
* 
* Las combinaciones son:
* 
* Tres letras con alguna diferente = 40 puntos
* 5-5-5 = 80
* 6-6-6 = 160
* 7-7-7 = 240
* 8-8-8 = 320
* 9-9-9 = 400
* J-J-J = 800
* Q-Q-Q = 2000
* K-K-K = 8000
* 
* Se empieza con 1600 puntos pueden elegir de apuesta entre 40 (x1), 80 (x2) y 120 (x3) puntos.
* 
* Al principio se pide una cantidad de apuesta. Se imprime un menú para elegir cómo continuar. Tras ello, el usuario introduce T para tirar de la palanca y hacer una tirada.
* Se genera el resultado, restando la apuesta y sumando las ganancias, si la hay; o bien puede introducir A para cambiar la apuesta o S para salir.
* 
* 
* Entradas:
* 	La apuesta, un número entero.
* 	Opciones de selección de comienzo, final de programa y cambio de apuesta, todas caracteres.
* 
* Salidas:
* 	Valor de la combinación, un número entero.
* 	Valor final con multiplicador.
* 	Cantidad total de puntos, un número entero.
* 
* Restricciones:
* 	La cantidad de apuesta se definirá por el multiplicador deseado (1-2-3).
* 	Las opciones de selección deben ser caracteres T, A o S, no se diferencia entre mayúscula y minúscula.
* 
* Pseudocódico generalizado:
* 	
* 	<inicio>
* 		<ImpresionInicial>
* 		<GuardarApuesta>
* 		<TirarRodillos>
* 		<CalculoPuntos>
* 	<final>
* 
* Módulos:
* 
* 	<TirarRodillos>
* 	Inicio
* 		Escribir("[T] para tirar de la palanca, [A] para cambiar la apuesta y [S] para salir del programa")
* 		Leer (opcionInicio)
* 
* 		Según [opcionInicio]
* 			Caso, T
* 				Restar apuesta, generar combinación de rodillos y calcular valorRodillos. Seguir con <CalculoPuntos>
* 			Caso, A
* 				Volver a selección de Apuesta
* 			Caso, S
* 				Escribir("Vaya... te vas con", puntos " puntos. Gracias por jugar!")
* 			Otro SINO,
* 				Imprimir mensaje de error y volver.
* 		
* 	Fin TirarRodillos
* 
* 	<CalculoPuntos>
* 	Inicio
* 		ganancia = valorTotal * apuesta
* 		puntos += ganancia
* 	Fin CalculoPuntos
* 
*/


import java.util.Random;
import java.util.Scanner;

class PelaSlotsChar {
	
	public static void main (String[] args) {
		
		Scanner teclado = new Scanner (System.in);
		Random r = new Random();
		
		int apuesta = 0;
		int valorTotal = 0;
		int ganancias = 0;
		int paga = 0;
		char opcionInicio = ' ';
		char otraVez = ' ';
		char[] valorRodillo = {'5','6','7','8','9','J','Q','K'};
		char rodillo1, rodillo2, rodillo3 = ' ';
		int puntos = 0;
		
		do{
			
			puntos = 1600;
			otraVez = ' ';
			
		//ImpresionInicial
			System.out.println("\n  Bienvenido!\n Tira de la palanca para empezar a ganar.\n\n Empiezas con 1600 puntos.\n Tabla de puntuaciones:*******************\n\n Letra-Letra-Letra ------ 40\n             5-5-5 ------ 80\n             6-6-6 ------ 160\n             7-7-7 ------ 240\n             8-8-8 ------ 320\n             9-9-9 ------ 400\n             J-J-J ------ 800\n             Q-Q-Q ------ 2000\n             K-K-K ------ 8000\n\n******************************************\n");
			
		//GuardarApuesta
			do {
			
				System.out.println(" Cual es tu apuesta?\n Introduce [1] = 40 puntos\n Introduce [2] = 80 puntos\n Introduce [3] = 120 puntos\n");
				
				// Validacion de variable
				while (!teclado.hasNextInt()) {
				
					System.out.println("\n Debes introducir un numero entero.\n");
					teclado.next();
				}
				
				do {
				
					apuesta = teclado.nextInt();
					
					if (apuesta < 1 || apuesta > 3) {
					
						System.out.println("\n Opcion invalida, debe ser una de las indicadas.\n");
					}
					
				} while (apuesta < 1 || apuesta > 3);
				
			// TirarRodillos
				
				do {
					System.out.println("\n [T] ----- Realizar tirada.\n [A] ----- Cambiar apuesta.\n [S] ----- Salir del juego.\n");
					
					do {
					
						while (!teclado.hasNextLine()) {
						
							System.out.println("Debes introducir una de las opciones.");
							teclado.next();
						}
						
						opcionInicio = Character.toUpperCase(teclado.next().charAt(0));
						
						if (opcionInicio != 'T' && opcionInicio != 'A' && opcionInicio != 'S') {
						
							System.out.println("\n Debes introducir una de las opciones.");
						}
					}	while (opcionInicio != 'T' && opcionInicio != 'A' && opcionInicio != 'S');
					
					switch (opcionInicio) {
					
						// Tirar de rodillo
						case 'T':
							//Restar la apuesta
							paga = apuesta * 40;
							puntos -= paga;
							
							System.out.println("\n Pagas --- "+paga+" puntos\n");
							
							//Generar valor de rodillos
							rodillo1 = valorRodillo[r.nextInt(8)];
							rodillo2 = valorRodillo[r.nextInt(8)];
							rodillo3 = valorRodillo[r.nextInt(8)];
							
							if (rodillo1 == (rodillo2) && rodillo2 == (rodillo3)){
								
								switch (rodillo1) {
								
									case '5':
										valorTotal = 80;
										break;
									
									case '6':
										valorTotal = 160;
										break;
									
									case '7':
										valorTotal = 240;
										break;
									
									case '8':
										valorTotal = 320;
										break;
									
									case '9':
										valorTotal = 400;
										break;
										
									case 'J':
										valorTotal = 800;
										break;
										
									case 'Q':
										valorTotal = 2000;
										break;
										
									case 'K':
										valorTotal = 8000;
										break;
								}
								
							} else if ((rodillo1 == 'J' || rodillo1 == 'Q' || rodillo1 == 'K') && (rodillo2 == 'J' || rodillo2 == 'Q' || rodillo2 == 'K') && (rodillo3 == 'J' || rodillo3 == 'Q' || rodillo3 == 'K')) {
							
								valorTotal = 40;
							} else {
								
								valorTotal = 0;
							}
						
						//CalcularPuntos
							ganancias = valorTotal * apuesta;
							puntos += ganancias;
							
							System.out.println(" _____   _____   _____\n |   |   |   |   |   |\n | "+rodillo1+" |   | "+rodillo2+" |   | "+rodillo3+" |\n |___|   |___|   |___|\n\n Has ganado ----- "+ganancias+" puntos.\n Te quedan ----- "+puntos+" puntos.\n********************************************************\n\n");
							
							break;
							
						case 'A':
						
							System.out.println("\n Ok!");
							break;
						
						case 'S':
						
							System.out.println("\n Vuelve pronto!");	
							break;
					
					}
				
					if (puntos <= 0) {
						
						System.out.println("\n ***************** Estas sin blanca! *****************\n\n Reintentar? ... [S/N]\n");
						
						while (!teclado.hasNextLine()) {
						
							System.out.println("\n Debes introducir una de las opciones.\n");
							teclado.next();
						}
						
						do {
							otraVez = Character.toUpperCase(teclado.next().charAt(0));
							
							if (otraVez != 'S' && otraVez != 'N') {
								
								System.out.println("\n Debes introducir una de las opciones.\n");
							}
						}	while (otraVez != 'S' && otraVez != 'N');
						
						opcionInicio = ' ';
					}
					
				} while (opcionInicio == 'T'); //fin DO Hacer Tirada
				
			} while (opcionInicio == 'A'); //fin DO Guardar Apuesta
	
		} while (otraVez == 'S');
	}
}

