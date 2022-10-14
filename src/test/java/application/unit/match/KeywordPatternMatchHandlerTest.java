package application.unit.match;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axreng.backend.application.match.KeywordPatternMatchHandler;
import com.axreng.backend.application.pattern.KeywordPattern;
import com.axreng.backend.domain.commons.events.EventDispatcher;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class KeywordPatternMatchHandlerTest {
    @Test
    public void processWithMatchedDocument() {
        var search = mock(ISearch.class);

        var query = mock(IQuery.class);
        var dispatcher = spy(new EventDispatcher());

        var keywordPattern = mock(KeywordPattern.class);
        when(keywordPattern.match(anyString())).thenReturn(true);

        var keywordPatternMatchHandler = new KeywordPatternMatchHandler(dispatcher, keywordPattern);
        keywordPatternMatchHandler.process(anyString(), search, query);

        verify(keywordPattern, times(1)).match(anyString());
        verify(dispatcher, times(1)).notify(any());
    }

    @Test
    public void processWithNoMatchedDocument() {
        var search = mock(ISearch.class);
        var query = mock(IQuery.class);
        var dispatcher = spy(new EventDispatcher());

        var keywordPattern = mock(KeywordPattern.class);
        when(keywordPattern.match(anyString())).thenReturn(false);

        var keywordPatternMatchHandler = new KeywordPatternMatchHandler(dispatcher, keywordPattern);
        keywordPatternMatchHandler.process(anyString(), search, query);

        verify(keywordPattern, times(1)).match(anyString());
        verify(dispatcher, times(0)).notify(any());
    }
}
