package vistas;

import gestores.GestorUsuarios;
import tablas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaAdministrarUsuarios extends JFrame {
    private JTable tablaUsuarios;  // Tabla visual para mostrar los usuarios
    private DefaultTableModel modelo;  // Modelo que gestiona los datos de la tabla
    private GestorUsuarios gestorUsuario = new GestorUsuarios();
    private Usuario admin;  // Usuario administrador que está logueado

    public VentanaAdministrarUsuarios(Usuario admin) {
        this.admin = admin;  // Guardar el usuario administrador
        setTitle("Administrar Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel titulo = new JLabel("Gestión de Usuarios");
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(20, 10, 200, 25);
        add(titulo);


        /*
         JTable es el componente visual que representa una tabla.
         DefaultTableModel es el modelo de datos que contiene las filas y columnas.
         Aquí definimos las columnas: ID, Nombre, Correo, Tipo (admin o no), y un botón de acción.
        */
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Correo", "Tipo", "Acción"}, 0);
        tablaUsuarios = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaUsuarios); // JScrollPane permite hacer scroll si hay muchos usuarios
        scroll.setBounds(20, 40, 540, 200);
        add(scroll);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(450, 260, 100, 30);
        Estilos.estilizarBoton(btnVolver);
        add(btnVolver);

        cargarUsuarios();

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaMenu(admin);
        });

        setVisible(true);
    }

    private void cargarUsuarios() {
        List<Usuario> usuarios = gestorUsuario.obtenerTodosLosUsuarios();

        for (Usuario usuario : usuarios) {
            // Convertimos el campo "tipo" para que se muestre "Sí" si es admin, "No" si es usuario normal
            String admin = usuario.getTipo().equals("admin") ? "Sí" : "No";
            modelo.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    admin,
                    "Eliminar" // Este sería el botón que luego cambiaremos
            });
        }

        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios para mostrar.");
        }

        // Añadir el ActionListener para el botón "Eliminar"
        tablaUsuarios.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaUsuarios.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox()));

    }

    // Metodo para eliminar un usuario
    private void eliminarUsuario(int usuarioId) {
        gestorUsuario.borrarUsuario(usuarioId, admin);  // El administrador que está logueado realiza la eliminación
        modelo.setRowCount(0);  // Limpiar la tabla
        cargarUsuarios();  // Cargar nuevamente los usuarios actualizados
    }

    // Clase para personalizar el renderizador de botón
    // Esta clase hace que la celda de la columna "Acción" se vea como un botón.
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText("Eliminar");
            return this;
        }
    }

    // Esta clase hace que el botón funcione: cuando haces clic, elimina al usuario correspondiente.
    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private int usuarioId;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            usuarioId = (int) table.getValueAt(row, 0);  // Obtener el ID del usuario
            JButton btnEliminar = new JButton(label);
            Estilos.estilizarBoton(btnEliminar);
            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    eliminarUsuario(usuarioId);  // Eliminar el usuario usando el metodo que ya definiste
                }
            });
            return btnEliminar;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }


}
