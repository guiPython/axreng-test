package com.axreng.backend.domain.commons.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class EventDispatcher implements IEventDispatcher {
    private Map<String, List<IEventHandler<IEvent>>> handlers;

    public EventDispatcher() {
        this.handlers = new HashMap<>();
    }

    @Override
    public void notify(IEvent event) {
        final String eventName = event.getClass().getName();
        final List<IEventHandler<IEvent>> handlers = this.handlers.get(eventName);
        if (handlers != null) {
            handlers.forEach(h -> h.handle(event));
        }
    }

    @Override
    public <T extends IEvent> void subscribe(Class<T> event, IEventHandler<T> handler) {
        final String eventName = event.getName();
        List<IEventHandler<IEvent>> handlers = this.handlers.get(eventName);
        if (handlers == null) {
            handlers = new ArrayList<IEventHandler<IEvent>>();
            handlers.add((IEventHandler<IEvent>) handler);
            this.handlers.put(eventName, handlers);
            return;
        }
        handlers.add((IEventHandler<IEvent>) handler);
    }

    @Override
    public <T extends IEvent> void unsubscribe(Class<T> event, IEventHandler<T> handler) {
        final String eventName = event.getClass().getName();
        List<IEventHandler<IEvent>> handlers = this.handlers.get(eventName);
        if(handlers != null){
            handlers.remove(handler);
        }
    }

}
