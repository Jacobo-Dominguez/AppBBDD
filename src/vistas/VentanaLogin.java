package vistas;

import gestores.GestorUsuarios;
import tablas.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JTextField campoEmail; // Campo para escribir el correo
    private JButton botonEntrar; // Boton de logear
    private JButton botonCrearUsuario;  // Boton de crear usuario

    public VentanaLogin() {
        setTitle("Login SteamDB");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        JLabel etiquetaEmail = new JLabel("Email:");
        etiquetaEmail.setBounds(20, 20, 80, 25);
        Estilos.estilizarEtiqueta(etiquetaEmail);
        add(etiquetaEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(100, 20, 160, 25);
        Estilos.estilizarCampoTexto(campoEmail);
        add(campoEmail);

        // Botón para iniciar sesion
        botonEntrar = new JButton("Entrar");
        botonEntrar.setBounds(100, 60, 80, 30);
        Estilos.estilizarBoton(botonEntrar);
        add(botonEntrar);

        // Botón para crear un nuevo usuario
        botonCrearUsuario = new JButton("Crear usuario");
        botonCrearUsuario.setBounds(100, 100, 140, 30);
        Estilos.estilizarBoton(botonCrearUsuario);
        add(botonCrearUsuario);

        // Evento de mensaje al iniciar session
        botonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = campoEmail.getText().trim();
                if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {  // Regex simple
                    JOptionPane.showMessageDialog(null, "Formato de email inválido");
                    return;
                }
                GestorUsuarios gestor = new GestorUsuarios();
                Usuario usuario = gestor.buscarPorEmail(email);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario.getNombre());
                    dispose(); // Cierra login
                    new VentanaMenu(usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }
            }
        });

        // Acción del botón "Crear Usuario"
        botonCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir ventana para crear un nuevo usuario
                new VentanaCrearUsuario();
            }
        });

        setVisible(true);
    }
}
