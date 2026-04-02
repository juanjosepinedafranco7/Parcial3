package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import entidades.*;
import logica.CalculadoraVentas;

public class GUIVentas extends JFrame {
    private JTextField txtNombre, txtEdad, txtTelefono, txtTipo, txtProducto, txtValorUnitario, txtCantidad;
    private JLabel lblNombre, lblTipo, lblPrecioTotal, lblDescuento, lblValorFinal, lblError;
    private JPanel facturaTotal;

    public GUIVentas() {
        configurarVentana();
        PanelFormulario();
        OpcionBotones();
        Resultados();
    }

    private void configurarVentana() {
        setTitle("Tienda DON APARATO");
        setSize(550, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
    }

    private void PanelFormulario() {
        JPanel pnlContenedor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel formulario = new JPanel(new GridLayout(8, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente y Producto"));

        formulario.add(new JLabel("Nombre:")); txtNombre = new JTextField(20); formulario.add(txtNombre);
        formulario.add(new JLabel("Edad:")); txtEdad = new JTextField(); formulario.add(txtEdad);
        formulario.add(new JLabel("Teléfono:")); txtTelefono = new JTextField(); formulario.add(txtTelefono);
        formulario.add(new JLabel("Tipo (A, B, C):")); txtTipo = new JTextField(); formulario.add(txtTipo);
        formulario.add(new JLabel("Producto:")); txtProducto = new JTextField(); formulario.add(txtProducto);
        formulario.add(new JLabel("Valor Unitario ($):")); txtValorUnitario = new JTextField(); formulario.add(txtValorUnitario);
        formulario.add(new JLabel("Cantidad:")); txtCantidad = new JTextField(); formulario.add(txtCantidad);

        pnlContenedor.add(formulario);
        add(pnlContenedor, BorderLayout.NORTH);
    }

    private void OpcionBotones() {
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnCompra = new JButton("Realizar Compra");
        btnCompra.setBackground(new Color(0, 128, 0)); btnCompra.setForeground(Color.WHITE);
        
        JButton btnMostrar = new JButton("Mostrar Datos");
        btnMostrar.setBackground(new Color(0, 51, 102)); btnMostrar.setForeground(Color.WHITE);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(204, 0, 0)); btnLimpiar.setForeground(Color.WHITE);

        btnCompra.addActionListener(e -> realizarCompra());
        btnMostrar.addActionListener(e -> Datos());
        btnLimpiar.addActionListener(e -> limpiar());

        botones.add(btnCompra); botones.add(btnMostrar); botones.add(btnLimpiar);
        add(botones, BorderLayout.CENTER);
    }

    private void Resultados() {
        facturaTotal = new JPanel(new GridLayout(0, 1, 5, 5));
        facturaTotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 128, 0)), "Resultados"));
        
        lblError = new JLabel("Los campos se encuentran vacíos."); lblError.setForeground(Color.RED);
        lblNombre = new JLabel(); lblTipo = new JLabel(); lblPrecioTotal = new JLabel();
        lblDescuento = new JLabel(); lblValorFinal = new JLabel();

        facturaTotal.add(lblError); facturaTotal.add(lblNombre); facturaTotal.add(lblTipo);
        facturaTotal.add(lblPrecioTotal); facturaTotal.add(lblDescuento); facturaTotal.add(lblValorFinal);
        facturaTotal.setVisible(false);
        add(facturaTotal, BorderLayout.SOUTH);
    }

    private void realizarCompra() {
        if (txtNombre.getText().isEmpty() || txtValorUnitario.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            lblError.setVisible(true);
            actualizarVisibilidadEtiquetas(false);
            facturaTotal.setVisible(true);
            pack();
            return;
        }

        try {
            lblError.setVisible(false);
            actualizarVisibilidadEtiquetas(true);
            
            Cliente cliente = new Cliente(txtNombre.getText(), Integer.parseInt(txtEdad.getText()), txtTelefono.getText(), txtTipo.getText());
            Producto producto = new Producto(txtProducto.getText(), Double.parseDouble(txtValorUnitario.getText()), Integer.parseInt(txtCantidad.getText()));

            double total = CalculadoraVentas.calcularValorTotal(producto);
            double descuento = CalculadoraVentas.calcularDescuento(cliente, total);
            double precioFinal = CalculadoraVentas.calcularPrecioFinal(total, descuento);
            String mensaje = CalculadoraVentas.mensaje(cliente, descuento);
    
            lblNombre.setText("Cliente: " + cliente.getNombre());
            lblTipo.setText("Tipo: " + (cliente.getTipo().isEmpty() ? "Vacio" : cliente.getTipo()));
            lblPrecioTotal.setText("Total: $" + String.format("%.3f", total));
            lblDescuento.setText(mensaje);
            lblValorFinal.setText("Valor Final: $" + String.format("%.3f", precioFinal));

            facturaTotal.setVisible(true);
            pack();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de números");
        }
    }

    private void actualizarVisibilidadEtiquetas(boolean visible) {
        lblNombre.setVisible(visible); lblTipo.setVisible(visible);
        lblPrecioTotal.setVisible(visible); lblDescuento.setVisible(visible);
        lblValorFinal.setVisible(visible);
    }

    private void Datos() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos se encuentran vacíos.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Nombre: " + txtNombre.getText() + "\nTeléfono: " + txtTelefono.getText());
    }

    private void limpiar() {
        txtNombre.setText(""); txtEdad.setText(""); txtTelefono.setText("");
        txtTipo.setText(""); txtProducto.setText(""); txtValorUnitario.setText(""); txtCantidad.setText("");
        facturaTotal.setVisible(false);
        pack();
    }
}