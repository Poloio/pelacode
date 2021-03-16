package cinterfaces;

import java.util.Date;

public interface Alumno {

    //Calcula la edad según una fecha de nacimiento
    int getEdad();

    //Setter que modifica la fecha de nacimento
    void setFechaNacimiento(Date fechaNacimiento);

    // Getter y setter para el nombre completo del alumno
    String getNombreApellidos();

    void setNombreApellidos(String nombreApellidos);

    //Getter para obtener el ID de alumno, no hay setter para evitar errores
    int getId();
}
