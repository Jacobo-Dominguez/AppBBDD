package vistas;

import gestores.GestorJuegos;
import gestores.GestorResenas;
import tablas.Resena;
import tablas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaResenas extends JFrame {
    private JTable tablaResenas;
    private DefaultTableModel modelo;
    private Usuario usuario;
    private GestorResenas gestorResenas = new GestorResenas();
    private GestorJuegos gestorJuegos = new GestorJuegos();

    public VentanaResenas(Usuario usuario, int idJuego) {
        this.usuario = usuario;

        setTitle("Reseñas del Juego");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel titulo = new JLabel("Reseñas de: " + gestorJuegos.obtenerPorId(idJuego).getNombre());
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(20, 10, 500, 25);
        add(titulo);

        /*
         JTable es el componente visual que representa una tabla.
         DefaultTableModel es el modelo de datos que contiene las filas y columnas.
         Aquí definimos las columnas: "Usuario", "Comentario", "Puntuación".
        */
        modelo = new DefaultTableModel(new Object[]{"Usuario", "Comentario", "Puntuación"}, 0);
        tablaResenas = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaResenas);
        scroll.setBounds(20, 40, 540, 200);
        add(scroll);

        JButton btnAgregarResena = new JButton("Agregar Reseña");
        btnAgregarResena.setBounds(20, 260, 150, 30);
        Estilos.estilizarBoton(btnAgregarResena);
        add(btnAgregarResena);

        JButton btnBorrarResena = new JButton("Borrar Reseña");
        btnBorrarResena.setBounds(200, 260, 150, 30);
        Estilos.estilizarBoton(btnBorrarResena);
        add(btnBorrarResena);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(450, 310, 100, 30);
        Estilos.estilizarBoton(btnVolver);
        add(btnVolver);

        cargarResenas(idJuego);

        btnAgregarResena.addActionListener((ActionEvent e) -> {
            // Mostrar un cuadro de texto para agregar la reseña
            String comentario = JOptionPane.showInputDialog("Introduce tu comentario:");
            String puntuacionStr = JOptionPane.showInputDialog("Introduce la puntuación (1-10):");

            try {
                int puntuacion = Integer.parseInt(puntuacionStr);
                if (puntuacion < 1 || puntuacion > 10) {
                    JOptionPane.showMessageDialog(null, "Puntuación no válida. Debe ser entre 1 y 10.");
                } else {
                    Resena nuevaResena = new Resena(0, usuario.getId(), idJuego, puntuacion, comentario);
                    gestorResenas.insertarResena(nuevaResena);
                    modelo.addRow(new Object[]{usuario.getNombre(), comentario, puntuacion});
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Puntuación debe ser un número.");
            }
        });

        btnBorrarResena.addActionListener((ActionEvent e) -> {
            int filaSeleccionada = tablaResenas.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una reseña para borrar.");
                return;
            }

            String usuarioResena = (String) modelo.getValueAt(filaSeleccionada, 0);
            if (!usuarioResena.equals(usuario.getNombre())) {
                JOptionPane.showMessageDialog(null, "Solo puedes borrar tus propias reseñas.");
                return;
            }

            // Lógica para borrar la reseña
            String comentario = (String) modelo.getValueAt(filaSeleccionada, 1);
            Resena resenaAEliminar = new Resena(0, usuario.getId(), idJuego, 0, comentario);
            gestorResenas.borrarResena(resenaAEliminar.getId(), usuario.getId());
            modelo.removeRow(filaSeleccionada);
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaJuegos(usuario);
        });

        setVisible(true);
    }

    private void cargarResenas(int idJuego) {
        List<String[]> resenas = gestorResenas.obtenerResenasConNombres(idJuego);

        for (String[] fila : resenas) {
            modelo.addRow(fila);
        }

        if (resenas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reseñas para este juego.");
        }
    }
}
