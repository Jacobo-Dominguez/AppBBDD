package gestores;

import tablas.Juego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorJuegos {

    // Metodo para obtener todos los juegos
    public List<Juego> obtenerTodosLosJuegos() {
        List<Juego> juegos = new ArrayList<>();
        String query = "SELECT * FROM juegos";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) { // Se recorre el resultado y se agregan objetos Juego a la lista
                Juego juego = new Juego(
                        rs.getInt("id_juego"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getFloat("precio"),
                        rs.getString("fecha_lanzamiento")
                );
                juegos.add(juego);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los juegos.");
            e.printStackTrace();
        }

        return juegos;
    }

    // Función para insertar un nuevo juego
    public void insertarJuego(Juego juego) {
        String query = "INSERT INTO juegos (nombre, genero, precio, fecha_lanzamiento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, juego.getNombre());
            stmt.setString(2, juego.getGenero());
            stmt.setFloat(3, juego.getPrecio());
            stmt.setString(4, juego.getFechaLanzamiento());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Juego insertado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el juego.");
            e.printStackTrace();
        }
    }

    // Metodo para obtener un juego por su ID
    public Juego obtenerPorId(int idJuego) {
        Juego juego = null;
        String query = "SELECT * FROM juegos WHERE id_juego = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idJuego);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                juego = new Juego(
                        rs.getInt("id_juego"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getFloat("precio"),
                        rs.getString("fecha_lanzamiento")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener juego por ID.");
            e.printStackTrace();
        }

        return juego;
    }
}
