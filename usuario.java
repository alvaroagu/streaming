public class usuario {
    private final String Nombre;
    private final String Apellido;
    private final String Usuario;
    private final String Clave;

    public usuario(String nombre, String apellido, String usuario, String clave) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Usuario = usuario;
        this.Clave = clave;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getClave() {
        return Clave;
    }
}
