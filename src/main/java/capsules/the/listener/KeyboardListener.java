package capsules.the.listener;

import capsules.the.bus.EventBus;
import capsules.the.bus.event.KeyboardEvent;
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
