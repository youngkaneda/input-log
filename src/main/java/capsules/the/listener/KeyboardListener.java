package capsules.the.listener;

import capsules.the.bus.EventBus;
import capsules.the.bus.event.KeyboardEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.LinkedList;
import java.util.List;

public class KeyboardListener implements NativeKeyListener {

    private final EventBus bus;
    private final List<Integer> pressedKeys;

    public KeyboardListener(EventBus bus) {
        this.bus = bus;
        this.pressedKeys = new LinkedList<>();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (!pressedKeys.contains(nativeKeyEvent.getKeyCode()) && pressedKeys.size() < 3) {
            pressedKeys.add(nativeKeyEvent.getKeyCode());
            bus.fireEvent(new KeyboardEvent(nativeKeyEvent.getKeyCode(), nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())));
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        pressedKeys.remove(Integer.valueOf(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        // not implemented;
    }
}
