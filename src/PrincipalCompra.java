import gestores.GestorCompras;
import gestores.GestorUsuarios;
import tablas.Compra;
import tablas.Usuario;

import java.util.List;

public class PrincipalCompra {
    public static void main(String[] args) {
        GestorCompras gestorCompras = new GestorCompras();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        // Simulamos que Juan (cliente) compra un juego
        Usuario juan = gestorUsuarios.buscarPorEmail("juan.perez@email.com");

        Compra nuevaCompra = new Compra(0, juan.getId(), 1, "2024-05-13"); // id_juego = 1
        gestorCompras.registrarCompra(nuevaCompra);

        // Mostrar compras de Juan
        List<Compra> compras = gestorCompras.obtenerComprasPorUsuario(juan.getId());
        System.out.println("Compras de Juan:");
        for (Compra c : compras) {
            System.out.println("- Juego ID: " + c.getIdJuego() + ", Fecha: " + c.getFechaCompra());
        }

        // Calcular gasto total
        float total = gestorCompras.calcularGastoTotal(juan.getId());
        System.out.println("Gasto total de Juan: " + total + " €");
    }
}
