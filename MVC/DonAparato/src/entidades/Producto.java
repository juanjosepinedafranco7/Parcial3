package entidades;

public class Producto {
    private String nombre;
    private double valorUnitario;
    private int cantidad;

    public Producto(String nombre, double valorUnitario, int cantidad) {
        this.nombre = nombre;
        this.valorUnitario = valorUnitario;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public double getValor() { return valorUnitario; }
    public int getCantidad() { return cantidad; }
}