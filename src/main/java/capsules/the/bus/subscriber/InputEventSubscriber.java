package capsules.the.bus.subscriber;

import capsules.the.bus.event.KeyboardEvent;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;

public class InputEventSubscriber extends EventSubscriber<KeyboardEvent> {

    private final JFrame window;
//    private final Properties mappedButtons;
    private final JsonArray mappedButtons;

    public InputEventSubscriber(JFrame window) throws IOException {
        this.window = window;
//        this.mappedButtons = new Properties();
//        this.mappedButtons.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        JsonReader reader = Json.createReader(getClass().getClassLoader().getResourceAsStream("config.json"));
        this.mappedButtons = reader.readArray();
    }

    @Override
    public void handle(KeyboardEvent event) {
        // Verifying which configured input was pressed.
        mappedButtons.stream().map(obj -> obj.asJsonObject()).forEach(obj -> {
            try {
//                int nativeKeyCode = NativeKeyEvent.class.getField(String.valueOf(v)).getInt(null);
                if (obj.getInt("keyCode") == event.keyCode()) {
                    // Loading the input correspondent image.
                    InputStream is = getClass().getClassLoader().getResourceAsStream(obj.getString("sprite"));
                    JLabel move = new JLabel(new ImageIcon(is.readAllBytes()));
                    move.setPreferredSize(new Dimension(90, 30));
                    // Adding it to the window.
                    window.getContentPane().add(move, 0);
                    window.revalidate();
                }
            } catch (/*NoSuchFieldException | IllegalAccessException | */ IOException e) {
                e.printStackTrace();
            }
        });
    }

}
