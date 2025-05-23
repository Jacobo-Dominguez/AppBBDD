package vistas;

import gestores.GestorCompras;
import tablas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaCompras extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private Usuario usuario;
    private GestorCompras gestorCompras = new GestorCompras();

    public VentanaCompras(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Historial de Compras");
        setSize(500, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        /*
         JTable es el componente visual que representa una tabla.
         DefaultTableModel es el modelo de datos que contiene las filas y columnas.
         Aquí definimos las columnas: Juego", "Precio (€)", "Fecha.
        */
        modelo = new DefaultTableModel(new Object[]{"Juego", "Precio (€)", "Fecha"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 20, 440, 220);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(190, 260, 100, 30);
        Estilos.estilizarBoton(btnVolver);
        add(btnVolver);

        cargarCompras();

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaMenu(usuario);
        });

        setVisible(true);
    }

    private void cargarCompras() {
        List<String[]> compras = gestorCompras.obtenerComprasDeUsuarioConNombres(usuario.getId());

        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes compras registradas.");
        } else {
            for (String[] fila : compras) {
                modelo.addRow(fila);  // Llenamos la tabla con los datos de las compras
            }
        }
    }
}
