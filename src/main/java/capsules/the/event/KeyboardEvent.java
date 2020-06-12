package capsules.the.event;

public class KeyboardEvent implements Event {

    private final int keyCode;
    private final String keyText;

    public KeyboardEvent(int keyCode, String keyText) {
        this.keyCode = keyCode;
        this.keyText = keyText;
    }

    public int keyCode() {
        return keyCode;
    }

    public String keyText() {
        return keyText;
    }
}
