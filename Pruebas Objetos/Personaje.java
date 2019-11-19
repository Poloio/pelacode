import java.util.Scanner;

class Personaje {
	
	String nombre;
	int nivel;
	String raza;
	char genero;
	String clase;
	
	public Personaje(String name, int level, String race, char genre, String job) {
	
		nombre = name;
		nivel = level;
		raza = race;
		genero = genre;
		clase = job;
	}
	
	static void crearPersonaje() {
		Scanner teclado = new Scanner (System.in);
		
		Personaje player = new Personaje (" ", 1, " ", ' ', " ");
		
		int opcionClase = 0;
		String nombreIn = " ";
		char volver = ' ';
		
		do {
			
			System.out.println("\n Selecciona la clase para tu personaje:\n\n   BERSERKER - [1]       MAGO - [2]\n   SACERDOTE - [3]    ASESINO - [4]\n CAVERNICOLA - [5]    FACHITA - [6]\n");
		 
		//Introducción y validación de clase
			do {
				while (!teclado.hasNextInt()) {
					
						System.out.println("\n Debes introducir un número del 1 al 5.\n");
						teclado.next();
				}
				
					opcionClase = teclado.nextInt();
					
				switch (opcionClase) {
				
					case 1:
						player.clase = "BERSERKER";
						break;
						
					case 2:
						player.clase = "MAGO";
						break;
						
					case 3:
						player.clase = "SACERDOTE";
						break;
						
					case 4:
						player.clase = "ASESINO";
						break;
						
					case 5:
						player.clase = "CAVERNICOLA";
						break;
						
					case 6:
						player.clase = "FACHITA";
						break;
					
					default:
						System.out.println("Debes introducir una de las opciones");
				}
			} while (opcionClase < 1 || opcionClase > 6);
				
			System.out.println("\n Has seleccionado "+player.clase+". Estas seguro de tu eleccion?    [S/N]\n");
			
			do {
				
				volver = Character.toUpperCase(teclado.next().charAt(0));
				teclado.nextLine();
			
				switch (volver) {
				
					case 'S':
						System.out.println("\n Bienvenido, "+player.clase+".\n");
						break;
					
					case 'N':
						System.out.println("\n Volviendo...");
						break;
					
					default:
						System.out.println("\n Debes introducir una de las opciones.\n");
						break;
				}	
			} while (volver != 'S' && volver != 'N');
		} while (volver == 'N');
		
	// Introducción y validación de nombre
		
		do {
			System.out.println(" Ahora, introduce el nombre del personaje.\n");
			
			nombreIn = teclado.nextLine();
			
			System.out.println("\n Te lamas "+nombreIn+", cierto?    [S/N]");
			
			do {
				volver = Character.toUpperCase(teclado.next().charAt(0));
				
				switch (volver) {
					
				case 'S':
					System.out.println("\n Con que "+nombreIn+", el "+player.clase+"...\n");
					player.nombre = nombreIn;
					break;
				
				case 'N':
					System.out.println("\n Volviendo...");
					break;
				
				default:
					System.out.println("\n Debes introducir una de las opciones.\n");
					break;
				}	
			} while (volver != 'S' && volver != 'N');
		} while (volver == 'N');
		
		


		
		
	}
	public static void main (String[] args){
		
		crearPersonaje();
	}
}

