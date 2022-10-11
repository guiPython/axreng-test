package domain.unit.commons;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.*;

import com.axreng.backend.domain.commons.events.EventDispatcher;
import com.axreng.backend.domain.commons.events.IEvent;
import com.axreng.backend.domain.commons.events.IEventHandler;

public class DispatcherTest {

    class AnyEvent implements IEvent{}
    
    @Test
    void Notify(){
        var handler = spy(new IEventHandler<AnyEvent>() {
            @Override
            public void handle(AnyEvent data) {}   
        });

        var dispatcher = new EventDispatcher();
        dispatcher.subscribe(AnyEvent.class, handler);
        var event = new AnyEvent();
        dispatcher.notify(event);

        verify(handler, times(1)).handle(event);       
    }

    @Test 
    void Subscribe(){

    }

    @Test
    void Unsubscribe(){

    }
}
