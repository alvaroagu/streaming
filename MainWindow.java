import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {
    private DefaultListModel<AppMovie> catalogModel = new DefaultListModel<>();
    private DefaultListModel<AppMovie> libraryModel = new DefaultListModel<>();
    private JList<AppMovie> catalogList;
    private JList<AppMovie> libraryList;

    public MainWindow() {
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Streaming - Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);

        catalogList = new JList<>(catalogModel);
        libraryList = new JList<>(libraryModel);

        JButton addBtn = new JButton("Agregar a mi biblioteca");
        addBtn.addActionListener(e -> addSelectedToLibrary());

        JButton removeBtn = new JButton("Quitar de mi biblioteca");
        removeBtn.addActionListener(e -> removeSelectedFromLibrary());

        JButton playBtn = new JButton("Reproducir");
        playBtn.addActionListener(e -> playSelected());

        JButton logoutBtn = new JButton("Cerrar sesión");
        logoutBtn.addActionListener(e -> doLogout());

        JPanel top = new JPanel(new BorderLayout());
        String username = AppSystem.currentUser != null ? AppSystem.currentUser.getNombre() + " (" + AppSystem.currentUser.getUsername() + ")" : "(Invitado)";
        top.add(new JLabel("Usuario: " + username), BorderLayout.WEST);
        top.add(logoutBtn, BorderLayout.EAST);

        JPanel center = new JPanel(new java.awt.GridLayout(1, 2, 12, 12));
        center.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        center.add(new JScrollPane(catalogList));
        center.add(new JScrollPane(libraryList));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actions.add(addBtn);
        actions.add(removeBtn);
        actions.add(playBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(actions, BorderLayout.SOUTH);
    }

    private void loadData() {
        catalogModel.clear();
        libraryModel.clear();
        for (AppMovie m : AppSystem.getCatalogo()) catalogModel.addElement(m);
        for (AppMovie m : AppSystem.getUserLibrary()) libraryModel.addElement(m);
    }

    private void addSelectedToLibrary() {
        AppMovie sel = catalogList.getSelectedValue();
        if (sel == null) { JOptionPane.showMessageDialog(this, "Seleccione una película del catálogo", "Atención", JOptionPane.WARNING_MESSAGE); return; }
        boolean ok = AppSystem.addMovieToCurrentUser(sel);
        if (ok) {
            libraryModel.addElement(sel);
            JOptionPane.showMessageDialog(this, "Película agregada a su biblioteca", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La película ya está en su biblioteca o no hay sesión iniciada", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeSelectedFromLibrary() {
        AppMovie sel = libraryList.getSelectedValue();
        if (sel == null) { JOptionPane.showMessageDialog(this, "Seleccione una película de su biblioteca", "Atención", JOptionPane.WARNING_MESSAGE); return; }
        if (AppSystem.currentUser != null && AppSystem.currentUser.removeMovie(sel)) {
            libraryModel.removeElement(sel);
            JOptionPane.showMessageDialog(this, "Película removida de su biblioteca", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No fue posible remover la película", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playSelected() {
        AppMovie sel = catalogList.getSelectedValue();
        if (sel == null) { JOptionPane.showMessageDialog(this, "Seleccione una película del catálogo", "Atención", JOptionPane.WARNING_MESSAGE); return; }
        String path = sel.getUrl();
        if (path == null || path.isEmpty()) { JOptionPane.showMessageDialog(this, "No hay URL configurada para esa película", "Atención", JOptionPane.WARNING_MESSAGE); return; }
        File f = new File(path);
        if (!f.exists()) {
            f = new File(System.getProperty("user.dir"), path);
        }
        if (!f.exists()) {
            JOptionPane.showMessageDialog(this, "Archivo de video no encontrado: " + path, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(f);
            } else {
                JOptionPane.showMessageDialog(this, "Desktop API no soportada en este entorno", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doLogout() {
        AppSystem.logout();
        new LoginWindow().setVisible(true);
        dispose();
    }
}
