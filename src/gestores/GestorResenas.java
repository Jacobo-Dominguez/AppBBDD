package gestores;

import tablas.Resena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorResenas {

    // Función para insertar una nueva reseña
    public void insertarResena(Resena resena) {
        String query = "INSERT INTO resenas (id_usuario, id_juego, puntuacion, comentario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, resena.getIdUsuario());
            stmt.setInt(2, resena.getIdJuego());
            stmt.setInt(3, resena.getPuntuacion());
            stmt.setString(4, resena.getComentario());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Reseña añadida correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar reseña.");
            e.printStackTrace();
        }
    }

    // Metodo para obtener reseñas de un juego en especifíco (metodo para la prueba indiviudal PrincipalResena)
    public List<Resena> obtenerResenasPorJuego(int idJuego) {
        List<Resena> resenas = new ArrayList<>();
        String query = "SELECT * FROM resenas WHERE id_juego = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idJuego);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Resena resena = new Resena(
                        rs.getInt("id_resena"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_juego"),
                        rs.getInt("puntuacion"),
                        rs.getString("comentario")
                );
                resenas.add(resena);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener reseñas.");
            e.printStackTrace();
        }

        return resenas;
    }

    // Función para borrar reseñas (solo si perteneces al usuario logeado)
    public void borrarResena(int idResena, int idUsuario) {
        String query = "DELETE FROM resenas WHERE id_resena = ? AND id_usuario = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idResena);
            stmt.setInt(2, idUsuario);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Reseña eliminada correctamente.");
            } else {
                System.out.println("No se encontró la reseña o no pertenece al usuario.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la reseña.");
            e.printStackTrace();
        }
    }

    // Función para obtener las reseñas de un juego selecionado (metodo para la parte gráfica)
    public List<String[]> obtenerResenasConNombres(int idJuego) {
        List<String[]> resenas = new ArrayList<>();
        String query = """
        SELECT usuarios.nombre, resenas.comentario, resenas.puntuacion
        FROM resenas
        JOIN usuarios ON resenas.id_usuario = usuarios.id_usuario
        WHERE resenas.id_juego = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idJuego);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreUsuario = rs.getString("nombre");
                String comentario = rs.getString("comentario");
                int puntuacion = rs.getInt("puntuacion");

                resenas.add(new String[]{nombreUsuario, comentario, String.valueOf(puntuacion)});
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener reseñas.");
            e.printStackTrace();
        }

        return resenas;
    }

    // Función para obtener las reseñas de un usuario (metodo para la parte gráfica)
    public List<String[]> obtenerResenasDeUsuario(int idUsuario) {
        List<String[]> resenas = new ArrayList<>();
        String query = """
        SELECT juegos.nombre, resenas.comentario, resenas.puntuacion, resenas.id_resena
        FROM resenas
        JOIN juegos ON resenas.id_juego = juegos.id_juego
        WHERE resenas.id_usuario = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreJuego = rs.getString("nombre");
                String comentario = rs.getString("comentario");
                int puntuacion = rs.getInt("puntuacion");
                int idResena = rs.getInt("id_resena");

                resenas.add(new String[]{nombreJuego, comentario, String.valueOf(puntuacion), String.valueOf(idResena)});
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener reseñas del usuario.");
            e.printStackTrace();
        }

        return resenas;
    }

    // Función para editar una reseña escrita por usuario (solo si eres ese usuario)
    public boolean editarResena(int usuarioId, String nombreJuego, String nuevoComentario, int nuevaPuntuacion) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE resenas SET comentario = ?, puntuacion = ? WHERE id_usuario = ? " +
                            "AND id_juego = (SELECT id_juego FROM juegos WHERE nombre = ?)"
            );
            stmt.setString(1, nuevoComentario);
            stmt.setInt(2, nuevaPuntuacion);
            stmt.setInt(3, usuarioId);
            stmt.setString(4, nombreJuego);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
