package gestores;

import tablas.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    // Función para insertar un nuevo usuario
    public void insertarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre, email, tipo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTipo());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario insertado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el usuario.");
            e.printStackTrace();
        }
    }

    // Metodo para obtener todos los usuarios (usado en parte grafica y en PrincipalUsuario)
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("tipo")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los usuarios.");
            e.printStackTrace();
        }

        return usuarios;
    }

    // Función para validar login por email (devuelve null si no existe)
    public Usuario buscarPorEmail(String email) {
        String query = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("tipo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario.");
            e.printStackTrace();
        }

        return usuario;
    }

    // Función para borrar usuario (solo si eres admin)
    public void borrarUsuario(int idUsuario, Usuario solicitante) {
        if (!solicitante.getTipo().equals("admin")) {
            System.out.println("Permiso denegado: solo los administradores pueden borrar usuarios.");
            return;
        }

        // Borrado en cascada para poder borrar usuarios que tengan reseñas o compras
        String eliminarResenas = "DELETE FROM resenas WHERE id_usuario = ?";
        String eliminarCompras = "DELETE FROM compras WHERE id_usuario = ?";
        String eliminarUsuario = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement stmtResenas = conn.prepareStatement(eliminarResenas);
                    PreparedStatement stmtCompras = conn.prepareStatement(eliminarCompras);
                    PreparedStatement stmtUsuario = conn.prepareStatement(eliminarUsuario)
            ) {
                // Eliminar reseñas primero
                stmtResenas.setInt(1, idUsuario);
                stmtResenas.executeUpdate();

                // Luego eliminar compras
                stmtCompras.setInt(1, idUsuario);
                stmtCompras.executeUpdate();

                // Finalmente eliminar el usuario
                stmtUsuario.setInt(1, idUsuario);
                int filas = stmtUsuario.executeUpdate();

                if (filas > 0) {
                    System.out.println("Usuario, reseñas y compras eliminados correctamente.");
                } else {
                    System.out.println("No se encontró el usuario con ID: " + idUsuario);
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback(); // Deshacer si falla algo
                System.out.println("Error al eliminar usuario y sus datos relacionados.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Función para registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
        // Verificamos que los campos no estén vacíos
        if (usuario.getNombre().isEmpty() || usuario.getEmail().isEmpty() || usuario.getTipo().isEmpty()) {
            System.out.println("Todos los campos deben estar completos.");
            return;
        }

        String query = "INSERT INTO usuarios (nombre, email, tipo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTipo());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario registrado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario.");
            e.printStackTrace();
        }
    }

}
