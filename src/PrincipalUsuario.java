import gestores.GestorUsuarios;
import tablas.Usuario;

public class PrincipalUsuario {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        // Insertar nuevo usuario
        Usuario nuevoUsuario = new Usuario(0, "Mario", "mario@email.com", "cliente");
        gestorUsuarios.insertarUsuario(nuevoUsuario);

        // Listar usuarios
        System.out.println("Usuarios en la base de datos:");
        for (Usuario u : gestorUsuarios.obtenerTodosLosUsuarios()) {
            System.out.println(u.getNombre() + " - " + u.getEmail() + " - " + u.getTipo());
        }

        // Buscar usuario por email
        Usuario buscado = gestorUsuarios.buscarPorEmail("mario@email.com");
        if (buscado != null) {
            System.out.println("Usuario encontrado: " + buscado.getNombre() + " (" + buscado.getTipo() + ")");
        } else {
            System.out.println("Usuario no encontrado.");
        }

        // Simular login de admin
        Usuario admin = gestorUsuarios.buscarPorEmail("david.torres@email.com"); // este es un admin según tu SQL inicial
        Usuario cliente = gestorUsuarios.buscarPorEmail("laura@mail.com");

        // Borrar un usuario como admin (permitido)
        gestorUsuarios.borrarUsuario(10, admin); // por ejemplo, borra a Diego

        // Borrar un usuario como cliente (denegado)
        gestorUsuarios.borrarUsuario(2, cliente);
    }
}
