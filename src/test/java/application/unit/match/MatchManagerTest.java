package application.unit.match;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.axreng.backend.application.match.IPatternMatchHandler;
import com.axreng.backend.application.match.MatchManager;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class MatchManagerTest {
    @Test
    public void handle(){
        var search = mock(ISearch.class);
        var query = mock(IQuery.class);
        var myPatternHandler = (new IPatternMatchHandler() {
            @Override
            public void process(String document, ISearch search, IQuery query) {
            }
        });

        var spyMyPatternHandler = spy(myPatternHandler);
        var matchHandler = new MatchManager(search, spyMyPatternHandler);
        matchHandler.handle("", query);

        verify(spyMyPatternHandler, times(1)).process("", search, query);
    }
}
