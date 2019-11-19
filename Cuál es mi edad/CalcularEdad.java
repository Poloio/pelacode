/* Entrada: Día, mes y año de nacimiento.
 * Salida: Edad y mensajes de ayuda
 * Precondiciones: Que todas las entradas de datos sean correctas.
 * 
 * Propósito: Calcular la edad del usuario comparando su fecha de nacimiento introducida con el LocalTime del ordenador.
 * 
 * Nombre del programa: CalcularEdad
 * Variables:
 * 		Enteras: dia, mes, ano, day, month, year, edad
 * Programa principal:
 * 		
 * 	Inicio:
 * 
 * 		//Obtención 7de los datos
 * 		Escribir (" Vamos a calcuar tu edad :)\n Introduce tu día de nacimiento:")
 * 
 * 		Leer (dia)
 * 		
 * 		SI dia > 0 Y dia <= 31
 * 			
 * 			Escribir ("Ahora tu mes en números")
 * 			Leer (mes)
 * 
 * 			SI mes > 0 Y mes <= 12
 * 				
 * 				Escribir ("Por último introduce tu año de nacimiento")
 *  			Leer (ano)
 * 
 * 				SI ano <= año actual
 * 
 * 					// Cálculo de la edad
 * 					
 * 					edad = year - ano
 * 
 * 					SI mes > month O dia > day
 * 					
 * 						edad = ++edad
 * 
 * 					FIN SI
 * 
 * 					// Impresión de resultados
 * 			
 * 					Escribir ("Tienes", edad, " años.")
 * 
 * 				SINO
 * 			
 * 					Escribir ("Año no válido.")				
 * 		
 * 			SINO
 * 		
 * 				Escribir ("Mes no válido.")
 * 
 * 		SINO
 * 			
 * 			Escribir ("Día no válido.")
 * 
 */



import java.time.LocalDateTime;
import java.util.Scanner;

public class CalcularEdad {
	
	
	
	public static void main (String args[]) {
	
		Scanner teclado = new Scanner(System.in);
		LocalDateTime now = LocalDateTime.now();
		
		int edad = 0;
		int dia = 0;
		int mes = 0;
		int ano = 0;
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
			
		System.out.println(" Vamos a calcuar tu edad :)\n");
		
		do {
			
			System.out.println(" Introduce tu dia de nacimiento:\n");
			
			while (!teclado.hasNextInt()) {
				System.out.println("Debes indicarlo con numeros\n");
				teclado.next();
			}
			dia = teclado.nextInt();
			
			if (dia > 0 && dia <= 31) {
				
				do {
				
					System.out.println(" Ahora introduce el mes en numeros:\n");
					
					while (!teclado.hasNextInt()) {
						System.out.println("Debes indicarlo con numeros\n");
						teclado.next();
					}
					
					mes = teclado.nextInt();
					
					if (mes > 0 && mes <= 12) {
						
						do {
							
						
							System.out.println(" Por ultimo, introduce el anio:\n");
							
							while (!teclado.hasNextInt()) {
								System.out.println("Debes indicarlo con numeros\n");
								teclado.next();
							}
							
							ano = teclado.nextInt();
							
							if (ano <= year) {
								
								edad = year - ano;
								
								if (mes > month || (mes == month && day <= dia)) {
										edad=edad - 1;
								}
									
								System.out.println ("Tienes "+edad+" anios");
								
							} else {
								
								System.out.println ("Año no valido, prueba otra vez");
								
							}
								
						} while (ano > year);
						
					} else {
						
						System.out.println ("Mes no valido, prueba otra vez");
						
					}
						
					} while (mes <= 0 || mes > 12);
				
				} else {
				
				System.out.println("Dia no valido, prueba otra vez.");
					
			}
				
		} while (dia <= 0 || dia > 31);
		
	} // Fin main
	
} // Fin class
