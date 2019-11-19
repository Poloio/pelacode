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


public class RuletaRusa2 {
	
	public static void main (String[] args) {
		
	}
}

