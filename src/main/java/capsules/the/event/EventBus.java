package capsules.the.event;

import java.util.*;

public class EventBus {

    private final Map<String, List<EventSubscriber>> subs;

    public EventBus() {
        this.subs = new HashMap<>();
    }

    public void fireEvent(Event event) {
        subs.get(event.getClass().getName()).stream().forEach(sub -> sub.handle(event));
    }

    public void subscribe(Class<? extends Event> clazz, EventSubscriber subscriber) {
        this.subs.compute(clazz.getName(), (k, v) -> {
            if (v == null) {
                return Arrays.asList(subscriber);
            }
            v.add(subscriber);
            return v;
        });
    }
}
