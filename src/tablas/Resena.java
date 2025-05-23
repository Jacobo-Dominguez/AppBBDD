package tablas;

public class Resena {
    private int id;
    private int idUsuario;
    private int idJuego;
    private int puntuacion;
    private String comentario;

    public Resena(int id, int idUsuario, int idJuego, int puntuacion, String comentario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idJuego = idJuego;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
