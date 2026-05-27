public class AppUser {
    private final String nombre;
    private final String apellido;
    private final String username;
    private final String password;
    private final java.util.List<AppMovie> biblioteca = new java.util.ArrayList<>();

    public AppUser(String nombre, String apellido, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public java.util.List<AppMovie> getBiblioteca() { return biblioteca; }

    public boolean hasMovie(AppMovie m) { return biblioteca.contains(m); }

    public boolean addMovie(AppMovie m) {
        if (hasMovie(m)) return false;
        return biblioteca.add(m);
    }

    public boolean removeMovie(AppMovie m) { return biblioteca.remove(m); }

    @Override
    public String toString() { return nombre + " (" + username + ")"; }
}
