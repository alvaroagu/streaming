import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LoginVentana extends JFrame {
    public LoginVentana() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Streaming");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Bienvenido al sistema de streaming", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(event -> dispose());

        JPanel contenedor = new JPanel(new BorderLayout(0, 16));
        contenedor.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        contenedor.add(titulo, BorderLayout.CENTER);
        contenedor.add(cerrar, BorderLayout.SOUTH);

        setContentPane(contenedor);
        setSize(420, 180);
        setLocationRelativeTo(null);
    }
}
