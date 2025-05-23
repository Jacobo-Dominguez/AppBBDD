package vistas;

import gestores.GestorCompras;
import gestores.GestorJuegos;
import tablas.Compra;
import tablas.Juego;
import tablas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class VentanaJuegos extends JFrame {
    private JTable tablaJuegos;
    private DefaultTableModel modelo;
    private Usuario usuario;
    private GestorJuegos gestorJuegos = new GestorJuegos();
    private GestorCompras gestorCompras = new GestorCompras();

    public VentanaJuegos(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Lista de Juegos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel titulo = new JLabel("Juegos disponibles");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(20, 10, 200, 25);
        add(titulo);

        /*
         JTable es el componente visual que representa una tabla.
         DefaultTableModel es el modelo de datos que contiene las filas y columnas.
         Aquí definimos las columnas: "ID", "Nombre", "Género", "Precio".
        */
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Género", "Precio"}, 0);
        tablaJuegos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaJuegos);
        scroll.setBounds(20, 40, 540, 200);
        add(scroll);

        JButton btnComprar = new JButton("Comprar juego seleccionado");
        btnComprar.setBounds(20, 260, 250, 30);
        Estilos.estilizarBoton(btnComprar);
        add(btnComprar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(450, 310, 100, 30);
        Estilos.estilizarBoton(btnVolver);
        add(btnVolver);

        cargarJuegos();

        btnComprar.addActionListener((ActionEvent e) -> {
            int fila = tablaJuegos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un juego primero.");
                return;
            }

            int idJuego = (int) modelo.getValueAt(fila, 0);

            // Comprobar si ya se ha comprado el juego
            if (compradoPorUsuario(idJuego)) {
                JOptionPane.showMessageDialog(null, "Ya has comprado este juego.");
                return;
            }

            // Registrar la compra con fecha actual
            Compra compra = new Compra(0, usuario.getId(), idJuego, java.time.LocalDate.now().toString());
            gestorCompras.registrarCompra(compra);

            JOptionPane.showMessageDialog(null, "¡Juego comprado exitosamente!");
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaMenu(usuario);
        });

        setVisible(true);

        JButton btnVerResenas = new JButton("Ver Reseñas");
        btnVerResenas.setBounds(280, 260, 140, 30);
        Estilos.estilizarBoton(btnVerResenas);
        add(btnVerResenas);

        btnVerResenas.addActionListener((ActionEvent e) -> {
            int fila = tablaJuegos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un juego primero.");
                return;
            }

            int idJuego = (int) modelo.getValueAt(fila, 0);
            dispose();
            new VentanaResenas(usuario, idJuego);
        });
    }

    private void cargarJuegos() {
        List<Juego> juegos = gestorJuegos.obtenerTodosLosJuegos();
        for (Juego j : juegos) {
            modelo.addRow(new Object[]{j.getId(), j.getNombre(), j.getGenero(), j.getPrecio()});
        }
    }

    private boolean compradoPorUsuario(int idJuego) {
        List<Compra> compras = gestorCompras.obtenerComprasPorUsuario(usuario.getId());
        for (Compra compra : compras) {
            if (compra.getIdJuego() == idJuego) {
                return true; // Ya compró el juego
            }
        }
        return false; // No ha comprado el juego
    }
}

