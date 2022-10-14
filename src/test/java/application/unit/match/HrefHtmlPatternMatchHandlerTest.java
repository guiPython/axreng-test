package application.unit.match;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axreng.backend.application.match.HrefHtmlPatternMatchHandler;
import com.axreng.backend.application.pattern.HrefHtmlPattern;
import com.axreng.backend.domain.commons.events.EventDispatcher;
import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class HrefHtmlPatternMatchHandlerTest {
    @Test
    public void processWithMatchedDocument() throws MalformedURLException {
        var mockUrl = mock(Url.class);
        when(mockUrl.concat(anyString())).thenReturn(new Url("http://minhaurl.com"));

        var search = mock(ISearch.class);
        when(search.url()).thenReturn(mockUrl);
        
        var query  = mock(IQuery.class); 
        var dispatcher = spy(new EventDispatcher());

        var hrefHtmlPattern = mock(HrefHtmlPattern.class);
        var mockMatches = new ArrayList<String>();
        mockMatches.add("00");
        mockMatches.add("wdadaw");
        when(hrefHtmlPattern.matches(anyString())).thenReturn(mockMatches);

        var hrefHtmlPatternMatch = new HrefHtmlPatternMatchHandler(dispatcher, hrefHtmlPattern);
        hrefHtmlPatternMatch.process("", search, query);

        verify(hrefHtmlPattern, times(1)).matches("");
        verify(dispatcher, times(2)).notify(any());
    }

    @Test
    public void processWithNoMatchedDocument() {
        var search = mock(ISearch.class);
        var query  = mock(IQuery.class); 
        var dispatcher = spy(new EventDispatcher());

        var hrefHtmlPattern = mock(HrefHtmlPattern.class);
        when(hrefHtmlPattern.matches(anyString())).thenReturn(new ArrayList<>());

        var hrefHtmlPatternMatch = new HrefHtmlPatternMatchHandler(dispatcher, hrefHtmlPattern);
        hrefHtmlPatternMatch.process(anyString(), search, query);

        verify(hrefHtmlPattern, times(1)).matches(anyString());
        verify(dispatcher, times(0)).notify(any());
    }
}
