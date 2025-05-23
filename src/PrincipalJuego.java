import gestores.GestorJuegos;
import tablas.Juego;

public class PrincipalJuego {
    public static void main(String[] args) {
        GestorJuegos gestor = new GestorJuegos();

        // Mostrar todos los juegos
        System.out.println("Listado de juegos:");
        for (Juego j : gestor.obtenerTodosLosJuegos()) {
            System.out.println(j.getNombre() + " - " + j.getGenero() + " - " + j.getPrecio() + "€");
        }

        // Insertar un juego nuevo
        Juego nuevo = new Juego(0, "Clair Obscur: Expedition 33", "JRPG", 49.99f, "2017-04-23");
        gestor.insertarJuego(nuevo);
    }
}
