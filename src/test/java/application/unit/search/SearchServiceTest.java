package application.unit.search;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.axreng.backend.application.match.KeywordPatternMatchHandler;
import com.axreng.backend.application.match.MatchManager;
import com.axreng.backend.application.search.SearchService;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class SearchServiceTest {

    @Test
    public void executeWhenSearchDontHaveQueries() {
        var search = mock(ISearch.class);
        var matchManager = spy(new MatchManager(search,
                new KeywordPatternMatchHandler(null, null)));
        var searchService = new SearchService(matchManager);

        when(search.queries()).thenReturn(new ConcurrentLinkedQueue<>());
        when(search.isCompleted()).thenReturn(true);
        searchService.execute(search);
    }

    @Test
    public void executeWhenSearchHaveQueries() {
        var search = mock(ISearch.class);
        var matchManager = spy(new MatchManager(search,
                new KeywordPatternMatchHandler(null, null)));
        var searchService = new SearchService(matchManager);

        var queries = new ConcurrentLinkedQueue<IQuery>();
        queries.add(mock(IQuery.class));
        when(search.queries()).thenReturn(queries);
        when(search.isCompleted()).thenReturn(true);
        searchService.execute(search);
    }
}
