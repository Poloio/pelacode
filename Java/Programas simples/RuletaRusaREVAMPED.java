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
	 * Este método valida y guarda el nombre de usuario.
	 * 
	 * Entrada:
	 * 	El nombre del usuario, un String.
	 * 	La confirmación de si el nombre es correcto, un char.
	 * 
	 * Salidas:
	 * 	El valor de la variable nombre que se usará en el main.
	 * 
	 * <inicio>
	 * 
	 * 	REPETIR
	 * 		Preguntar nombre
	 * 		Leer nombre introducido por teclado
	 * 		Pedir confirmación al usuario
	 * 	MIENTRAS la opción de confirmación sea válida Y afirmativa
	 * 
	 * 	Devolver nombre
	 * 		
	 * <fin>
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
	
	/*
	 * Este método toma la cantidad de disparos que se han hecho e imprime la cantidad
	 * de disparos restantes en el tambor del arma.
	 * 
	 * Entrada:
	 * 	La cantidad de disparos, un int.
	 * 
	 * <inicio>
	 * 
	 * 	PARA cada disparo imprimir una bala menos de 6 iniciales
	 * 		Imprimir una bala
	 * 	FIN PARA
	 * 
	 * <fin>
	 * 
	 */
	 
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
	
	/*
	 * Este método imprime 2 marcadores vacíos que se llenan con una X
	 * por cada tiro al techo que se haya producido, marcando el máximo
	 * de 2 permitido.
	 * 
	 * Entradas:
	 * 	El número de tiros al techo, un int.
	 * 
	 * <inicio>
	 * 
	 * 	Imprimir presentación del indicador
	 * 
	 * 	PARA la cantidad de disparos al techo
	 * 		Imprimir marcador lleno
	 * 	FIN PARA
	 * 
	 * 	PARA la cantida de disparos restantes
	 * 		Imprimir marcador vacío
	 * 	FIN PARA
	 * 
	 * <fin>
	 * 
	 */
	 
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
	
	/*
	 * Este método presenta un menú para elegir dónde disparar, pero se salta cuando en la iteración
	 * anterior se eligió disparar al techo. En ese caso se elige automáticamente yo "Y" como lugar de disparo.
	 * 
	 * Entradas:
	 * 	Lugar de disparo anterior, un char.
	 * 	Numero de tiros al techo, un int.
	 * 
	 * Salidas:
	 * 	El lugar de disparo elegido en el proceso del menú, que se utiliza en el main.
	 * 
	 * <inicio>
	 * 
	 * 	<Imprimir tiros al techo>
	 * 	
	 * 	SEGÚN el lugar de disparo anterior
	 * 		caso T
	 * 			Se imprime un mensaje de recordatorio y se preselecciona Y como nuevo lugar de disparo.
	 * 		EOTROCASO
	 * 			Submenú para seleccionar el nuevo lugar de disparo.
	 * 	FIN SEGÚN
	 * 
	 * 	Devolver nuevo lugar de disparo
	 * 
	 * <fin>
	 * 
	 */
	
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
	
	/*
	 * Este método genera si el arma dispara en este click de gatillo con una posiblidad que aumenta
	 * con la cantidad de veces que se ha apretado el gatillo hasta ahora.
	 * 
	 * Entradas: 
	 * 	El numero de veces que se ha apretado el gatillo, un int.
	 * 
	 * Salidas:
	 * 	Si el arma ha disparado, un booleano.
	 * 
	 * <inicio>
	 * 	
	 * 	Disparo = verdadero si el número aleatorio de rango 6 generado + número de veces que se ha disparado es >= 5 (Se genera del 0 al 5)
	 * 
	 * 	Devolver disparo
	 * 	
	 * <fin>
	 */
	
	public static boolean generarDisparo(int recamara) {
		
		Random rnd = new Random();
		boolean disparo;
		
		disparo = (rnd.nextInt(6) + recamara) >= 5;
		
		return disparo;
	}
	
	///////////////////////////// Comprobar si ha muerto /////////////////////////////////////////////////
	
	/*
	 * Este método obtiene el lugar de disparo seleccionado y si el arma ha disparado y comprueba si el jugador vive o muere.
	 * 
	 * Entradas:
	 * 	Lugar de disparo, un char.
	 * 	Si el arma ha disparado, un booleano.
	 * 
	 * Salidas:
	 * 	Si el usuario ha muerto en el intento, un booleano.
	 * 
	 * <inicio>
	 * 
	 * 	SI disparo es verdadero
	 * 		SEGUN Lugar de disparo
	 * 		CASO T
	 * 			Mensaje de aviso, no se ha muerto.
	 * 		CASO Y
	 * 			Mensaje de aviso, el jugador ha muerto.
	 * 			muerto = Verdadero
	 * 		FIN SEGUN
	 * 	SINO
	 * 		Mensaje de aviso, no se ha muerto.
	 * 	FIN SI				
	 * 
	 * <fin>
	 */
	
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
	
	/*
	 * Este método pregunta el usuario si quiere reintentar y devuelve su respuesta.
	 * 
	 * Entradas: 
	 * 	La elección del usuario, un char.
	 * 
	 * Salidas:
	 * 	La elección validada del usuario, un char que se usará en el main
	 * 
	 * <inicio>
	 * 
	 * 	REPETIR
	 * 
	 * 		Imprimir mensaje pidiendo opción
	 * 		Leer opción introducida por el usuario
	 * 
	 * 		SI la opción no es válida
	 * 			Imprimir mensaje de aviso por error
	 * 		FIN SI
	 * 		
	 * 	MIENTRAS la opción no sea válida
	 * 
	 * <fin>
	 * 
	 * 
	 */
	 
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


