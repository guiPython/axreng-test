package application.unit.match;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.axreng.backend.application.match.IPatternMatch;
import com.axreng.backend.application.match.MatchHandler;
import com.axreng.backend.application.pattern.IPattern;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.search.entity.ISearch;

public class MatchHandlerTest {
    @Test
    public void handle(){
        var search = mock(ISearch.class);
        var query = mock(IQuery.class);
        var myPatternHandler = new IPatternMatch() {

            @Override
            public IPattern pattern() {
                return null;
            }

            @Override
            public String regex() {
                return "";
            }

            @Override
            public void process(String document, ISearch search, IQuery query) {
            }
            
        };

        var spyMyPatternHandler = spy(myPatternHandler);
        var matchHandler = new MatchHandler(search, spyMyPatternHandler);
        matchHandler.handle("", query);

        verify(spyMyPatternHandler, times(1)).process("", search, query);
    }
}
