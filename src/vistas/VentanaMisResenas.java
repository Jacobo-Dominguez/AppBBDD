package vistas;

import gestores.GestorResenas;
import tablas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaMisResenas extends JFrame {
    private JTable tablaResenas;
    private DefaultTableModel modelo;
    private Usuario usuario;
    private GestorResenas gestorResenas = new GestorResenas();

    public VentanaMisResenas(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Mis Reseñas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel titulo = new JLabel("Tus Reseñas");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(20, 10, 200, 25);
        add(titulo);

        /*
         JTable es el componente visual que representa una tabla.
         DefaultTableModel es el modelo de datos que contiene las filas y columnas.
         Aquí definimos las columnas: "Juego", "Comentario", "Puntuación".
        */
        modelo = new DefaultTableModel(new Object[]{"Juego", "Comentario", "Puntuación"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No editable directamente
            }
        };
        tablaResenas = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaResenas);
        scroll.setBounds(20, 40, 540, 200);
        add(scroll);

        JButton btnEditar = new JButton("Editar reseña");
        btnEditar.setBounds(320, 260, 120, 30);
        Estilos.estilizarBoton(btnEditar);
        add(btnEditar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(450, 260, 100, 30);
        Estilos.estilizarBoton(btnVolver);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaMenu(usuario);
        });

        btnEditar.addActionListener(e -> {
            int fila = tablaResenas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una reseña para editar.");
                return;
            }

            String juego = (String) modelo.getValueAt(fila, 0);
            String comentarioActual = (String) modelo.getValueAt(fila, 1);
            String puntuacionActual = (String) modelo.getValueAt(fila, 2);

            String nuevoComentario = JOptionPane.showInputDialog(this, "Editar comentario:", comentarioActual);
            if (nuevoComentario == null) return;

            String nuevaPuntuacion = JOptionPane.showInputDialog(this, "Editar puntuación (0-10):", puntuacionActual);
            if (nuevaPuntuacion == null) return;

            try {
                int puntuacion = Integer.parseInt(nuevaPuntuacion);
                if (puntuacion < 0 || puntuacion > 10) throw new NumberFormatException();

                boolean exito = gestorResenas.editarResena(usuario.getId(), juego, nuevoComentario, puntuacion);
                if (exito) {
                    modelo.setValueAt(nuevoComentario, fila, 1);
                    modelo.setValueAt(nuevaPuntuacion, fila, 2);
                    JOptionPane.showMessageDialog(this, "Reseña actualizada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al actualizar reseña.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Puntuación no válida.");
            }
        });

        cargarMisResenas();
        setVisible(true);
    }

    private void cargarMisResenas() {
        List<String[]> resenas = gestorResenas.obtenerResenasDeUsuario(usuario.getId());
        modelo.setRowCount(0); // limpiar por si recargas

        for (String[] fila : resenas) {
            modelo.addRow(fila);
        }

        if (resenas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No has dejado reseñas.");
        }
    }
}
