class Coche {
	
	String matricula;
	String marca;
	String modelo;
	String color;
	int potencia;
	String tipo;
	int year;
	int consumo;
	int velocidades;
	
	public Coche(String licensePlate, String brand, String model) {
	
		matricula = licensePlate;
		marca = brand;
		modelo = model;
	}
	
	public static void main (String[] args) {
	
		Coche miCoshe = new Coche("6969420", "Toyota", "Prius");
		System.out.println(miCoshe.matricula+miCoshe.marca+miCoshe.modelo);
	}
}

