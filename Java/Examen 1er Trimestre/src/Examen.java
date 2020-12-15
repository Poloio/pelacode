public class Examen {

    // CASOS DE PRUEBA
    public static void main(String[] args) {
        System.out.println("El número de pasos desde (1,0) hasta (3,-3) son: "
                +printPath(1,0,3,-3));
        System.out.println("El número de pasos desde (-20,30) hasta (10,40) son: "
                +printPath(-20,30,10,40));

        System.out.println("\n\n");

        System.out.println("Un paralelogramo de numeros de altura 9 y base 10");
        printParallelogram(9,10,false);
        System.out.println("\n");
        System.out.println("Un paralelogramo de asteriscos de altura 5 y base 20");
        printParallelogram(5,20,true);

        System.out.println("\n\n");

        System.out.println("Una lista de números del 30 al 1");
        numberList(30);
    }

    /*
    Esta función calcula el número de pasos mínimo para ir de un punto de origen
    a un punto final.
    Precondiciones: Los números de las coordenadas deben ser enteros.
    Postcondiciones: El número de pasos devuelto es el camino más corto
    Entrada: xOrigin y yOrigin representan el punto de comienzo, y xEnd y yEnd el punto final.
    Salida: un entero que corresponde al número mínimo de pasos necesarios.
     */
    public static int printPath(int xOrigin, int yOrigin, int xEnd, int yEnd) {
        int xCurrent = xOrigin;
        int yCurrent = yOrigin;//Creamos un punto de seguimiento para saber por dónde vamos
        int steps = 0;

        while (xCurrent != xEnd || yCurrent != yEnd) {
            //Así se permite moverse en diagonal cuando sea necesario
            if (xCurrent < xEnd) {
                xCurrent++;
            } else if (xCurrent > xEnd){
                xCurrent--;
            }
            if (yCurrent < yEnd) {
                yCurrent ++;
            } else if (yCurrent > yEnd) {
                yCurrent--;
            }
            steps++;
        }
        return steps;
    }


    /*
    Un método que imprime un paralelogramo como se le indique.
        ****|  5 altura - 4 base - Asteriscos true
       **** |
      ****  |
     ****   |
    ****    |
    Precondiciones: La altura y la base serán mayores que 0, y altura menor o igual que 9 para no romperse
        cuando se elige imprimir con números.
    Postcondiciones: Se imprime por pantalla un paralelogramo de base, altura y relleno indicados.
    Entrada: altura y base que son enteros, y printAsteriscos un booleano que indica si se imprime con
        asteriscos (true) o con números (false)
    Salida: void

    Al principio había hecho un método para cada caso de printAsteriscos, pero
    con un if en la segunda parte del bucle de impresión de línea ahorro mucho código repetido.
     */
    public static void printParallelogram(int height, int base, boolean printAstericos) {
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < height-i; j++) {//Bucle que crea el espacio para hacer la escalera de líneas
                System.out.print(" ");
            }

            if (printAstericos) {//Si se quieren asteriscos se hace bucle de asteriscos
                for (int k = 0; k < base; k++) {
                    System.out.print("*");
                }
                for (int k = 0; k < i; k++) {//Bucle de espacio que aumenta cada iteración
                    System.out.print(" ");
                }
                System.out.print("|");

            } else {//Si no se quieren asteriscos se hace el bucle de numeritos
                for (int k = 0; k < base; k++) {
                    System.out.print(height - i);
                }
            }
            System.out.println();
        }
    }


    /*
        +++++++++ RECURSIVIDAD +++++++++++
        1. Es recursividad directa porque el método se llama a sí mismo.
        2. Hay un caso general, que es cuando n1 no sea 1, y un caso base que es cuando n1
            sea 1.
        3.
        El método escribe una lista en decremento del número
        de entrada y todos los que van detrás hasta el 1, separados por comas y con un punto al final.

        Precondiciones: number debe ser mayor que 0
        Postcondiciones: se escriben todos los números separados por comas hasta el 1,
            tras el que habrá un punto.
        Entrada: number un entero
        Salida: void
     */
    public static void numberList(int n1) {
        /*
        for(int i = number; i >= 1; i--) {
            if (i != 1) {
                System.out.print(i+", ");
            } else {
                System.out.println(i+".");
            }
        }
        Un bucle while quizás será más legible que esto
        */
        while (n1>0) {
            if (n1 != 1) {
                System.out.print(n1+", ");
            } else {
                System.out.println(n1+".");
            }
            n1--;
        } //Precioso
    }
}
