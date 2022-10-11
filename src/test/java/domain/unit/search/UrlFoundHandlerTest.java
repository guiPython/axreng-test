package domain.unit.search;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.Search;
import com.axreng.backend.domain.search.events.UrlFound;
import com.axreng.backend.domain.search.events.UrlFoundHandler;

public class UrlFoundHandlerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void setup(){    
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void finish(){
        System.setOut(standardOut);
    }

    @Test
    public void handle() throws Exception{
        var url = mock(Url.class);
        var search = spy(new Search(url, "keyword", 1));
        when(search.url()).thenReturn(new Url("http://minhaurl2.com"));
        
        var query = mock(IQuery.class);
        when(query.url()).thenReturn(new Url("http://minhaurl2.com"));

        var urlFound = new UrlFound(search, query.url().toString());
        var event = new UrlFoundHandler();
        event.handle(urlFound);
        verify(search, times(1)).addQuery(any());
    }

}
