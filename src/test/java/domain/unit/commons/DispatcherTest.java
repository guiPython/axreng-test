package domain.unit.commons;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.axreng.backend.domain.commons.events.EventDispatcher;
import com.axreng.backend.domain.commons.events.IEvent;
import com.axreng.backend.domain.commons.events.IEventHandler;

public class DispatcherTest {

    class AnyEvent implements IEvent{}
    
    @Test
    public void notifySubscribedHandlers(){
        IEventHandler<AnyEvent> handler = (new IEventHandler<AnyEvent>() {
            @Override
            public void handle(AnyEvent data) {}   
        });
        var spyHandler = spy(handler);

        var dispatcher = new EventDispatcher();
        dispatcher.subscribe(AnyEvent.class, spyHandler);
        var event = new AnyEvent();
        dispatcher.notify(event);

        verify(spyHandler, times(1)).handle(event);       
    }

    @Test 
    public void subscribeHandler(){
        IEventHandler<AnyEvent> handler = (new IEventHandler<AnyEvent>() {
            @Override
            public void handle(AnyEvent data) {}   
        });
        var spyHandler = spy(handler);

        var dispatcher = spy(new EventDispatcher());
        dispatcher.subscribe(AnyEvent.class, spyHandler);
        var event = new AnyEvent();
        dispatcher.notify(event);
        verify(spyHandler, times(1)).handle(event);
    }

    @Test
    public void unsubscribeHandler(){
        IEventHandler<AnyEvent> handler = (new IEventHandler<AnyEvent>() {
            @Override
            public void handle(AnyEvent data) {}   
        });
        var spyHandler = spy(handler);

        var dispatcher = new EventDispatcher();
        dispatcher.subscribe(AnyEvent.class, spyHandler);   
        dispatcher.unsubscribe(AnyEvent.class, spyHandler);
        var event = new AnyEvent();
        dispatcher.notify(event);

        verify(spyHandler, times(0)).handle(event);
    }
}
