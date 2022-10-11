package com.axreng.backend.domain.commons.events;

public interface IEventHandler<T extends IEvent> {
    void handle(T data);
}
