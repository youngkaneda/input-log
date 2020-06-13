package capsules.the.event;

import org.jnativehook.keyboard.NativeKeyEvent;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InputEventSubscriber extends EventSubscriber<KeyboardEvent> {

    private final JFrame window;
    private final Properties mappedButtons;

    public InputEventSubscriber(JFrame window) throws IOException {
        this.window = window;
        this.mappedButtons = new Properties();
        this.mappedButtons.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
    }

    @Override
    public void handle(KeyboardEvent event) {
        // Verifying which configured input was pressed.
        mappedButtons.forEach((k, v) -> {
            try {
                int nativeKeyCode = NativeKeyEvent.class.getField(String.valueOf(v)).getInt(null);
                if (nativeKeyCode == event.keyCode()) {
                    // Loading the input correspondent image.
                    InputStream is = getClass().getClassLoader().getResourceAsStream("sprites/" + k + ".png");
                    JLabel move = new JLabel(new ImageIcon(is.readAllBytes()));
                    move.setPreferredSize(new Dimension(70, 30));
                    // Adding it to the window.
                    window.getContentPane().add(move, 0);
                    window.revalidate();
                }
            } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        });
    }

}
