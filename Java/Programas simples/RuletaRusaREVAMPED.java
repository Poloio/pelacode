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
	
	
	
	//////////////////////////////// Leer y Validar Nombre ///////////////////////////////////////////
	
	/*
	 * Entrada
	 * 
	 */
	
	public static String pedirNombre(Scanner teclado) {
		
		String nombre;
		char opcion;
		
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
		
		return nombre;
	}
	
	
	///////////////////////////// Imprimir Tiros Restantes /////////////////////////////
	
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
	
	
	
	/////////////////////////////////////// Imprimir tiros al techo //////////////////////////////////////
	
	public static void printTecho(int vecesTecho) {
	
		System.out.print("\n Disparos al techo restantes:");
		
		for (int i = 0; i < vecesTecho; i++) {
			
			System.out.print(" ( X )");
		}

		for (int i = 0; i < (2 - vecesTecho); i++) {
			
			System.out.print(" ( _ )");
		}
		
		System.out.print("\n");
	}
	
	
	/////////////////////////////////////// Leer y Validar Opción Menú //////////////////////////////////////
	
	public static char menuLugarDisparo(char lugar, int vecesTecho, Scanner teclado) {
		
		//Imprimir veces que se ha elegido techo
		printTecho(vecesTecho);
		
		switch (lugar) {
				
		case 'T':
		
			lugar = 'Y';
			System.out.println("\n Antes elegiste el techo, ahora no te libras...");
			break;
			
		default:
			
			do {
				
				System.out.print("\n Elige donde quieres disparar: [Y] Tu mismo - [T] Techo"
				+"\n>> ");
				
				lugar = teclado.nextLine().toUpperCase().charAt(0);
				
				if (lugar != 'T' && lugar != 'Y') {
				
					System.out.print("\n Debes introducir una de las opciones.\n"
					+">> ");
				} else if (vecesTecho == 2) {
					
					System.out.print("\n Ya has gastado tus tiros al techo... Lo siento!");
				}
			
			} while ((lugar != 'T' && lugar != 'Y') || (lugar == 'T' && vecesTecho == 2));
		}
		
		return lugar;
	}
	
	////////////////////////////////////// Generar Disparo ////////////////////////////////////////
	
	public static boolean generarDisparo(int recamara) {
		
		Random rnd = new Random();
		boolean disparo;
		
		disparo = (rnd.nextInt(6) + recamara) >= 5;
		
		return disparo;
	}
	
	///////////////////////////// Comprobar si ha muerto /////////////////////////////////////////////////
	
	public static boolean comprobarMuerto(char lugar, boolean disparo) {
		
		boolean muerto = false;
		
		if (disparo) {
			
			switch (lugar) {
			
			case 'T':
			
				System.out.println("\n Por los pelos... El disparo fue al techo. Pero el siguiente va a por ti!");
				break;
				
			case 'Y':
				
				System.out.println("\n H A S  M U E R T O \n");
				muerto = true;
			}
			
		} else {
		
			System.out.println("\n Vives! No ha habido disparo. Por ahora...");
		}
		
		return muerto;
	}
		
		///////////////////////// Leer y validar Reintentar ///////////////////////////
		public static char pedirReintentar(Scanner teclado) {
			
			char opcion;
			
			do {//Pedir nombre
				
				System.out.print(" Reintentar?:   [S/N]\n"
				+">> ");
				
				opcion = teclado.nextLine().toUpperCase().charAt(0);
				
				if (opcion != 'N' && opcion != 'S') {
				
					System.out.print("\n Debes introducir una de las opciones.\n"
					+">> ");
				}
		
			} while (opcion != 'N' && opcion != 'S');
			
			return opcion;
		}
	
	////////////////////////////////////////+++  M A I N  +++////////////////////////////////////////////////
	
	public static void main (String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		String nombre;
		char reintentar = ' ';
		
		// Leer y validar nombre
		nombre = pedirNombre(teclado);
		
		do {//Reintentar
			
			
			int muertes = 0, puntos = 0;
			char lugarDisparo = ' ';
			boolean muerte = false, disparo = false;
			int numRecamara = 0;
			int vecesTecho = 0;
			
			
			// Presentación
			System.out.println("\n+++++++++++++++++++++++++++++++++++++++++ Ruleta Rusa REVAMPED +++++++++++++++++++++++++++++++++++++++++"
			+"\n\n Hola, "+nombre+". Tienes un revolver con una sola bala. Tu objetivo es disparar la bala sin morir."
			+"\n Puedes disparar a ti mismo o al techo. Podrás disparar al techo dos veces y nunca seguidas, ten cuidado."
			+"\n Suerte!");
			
			do {
				printTambor(numRecamara);
				
				if (muerte) {
					
					System.out.println("\n Numero de muertes:  "+muertes);
					muerte = false;
				}
				
				// Leer yvalidar lugar de disparo
				lugarDisparo = menuLugarDisparo(lugarDisparo, vecesTecho, teclado);
				
				//Si se ha elegido techo, se añade a vecesTecho
				if (lugarDisparo == 'T') {
				
					vecesTecho++;
				}
				
				//Generar Disparo
				disparo = generarDisparo(numRecamara);
				muerte = comprobarMuerto(lugarDisparo, disparo);
				
				
				// Sumamos un tiro
				numRecamara++;
				
			} while(!muerte && !disparo);
			
			if (muerte) {
			
				System.out.println("\n -+-+-+-+-+-+-+- G A M E   O V E R -+-+-+-+-+-+-+-\n");
			} else {
				
				System.out.println("\n El arma ha disparado y sigues vivo...\n\n"
				    +"  _    _           _____    _____          _   _          _____   ____  "
				+"\r\n | |  | |   /\\    / ____|  / ____|   /\\   | \\ | |   /\\   |  __ \\ / __ \\ "
				+"\r\n | |__| |  /  \\  | (___   | |  __   /  \\  |  \\| |  /  \\  | |  | | |  | |"
				+"\r\n |  __  | / /\\ \\  \\___ \\  | | |_ | / /\\ \\ | . ` | / /\\ \\ | |  | | |  | |"
				+"\r\n | |  | |/ ____ \\ ____) | | |__| |/ ____ \\| |\\  |/ ____ \\| |__| | |__| |"
				+"\r\n |_|  |_/_/    \\_\\_____/   \\_____/_/    \\_\\_| \\_/_/    \\_\\_____/ \\____/ ");
			}
			
			
			
			reintentar = pedirReintentar(teclado);
			
		} while(reintentar == 'S');

	}
}


