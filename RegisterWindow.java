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

public class RegisterWindow extends JFrame {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField usuarioField;
    private JPasswordField claveField;

    public RegisterWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registro - Streaming");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(5, 2, 8, 8));
        form.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));

        form.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        form.add(nombreField);

        form.add(new JLabel("Apellido:"));
        apellidoField = new JTextField();
        form.add(apellidoField);

        form.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        form.add(usuarioField);

        form.add(new JLabel("Clave:"));
        claveField = new JPasswordField();
        form.add(claveField);

        JButton registerBtn = new JButton("Crear cuenta");
        registerBtn.addActionListener(e -> doRegister());

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(registerBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(actions, BorderLayout.SOUTH);
    }

    private void doRegister() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String usuario = usuarioField.getText().trim();
        String clave = new String(claveField.getPassword());

        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = AppSystem.registerUser(nombre, apellido, usuario, clave);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Cuenta creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
