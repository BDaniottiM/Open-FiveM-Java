import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        setUIFont(new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 24));

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("xtv77fi.ico");
        frame.setIconImage(icon.getImage());

        String discordUrl = "discord://https://discord.com/channels/865690041966919689/1018324428381229076";
        String fivemUrl = "fivem://connect/kamikazegtarp.com.br?pure_1";
        if (isProcessRunning("Discord.exe")) {
            showMessageBox("Ponto", "Cê abriu o FiveM, não esqueçe de abrir o ponto!", "Não esquece de abrir a porra do ponto nesse caralho.");
            openWebPage(fivemUrl);
            openDiscordChannel(discordUrl);
        }

        while(isProcessRunning("FiveM.exe")) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }
        }

        showMessageBox("Fecha seu ponto", "Cê fechou o fivem, agora fecha o ponto!", "Por favor!");
        openDiscordChannel(discordUrl);
    }

    private static boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            boolean var4;
            try {
                List<String> lines = reader.lines().toList();
                var4 = lines.stream().anyMatch((line) -> {
                    return line.contains(processName);
                });
            } catch (Throwable var6) {
                try {
                    reader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            reader.close();
            return var4;
        } catch (IOException var7) {
            var7.printStackTrace();
            return false;
        }
    }

    private static void showMessageBox(String title, String message, String informativeText) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog((Component)null, message, title, JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException | IOException var2) {
            var2.printStackTrace();
        }

    }

    private static void openDiscordChannel(String discordUrl) {
        try {
            Desktop.getDesktop().browse(new URI(discordUrl));
        } catch (URISyntaxException | IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
