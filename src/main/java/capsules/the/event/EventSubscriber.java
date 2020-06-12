package capsules.the.event;

public abstract class EventSubscriber<T extends Event> {

    public abstract void handle(T event);
}
