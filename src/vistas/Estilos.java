package vistas;

import javax.swing.*;
import java.awt.*;

public class Estilos { // Clase de estilos que pedi a ChatGPT para que se pareciera a los colores de Steam
    public static void aplicarEstiloVentana(JFrame ventana) {
        ventana.getContentPane().setBackground(new Color(27, 27, 27));
        ventana.setLayout(null);
    }

    public static void estilizarBoton(JButton boton) {
        boton.setBackground(new Color(0, 132, 176));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    public static void estilizarCampoTexto(JTextField campo) {
        campo.setBackground(new Color(44, 44, 44));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));
    }

    public static void estilizarEtiqueta(JLabel etiqueta) {
        etiqueta.setForeground(Color.LIGHT_GRAY);
        etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }
}
