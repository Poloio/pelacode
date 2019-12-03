/*
 * Análisis:
 * 	Una revisión de mi juego de la ruleta rusa, pero con métodos. Ruleta Rusa REVAMPED.
 * 
 * Entradas:
 *  Nombre del jugador, un String.
 * 	Opciones de menú, todos caracteres.
 * 	Lugar de disparo, un caracter.
 * 	Si el usuario quiere reintentar, un booleano.
 * 
 * Salidas:
 * 	Si la recamara tiene bala, un booleano.
 * 	Número de puntos, un num entero.
 * 	Número de muertes, un num entero.
 * 
 * Pseudocódigo generalizado:
 * 	
 * <inicio>	
 * 	<Pedir nombre>
 * 	
 * 	REPETIR
 * 		<Imprimir presentación>
 * 
 *		REPETIR
 * 			<Imprimir tambor>
 * 			<Menú>
 * 				<ElegirLugar>
 * 				<GenerarDisparo>
 * 			<fin Menú>
 *		MIENTRAS muerte sea falso
 * 
 * 	MIENTRAS el usuario quiera reintentar
 * 
 * <fin programa>
 * 
 */
import java.util.Scanner;
import java.util.Random;

public class RuletaRusaREVAMPED {
	
	public static void printTambor(int recamara) {
		
		System.out.println("\n Disparos restantes:");
		
		for (int i = 0; i < (6 - recamara); i++) {
			
			System.out.print("   ___ _ ");
		}
		
		System.out.print("\n");
		
		for (int i = 0; i < (6 - recamara); i++) {
			
			System.out.print("  |___|_)");
		}
		
		System.out.print("\n");
	}
	
	public static boolean generarDisparo(char lugar, int recamara) {
		
		Random rnd = new Random();
		boolean disparo, muerto = false;
		
		disparo = (rnd.nextInt(6) + recamara) >= 5;
		
		if (disparo) {
			
			switch (lugar) {
			
			case 'T':
			
				System.out.println("\n Por los pelos... El disparo fue al techo. Pero el siguiente va a por ti!");
				break;
				
			case 'Y':
				
				System.out.println("\n XXXXXXXXXXXXXXXXXXXXXXXXX H A S  M U E R T O XXXXXXXXXXXXXXXXXXXXXXXXX");
				muerto = true;
			}
			
		} else {
		
			System.out.println("\n Vives! No ha habido disparo. Por ahora...");
		}
		
		return muerto;
	}
	
	////////////////////////////////////////  M A I N  ////////////////////////////////////////////////
	
	public static void main (String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		String nombre;
		char opcion, reintentar = ' ';
		
		do {//Pedir nombre
			
			System.out.print(" Como te llamas?\n"
			+">> ");
			
			nombre = teclado.nextLine();
			
			System.out.print("\n Te llamas "+ nombre +", es correcto?  [S/N]\n"
			+">> ");
			
			opcion = teclado.nextLine().toUpperCase().charAt(0);
			
			if (opcion != 'N' && opcion != 'S') {
			
				System.out.print("\n Debes introducir una de las opciones.\n"
				+">> ");
			}
	
		} while (opcion == 'N' || (opcion != 'N' && opcion != 'S'));
			
			
		
		
		do {//Reintentar
			//Presentación
			
			int muertes = 0, puntos = 0;
			char lugarDisparo = ' ';
			boolean muerte = false;
			int numRecamara = 0;
			
			System.out.println("\n+++++++++++++++++++++++++++++++++++++++++ Ruleta Rusa REVAMPED +++++++++++++++++++++++++++++++++++++++++"
			+"\n\n Hola, "+nombre+". Tienes un revolver con una sola bala. Tu objetivo es disparar la bala sin morir."
			+"\n Puedes disparar a ti mismo o al techo. Podrás disparar al techo dos veces y nunca seguidas, ten cuidado."
			+"\n Suerte!");
			
			do {
				printTambor(numRecamara);
				
				switch (lugarDisparo) {
				
				case 'T':
				
					lugarDisparo = 'Y';
					break;
					
				default:
					
					do {
						
						System.out.print("\n Elige donde quieres disparar. [Y] Tu mismo - [T] Techo"
						+"\n>> ");
						
						lugarDisparo = teclado.nextLine().toUpperCase().charAt(0);
						
						if (lugarDisparo != 'T' && lugarDisparo != 'Y') {
						
							System.out.print("\n Debes introducir una de las opciones.\n"
							+">> ");
						}
					
					} while (lugarDisparo != 'T' && lugarDisparo != 'Y');
				}
				
				muerte = generarDisparo(lugarDisparo,numRecamara);
				
				numRecamara++;
				
			} while(!muerte && numRecamara < 6);
			
			
			
			reintentar = 'S';
			
		} while(reintentar == 'S');

	}
}


