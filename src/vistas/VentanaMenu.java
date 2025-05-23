package vistas;

import tablas.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu extends JFrame {
    private Usuario usuario;

    public VentanaMenu(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Principal - " + usuario.getTipo());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("/resources/icono.png")).getImage());

        Estilos.aplicarEstiloVentana(this); // Aplicamos el estilo general a la ventana

        // Botón para ver los juegos disponibles en el servicio
        JButton btnJuegos = new JButton("Ver Juegos");
        btnJuegos.setBounds(50, 30, 140, 30);
        Estilos.estilizarBoton(btnJuegos);
        add(btnJuegos);
        btnJuegos.addActionListener(e -> {
            dispose();
            new VentanaJuegos(usuario);
        });

        // Botón para ver las compras del usuario que ha iniciado sesion
        JButton btnCompras = new JButton("Mis Compras");
        btnCompras.setBounds(200, 30, 140, 30);
        Estilos.estilizarBoton(btnCompras);
        add(btnCompras);
        btnCompras.addActionListener(e -> {
            dispose();
            new VentanaCompras(usuario);
        });

        // Botón para ver las reseñas que ha escrito en cualquier juego el usuario
        JButton btnResenas = new JButton("Reseñas");
        btnResenas.setBounds(50, 80, 140, 30);
        Estilos.estilizarBoton(btnResenas);
        add(btnResenas);
        btnResenas.addActionListener(e -> {
            dispose();
            new VentanaMisResenas(usuario);
        });

        // Solo si el usuario es admin tendra disponible un boton para borrar usuarios
        if (usuario.getTipo().equals("admin")) {
            JButton btnUsuarios = new JButton("Gestor Usuarios");
            btnUsuarios.setBounds(200, 80, 140, 30);
            Estilos.estilizarBoton(btnUsuarios);
            add(btnUsuarios);

            // Acción del botón "Gestionar Usuarios"
            btnUsuarios.addActionListener(e -> {
                dispose();
                new VentanaAdministrarUsuarios(usuario);  // Abre la ventana para gestionar usuarios
            });
        }

        // Botón Cerrar Sesión
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(50, 150, 140, 30);
        Estilos.estilizarBoton(btnCerrarSesion);
        add(btnCerrarSesion);
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });

        // Botón de salir (cerrar app)
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 150, 140, 30);
        Estilos.estilizarBoton(btnSalir);
        add(btnSalir);

        btnSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Hasta pronto.");
            System.exit(0);
        });

        setVisible(true);
    }
}
