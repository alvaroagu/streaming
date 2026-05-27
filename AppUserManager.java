public class AppUserManager {
    private final java.util.List<AppUser> users = new java.util.ArrayList<>();

    public boolean register(AppUser u) {
        if (findByUsername(u.getUsername()) != null) return false;
        users.add(u);
        return true;
    }

    public AppUser findByUsername(String username) {
        for (AppUser u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    public AppUser authenticate(String username, String password) {
        AppUser u = findByUsername(username);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }

    public java.util.List<AppUser> getAllUsers() { return users; }
}
