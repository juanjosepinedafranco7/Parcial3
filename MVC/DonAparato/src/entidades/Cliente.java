package entidades;

public class Cliente {
    private String nombre;
    private int edad;
    private String telefono;
    private String tipo;

    public Cliente(String nombre, int edad, String telefono, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
}