package capsules.the.listener;

import capsules.the.event.EventBus;
import capsules.the.event.KeyboardEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyboardListener implements NativeKeyListener {

    private final EventBus bus;

    public KeyboardListener(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        bus.fireEvent(new KeyboardEvent(nativeKeyEvent.getKeyCode(), nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())));
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        // not implemented;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        // not implemented;
    }
}
