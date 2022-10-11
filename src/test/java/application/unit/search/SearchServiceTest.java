package application.unit.search;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.axreng.backend.application.match.KeywordPatternMatch;
import com.axreng.backend.application.match.MatchHandler;
import com.axreng.backend.application.search.SearchService;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class SearchServiceTest {

    @Test
    public void executeWhenSearchDontHaveQueries() {
        var search = mock(ISearch.class);
        var matchHandler = spy(new MatchHandler(search,
                new KeywordPatternMatch(null, null)));
        var searchService = new SearchService(matchHandler);

        when(search.queries()).thenReturn(new ArrayList<>());
        when(search.isCompleted()).thenReturn(true);
        searchService.execute(search);
    }

    @Test
    public void executeWhenSearchHaveQueries() {
        var search = mock(ISearch.class);
        var matchHandler = spy(new MatchHandler(search,
                new KeywordPatternMatch(null, null)));
        var searchService = new SearchService(matchHandler);

        var queries = new ArrayList<IQuery>();
        queries.add(mock(IQuery.class));
        when(search.queries()).thenReturn(queries);
        when(search.isCompleted()).thenReturn(true);
        searchService.execute(search);
    }
}
