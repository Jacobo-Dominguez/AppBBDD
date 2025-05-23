import gestores.GestorResenas;
import gestores.GestorUsuarios;
import tablas.Resena;
import tablas.Usuario;

public class PrincipalResena {
    public static void main(String[] args) {
        GestorResenas gestorResenas = new GestorResenas();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        Usuario maria = gestorUsuarios.buscarPorEmail("maria.garcia@email.com");

        // Insertar reseña
        Resena nueva = new Resena(0, maria.getId(), 1, 9, "Muy divertido y desafiante.");
        gestorResenas.insertarResena(nueva);

        // Borrar reseña con ID 1 si es de Mario
        gestorResenas.borrarResena(1, maria.getId());


        // Mostrar reseñas del juego 1
        System.out.println("Reseñas para el juego 1:");
        for (Resena r : gestorResenas.obtenerResenasPorJuego(1)) {
            System.out.println("- Usuario ID: " + r.getIdUsuario() + ", Puntuación: " + r.getPuntuacion() + ", Comentario: " + r.getComentario());
        }


    }
}
