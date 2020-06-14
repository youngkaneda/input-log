package capsules.the;

import capsules.the.bus.EventBus;
import capsules.the.bus.subscriber.InputEventSubscriber;
import capsules.the.bus.event.KeyboardEvent;
import capsules.the.listener.KeyboardListener;
import capsules.the.listener.MouseDragAdapter;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
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
        JFrame window = new JFrame("Input log");
		window.setUndecorated(true);
        window.setContentPane(new JPanel());
        window.setSize(new Dimension(90, 600));
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MouseDragAdapter adapter = new MouseDragAdapter(window);
        window.addMouseListener(adapter);
        window.addMouseMotionListener(adapter);
		//
        bus.subscribe(KeyboardEvent.class, new InputEventSubscriber(window));
        //
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new KeyboardListener(bus));
    }
}
