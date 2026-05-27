import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Streaming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 200);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

        form.add(new JLabel("Usuario:"));
        usernameField = new JTextField();
        form.add(usernameField);

        form.add(new JLabel("Clave:"));
        passwordField = new JPasswordField();
        form.add(passwordField);

        JButton loginBtn = new JButton("Iniciar sesión");
        loginBtn.addActionListener(e -> doLogin());
        JButton registerBtn = new JButton("Registrarse");
        registerBtn.addActionListener(e -> new RegisterWindow().setVisible(true));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(registerBtn);
        actions.add(loginBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(actions, BorderLayout.SOUTH);
    }

    private void doLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword());
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete usuario y clave", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (AppSystem.login(user, pass)) {
            new MainWindow().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
