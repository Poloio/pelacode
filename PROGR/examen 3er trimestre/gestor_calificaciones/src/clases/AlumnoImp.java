package clases;

import cinterfaces.Alumno;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlumnoImp implements Alumno {

    public static int numAlumnos;

    private int id;
    private Date fechaNacimiento;
    private String nombreApellidos;

    public AlumnoImp(Date fechaNacimiento, String nombreApellidos) {
        this.fechaNacimiento = fechaNacimiento;
        this.nombreApellidos = nombreApellidos;
        numAlumnos++;//Sumamos 1 al numero de alumnos y lo usamos para asignar el ID
        id = numAlumnos;
    }

    //Devuelve la edad del alumno teniendo en cuenta la fecha de nacimiento respecto a la actual
    @Override
    public int getEdad() {
        //Voy a usar los métodos deprecados de Date porque no he practicado fechas
        //No voy a implementar diferenciar los días
        //TODO tener en cuenta días para la edad

        Calendar cal = GregorianCalendar.getInstance();
        Date fechaActual = cal.getTime();

        return fechaActual.getYear() - fechaNacimiento.getYear();
    }

    @Override
    public String toString() {

        return "Identificador: "+id+"\n" +
                "Edad: "+getEdad()+"\n" +
                "Nombre y Apellidos: "+nombreApellidos+"\n" +
                "Fecha de nacimiento: "+fechaNacimiento+"\n";
    }

    @Override
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    @Override
    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    @Override
    public int getId() {
        return id;
    }
}
