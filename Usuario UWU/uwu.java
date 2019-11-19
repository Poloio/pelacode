import java.util.Scanner;

class uwu{

	public static void main(String args[])
	{
		// Creacion Objeto Escaner
		
		Scanner myObj = new Scanner(System.in);
		
		// Introduccion de Usuario y Contraseña	hehe
		
		System.out.println("Mete el uzuario shurra");
		
		do{
			String uzuario = myObj.nextLine();
			String username = "Pelaio", password = "uwusito";
			if(uzuario.equals(username)){
				System.out.println("Ahora introduse la contrazenia");
				
				String contrazenia = myObj.nextLine();
				if(contrazenia.equals(password)) {
					System.out.println("Access Granted");
					} else {
						System.out.println("Contazenia incorresta");
					}
				} else {
					System.out.println("El uzuario no ecsiste");	
							}
						} while (true); // Repite cada vez que 
		}
	}

