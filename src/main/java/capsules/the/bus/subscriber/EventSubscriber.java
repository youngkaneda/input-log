package capsules.the.bus.subscriber;

import capsules.the.bus.event.Event;

public abstract class EventSubscriber<T extends Event> {

    public abstract void handle(T event);
}
