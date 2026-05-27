public class AppSystem {
    public static final AppUserManager userManager = new AppUserManager();
    public static final java.util.List<AppMovie> catalogo = new java.util.ArrayList<>();
    public static AppUser currentUser = null;

    static {
        loadMoviesFromJson();
        preloadCatalog();
    }

    public static void preloadCatalog() {
        addIfNotPresent(new AppMovie("El viaje", "Aventura", 2019, "Una aventura emocionante.", "videos/sample1.mp4"));
        addIfNotPresent(new AppMovie("La esperanza", "Drama", 2021, "Historia inspiradora.", "videos/sample2.mp4"));
        addIfNotPresent(new AppMovie("Risa sin fin", "Comedia", 2018, "Comedia ligera para todos.", "videos/sample3.mp4"));
        addIfNotPresent(new AppMovie("Sci-Fi: Futuro", "Ciencia ficción", 2023, "Ciencia ficción y acción.", "videos/sample4.mp4"));
    }

    private static void addIfNotPresent(AppMovie m) {
        if (!catalogo.contains(m)) catalogo.add(m);
    }

    public static java.util.List<AppMovie> getCatalogo() { return catalogo; }

    public static boolean registerUser(String nombre, String apellido, String username, String password) {
        return userManager.register(new AppUser(nombre, apellido, username, password));
    }

    public static boolean login(String username, String password) {
        AppUser u = userManager.authenticate(username, password);
        if (u != null) { currentUser = u; return true; }
        return false;
    }

    public static void logout() { currentUser = null; }

    public static boolean addMovieToCurrentUser(AppMovie m) {
        if (currentUser == null) return false;
        return currentUser.addMovie(m);
    }

    public static java.util.List<AppMovie> getUserLibrary() {
        if (currentUser == null) return new java.util.ArrayList<>();
        return currentUser.getBiblioteca();
    }

    public static void loadMoviesFromJson() {
        java.io.File file = new java.io.File("movies.json");
        if (!file.exists()) return;
        try {
            String text = new String(java.nio.file.Files.readAllBytes(file.toPath()), java.nio.charset.StandardCharsets.UTF_8);
            java.util.regex.Pattern objPattern = java.util.regex.Pattern.compile("\\{([^}]*)\\}");
            java.util.regex.Matcher m = objPattern.matcher(text);
            while (m.find()) {
                String obj = m.group(1);
                String titulo = extractString(obj, "titulo");
                String genero = extractString(obj, "genero");
                int anio = extractInt(obj, "anio", 0);
                String descripcion = extractString(obj, "descripcion");
                String url = extractString(obj, "url");
                if (titulo != null) addIfNotPresent(new AppMovie(titulo, genero, anio, descripcion, url));
            }
        } catch (Exception e) {
            System.err.println("Error loading movies.json: " + e.getMessage());
        }
    }

    private static String extractString(String obj, String key) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"", java.util.regex.Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher mm = p.matcher(obj);
        return mm.find() ? mm.group(1) : null;
    }

    private static int extractInt(String obj, String key, int defaultVal) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\"" + key + "\"\\s*:\\s*(\\d+)", java.util.regex.Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher mm = p.matcher(obj);
        return mm.find() ? Integer.parseInt(mm.group(1)) : defaultVal;
    }
}
