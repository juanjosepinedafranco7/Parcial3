package logica;

import entidades.Cliente;
import entidades.Producto;

public class CalculadoraVentas {

    public static double calcularValorTotal(Producto producto) {
        return producto.getValor() * producto.getCantidad();
    }

    public static double calcularDescuento(Cliente cliente, double totalFactura) {
        String tipo = cliente.getTipo().toUpperCase();
        switch (tipo) {
            case "A": return totalFactura * 0.40;
            case "B": return totalFactura * 0.20;
            case "C": return totalFactura * 0.10;
            default: return 0;
        }
    }
    
    public static double calcularPrecioFinal(double total, double descuento) {
        return total - descuento;
    }

    public static String mensaje(Cliente cliente, double descuento) {
        String tipo = cliente.getTipo().toUpperCase();
        switch (tipo) {
            case "A": return "Descuento del 40%: -$" + String.format("%.3f", descuento);
            case "B": return "Descuento del 20%: -$" + String.format("%.3f", descuento);
            case "C": return "Descuento del 10%: -$" + String.format("%.3f", descuento);
            default: return "No se le realiza descuento";
         }
    }
}