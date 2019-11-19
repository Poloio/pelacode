/* Entrada: usuario, lugarDisparo, reintentar
 * 
 * Salida: MAU, gatillazos, numMuertes, numRecamara
 * 
 * Propósito: El jugador debe disparar una ruleta rusa 6 veces sin morir. Para eso se genera un número aleatorio entre 1 y 6, donde 6 es muerte y los demás es vivo. Cada disparo reduce el rango del número aleatorio en uno, haciendo menos posible que sobreviva. Puede elegir entre dispararse a sí mismo o al techo, si dispara al techo se salva de una posible muerte, pero obliga a dispararse a sí mismo la siguiente tirada. Si muere pregunta si se quiere reintentar, y si gana enseña un mensaje de felicitación. Tanto si gana como si rechaza el reintento, el programa hace una salida con código 0.
 * 
 * Nombre programa: ruletaRusa
 * 
 * Variables: 
 * 		Enteras: usuario, numRecamara, numMuertes, gatillazos
 * 
 * Programa principal:
 * 		Inicio:
 * 			Importar escaner
 * 			Importar aleatorio
 * 			
 * 			// Comienza clase
 * 
 * 			Crear objeto scanner
 * 			
 * 			numRecamara = nulo
 * 			numMuertes = 0
 * 			gatillazos = 0
 * 			muerte = Falso
 * 			
 *			Leer (usuario)
 * 					 
 * 			Escribir ("Hola, ", usuario, ". Delante tienes un revolver con una sola bala en el tambor.
 * 			Si disparas 6 veces sin morir ganaras tu libertad.
 * 			
 * 			Puedes apretar el gatillo contra ti mismo o hacia el techo,
 * 			pero tras hacerlo contra el techo tendrás que apretarlo hacia ti la
 * 			siguiente vez obligatoriamente.
 * 
 * 			Que comienze el juego...")
 * 			
 * 			HACER
 * 				
 * 				SI muerte == verdadero
 * 					numMuertes = ++numMuertes
 * 					gatillazos = 0
 * 					primRecamara = 1
 * 				
 * 				SI numMuertes > 0
 * 					Escribir ("Muertes -------- ", numMuertes)
 * 				FIN SI
 * 				
 * 				HACER
 * 					
 * 					SI gatillazos > 0
 * 						Escribir ("Puntos -------- ", gatillazos, "/6)
 * 
 * 					SINO SI gatillazos == 6
 * 						Escribir ("______________/n| Eres libre |/n______________")
 * 						SI usuario igualaA vito O vitolo O chokero
 * 							Escribir ("Felicidades campeon!")
 * 						Sali del programa
 * 					
 * 					numRecamara = numero aleatorio entre 0 y 6
 * 					numRecamara += primRecamara
 * 					
 * 					Escribir ("¿Donde disparar? --------- yo o techo")
 * 					SI lugarDisparo igualaA techo
 * 						Escribir("Ahora no te libras...")
 * 						lugarDisparo = yo
 * 						SINO Leer (lugarDisparo)
 * 					FIN SI
 * 
 * 					
 * 					SI numRecamara < 6 Y lugarDisparo igualaA yo O techo
 * 						gatillazos = ++gatillazos
 * 						primRecamara = ++primRecamara
 * 						Escribir ("¡Vives! No hay disparo. Por * ahora...")
 * 						
 * 						SINO SI lugarDisparo igualaA techo
 * 								Escribir ("Por los pelos... El * disparo fue al techo.")
 * 							
 * 							SINO SI lugarDisparo igualaA yo
 * 									Escribir ("AHAHAHAHAHA MORISTE * AHAHAHAHAHA/n/n¿Reintentar? S/N")
 * 									Leer (reintentar)
 * 									SI reintentar igualaA s
 * 										muerte = True
 * 										SINO 
 * 											Escribir ("Cobardeeeeeeh")
 * 											Salir del programa
 * 								FIN SI
 * 							FIN SI
 * 						FIN SI
 *					FIN SI
 * 			MIENTRAS (Verdedero)	
 * 
*/

import java.util.Scanner;
import java.util.Random;

public class LaRuletaRusa {
	
	public static void main(String args[]) {
		
		Random bala = new Random();
		Scanner teclado = new Scanner(System.in);

		int numMuertes = 0;
		short gatillazos = 0;
		boolean muerte = false;
		String usuario = null;
		String lugarDisparo = "yo";
		short primRecamara = 1;
		int numRecamara = 0;
		String reintentar = "s";
	
		System.out.println("Introduce tu nombre...");
		
		usuario = teclado.nextLine();
		
		System.out.println("\nHola, "+usuario+".\n\nDelante tienes un revolver con una sola bala en el tambor.\nSi disparas 6 veces sin morir ganaras tu libertad.\n\nPuedes apretar el gatillo contra ti mismo o hacia el techo,\n pero tras hacerlo contra el techo tendras que apretarlo hacia ti la siguiente vez obligatoriamente.\n\nQue comienze el juego...\n------------------------------------------------");
		
		do {
			
			if(muerte == true){
				
				lugarDisparo = "yo";
				numMuertes = ++numMuertes;
				gatillazos = 0;
				primRecamara = 1;
				muerte = false;
				System.out.println("\n**********************************\nMuertes -------- "+numMuertes+"\n");
			}
			
			if(gatillazos > 0){
					
					System.out.println("Puntos -------- "+gatillazos+"/6\n");
				}
					
				if(gatillazos >= 6){
					
					System.out.println("\n||| E R E S   L I B R E |||\n");
					
					if(usuario.equals("vito") || usuario.equals("vitolo") || usuario.equals("Vito")){
						
						System.out.println("Felisidades pto y no te drogues mucho");
					}
					
					System.exit(0);
				}
				
				numRecamara = bala.nextInt(7);
				numRecamara += primRecamara;
				
				if(lugarDisparo.equals("techo")){
					
					System.out.println("\nAhora no te libras...\nTe toca a ti!");
					lugarDisparo = "yo";
				} else {
					do {
						
						System.out.println("Donde disparar? --------- yo o techo\n");
						
						lugarDisparo = teclado.nextLine();
						
						if(!(lugarDisparo.equals("techo")) && !(lugarDisparo.equals("yo"))) {
							
							System.out.println("\nEntrada invalida, prueba otra vez.\n");
						}
						
					} while(!(lugarDisparo.equals("techo")) && !(lugarDisparo.equals("yo")));
				}					
					
					if(numRecamara < 6 && (lugarDisparo.equals("yo") || lugarDisparo.equals("techo"))){
						
						gatillazos = ++gatillazos;
						primRecamara = ++primRecamara;
						
						System.out.print("\nVives! No ha habido disparo. Por ahora...\n");
						
					} else if(lugarDisparo.equals("techo")) {
						
						gatillazos = ++gatillazos;
						primRecamara = 1;
						
						System.out.print("\nPor los pelos... El disparo fue al techo. Pero el siguiente va a por ti!\n");
						
					} else if(lugarDisparo.equals("yo")){
						
						System.out.println("\nAHAHAHAHAHA MORISTE PRRO AHAHAHAHAHA\n--- Reintentar? s/n ---");
						do {
							reintentar = teclado.nextLine();						
							if(reintentar.equals("s")){
								muerte = true;
							} else if(reintentar.equals("n")){
								System.out.println("\n\nCOBARDEEERRRL");
								System.exit(0);
							}
							
						} while (!(reintentar.equals("s")) && !(reintentar.equals("n")));
					}
				} while (true);
			} 
		}
