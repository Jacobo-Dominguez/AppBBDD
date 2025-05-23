package vistas;

import gestores.GestorUsuarios;
import tablas.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearUsuario extends JFrame {
    private JTextField campoNombre;
    private JTextField campoEmail;
    private JComboBox<String> comboTipo; // Para seleccionar tipo de usuario
    private JButton botonRegistrar;

    public VentanaCrearUsuario() {
        setTitle("Registrar Nuevo Usuario");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(20, 20, 80, 25);
        Estilos.estilizarEtiqueta(etiquetaNombre);
        add(etiquetaNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(100, 20, 160, 25);
        Estilos.estilizarCampoTexto(campoNombre);
        add(campoNombre);

        JLabel etiquetaEmail = new JLabel("Email:");
        etiquetaEmail.setBounds(20, 60, 80, 25);
        Estilos.estilizarEtiqueta(etiquetaEmail);
        add(etiquetaEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(100, 60, 160, 25);
        Estilos.estilizarCampoTexto(campoEmail);
        add(campoEmail);

        JLabel etiquetaTipo = new JLabel("Tipo:");
        etiquetaTipo.setBounds(20, 100, 80, 25);
        Estilos.estilizarEtiqueta(etiquetaTipo);
        add(etiquetaTipo);

        // Combo box para elegir tipo de usuario (admin o cliente)
        comboTipo = new JComboBox<>(new String[]{"admin", "cliente"});
        comboTipo.setBounds(100, 100, 160, 25);
        add(comboTipo);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(100, 140, 100, 30);
        Estilos.estilizarBoton(botonRegistrar);
        add(botonRegistrar);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText().trim();
                String email = campoEmail.getText().trim();
                String tipo = (String) comboTipo.getSelectedItem();

                // Validar los campos
                if (nombre.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                } else {
                    // Crear el nuevo usuario y guardarlo
                    Usuario nuevoUsuario = new Usuario(0, nombre, email, tipo); // ID se maneja automáticamente en la base de datos
                    GestorUsuarios gestor = new GestorUsuarios();
                    gestor.insertarUsuario(nuevoUsuario);

                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                    dispose(); // Cerrar ventana de registro
                }
            }
        });

        setVisible(true);
    }
}
