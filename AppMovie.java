public class AppMovie {
    private final String titulo;
    private final String genero;
    private final int anio;
    private final String descripcion;
    private final String url;

    public AppMovie(String titulo, String genero, int anio, String descripcion, String url) {
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.descripcion = descripcion;
        this.url = url == null ? "" : url;
    }

    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getAnio() { return anio; }
    public String getDescripcion() { return descripcion; }
    public String getUrl() { return url; }

    @Override
    public String toString() { return titulo + " (" + anio + ")"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppMovie)) return false;
        AppMovie other = (AppMovie) o;
        return anio == other.anio && titulo != null && titulo.equalsIgnoreCase(other.titulo);
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.toLowerCase().hashCode() : 0;
        result = 31 * result + anio;
        return result;
    }
}
