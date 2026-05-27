import javax.swing.JFrame;

public class Sistema extends JFrame {
    public static String Nombre;
    public static String Apellido;
    public static String Usuario;
    public static String Clave;
    public static String Respuesta;
    public static ListaUsuario lu = new ListaUsuario();

    public Sistema() {
        super("Sistema");
    }

    public static boolean registrarUsuario(String nombre, String apellido, String usuario, String clave) {
        Nombre = nombre;
        Apellido = apellido;
        Usuario = usuario;
        Clave = clave;

        return lu.agregarUsuario(new usuario(nombre, apellido, usuario, clave));
    }
}
