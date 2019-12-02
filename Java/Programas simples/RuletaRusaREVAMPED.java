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
 * 				<Disparar self>
 * 				<Disparar techo>
 * 				<Rendirse>
 * 			<fin Menú>
 *		MIENTRAS muerte sea falso
 * 
 * 	MIENTRAS el usuario quiera reintentar
 * 
 * <fin programa>
 * 
 */


public class RuletaRusaREVAMPED {
	
	public static void main (String[] args) {
		
		String nombre;
		
		
		System.out.print(" Como te llamas?\n"
		+">> ");
		
		nombre = teclado.nextLine();
		
		System.out.print("\n Te llamas "+ nombre +", es correcto?  [S/N]\n"
		+">> ");
		
		
		
		
	}
}

