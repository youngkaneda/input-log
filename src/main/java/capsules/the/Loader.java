package capsules.the;

import capsules.the.event.EventBus;
import capsules.the.event.InputEventSubscriber;
import capsules.the.event.KeyboardEvent;
import capsules.the.listener.KeyboardListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Loader {

    public static void main(String[] args) throws NativeHookException, IOException {
        EventBus bus = new EventBus();
        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        // Setting up the window where the inputs will be displayed.
        JFrame window = new JFrame();
        window.setContentPane(new JPanel());
        window.setSize(new Dimension(90, 590));
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //
        bus.subscribe(KeyboardEvent.class, new InputEventSubscriber(window));
        //
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new KeyboardListener(bus));
    }
}
