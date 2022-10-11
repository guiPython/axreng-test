package domain.unit.search;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;
import com.axreng.backend.domain.search.events.KeywordFound;
import com.axreng.backend.domain.search.events.KeywordFoundHandler;

public class KeywordFoundHandlerTest {
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
    public void handle() throws MalformedURLException{
        var search = mock(ISearch.class);
        var query = mock(IQuery.class);
        when(query.url()).thenReturn(new Url("http://minhaurl.com"));

        var eventHandler = new KeywordFoundHandler(); 
        eventHandler.handle(new KeywordFound(search, query));
        assertEquals("Result found: " + query.url(), outputStream.toString().trim());
    }
}
