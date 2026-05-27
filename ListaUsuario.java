public class ListaUsuario {
    private static final int CAPACIDAD_MAXIMA = 10;

    private int nUsuario;
    private final usuario[] usuarios;

    public ListaUsuario() {
        this.nUsuario = 0;
        this.usuarios = new usuario[CAPACIDAD_MAXIMA];
    }

    public boolean agregarUsuario(usuario nuevoUsuario) {
        if (nUsuario >= CAPACIDAD_MAXIMA) {
            System.out.println("Capacidad maxima alcanzada.");
            return false;
        }

        usuarios[nUsuario] = nuevoUsuario;
        nUsuario++;
        return true;
    }

    public usuario obtenerUsuario(int indice) {
        if (indice < 0 || indice >= nUsuario) {
            return null;
        }

        return usuarios[indice];
    }

    public int cantidadUsuarios() {
        return nUsuario;
    }
}
