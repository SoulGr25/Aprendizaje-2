package Persona;

import java.time.LocalDate;
import java.time.Period;

public class Persona {
    private String nombre;
    private String apellido;
    private String genero; // "M" o "F"
    private LocalDate fechaNacimiento;

    public Persona(String nombre, String apellido, String genero, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero.toUpperCase().substring(0, 1); // Solo la primera letra
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) {
        if (genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F")) {
            this.genero = genero.toUpperCase().substring(0,1);
        }
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + getEdad() +
                '}';
    }
}


