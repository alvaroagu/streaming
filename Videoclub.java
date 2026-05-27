import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Videoclub {
    public Videoclub() {
    }

    public static void main(String[] args) {
        configurarLookAndFeel();
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }

    private static void configurarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo lookAndFeel : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(lookAndFeel.getName())) {
                    UIManager.setLookAndFeel(lookAndFeel.getClassName());
                    return;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException exception) {
            System.err.println("No fue posible aplicar Nimbus: " + exception.getMessage());
        }
    }
}
