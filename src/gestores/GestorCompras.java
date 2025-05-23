package gestores;

import tablas.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorCompras {

    // Función para registrar una nueva compra
    public void registrarCompra(Compra compra) {
        // Consulta SQL para insertar una nueva compra
        String query = "INSERT INTO compras (id_usuario, id_juego, fecha_compra) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Se asignan los valores a los parámetros del PreparedStatement
            stmt.setInt(1, compra.getIdUsuario());
            stmt.setInt(2, compra.getIdJuego());
            stmt.setString(3, compra.getFechaCompra());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Compra registrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la compra.");
            e.printStackTrace();
        }
    }

    // Metodo para obtener todas las compras de un usuario (metodo para la prueba indiviudal PrincipalCompra)
    public List<Compra> obtenerComprasPorUsuario(int idUsuario) {
        List<Compra> compras = new ArrayList<>();
        String query = "SELECT * FROM compras WHERE id_usuario = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario); // Se establece el ID del usuario como parámetro para filtrar las compras
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { // Se recorren los resultados y se crean objetos Compra para cada registro
                Compra compra = new Compra(
                        rs.getInt("id_compra"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_juego"),
                        rs.getString("fecha_compra")
                );
                compras.add(compra);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener compras del usuario.");
            e.printStackTrace();
        }

        return compras;
    }

    // Función para calcular gasto total de un usuario
    public float calcularGastoTotal(int idUsuario) {
        float total = 0f;
        // Consulta que suma los precios de los juegos que el usuario ha comprado
        String query = """
            SELECT SUM(j.precio) as total
            FROM compras c
            JOIN juegos j ON c.id_juego = j.id_juego
            WHERE c.id_usuario = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Si hay resultado, se extrae el total de la columna "total"
                total = rs.getFloat("total");
            }

        } catch (SQLException e) {
            System.out.println("Error al calcular gasto total.");
            e.printStackTrace();
        }

        return total;
    }

    // Función que devuelve las compras de un usuario incluyendo el nombre del juego, precio y fecha
    public List<String[]> obtenerComprasDeUsuarioConNombres(int idUsuario) {
        List<String[]> lista = new ArrayList<>();
        // Consulta SQL que une las tablas compras y juegos para obtener los datos necesarios
        String query = """
        SELECT juegos.nombre, juegos.precio, compras.fecha_compra
        FROM compras
        JOIN juegos ON compras.id_juego = juegos.id_juego
        WHERE compras.id_usuario = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) { // Se recorre el resultado y se guarda cada fila como un conjunto de Strings
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String fecha = rs.getString("fecha_compra");

                lista.add(new String[]{nombre, String.valueOf(precio), fecha});
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener compras.");
            e.printStackTrace();
        }

        return lista;
    }

}
