package domain.unit.search;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.Query;
import com.axreng.backend.domain.search.entity.ISearch;
import com.axreng.backend.domain.search.entity.Search;

public class SearchTest {

    private final Url mockrl = mock(Url.class);
    private final String keyword = "four";

    @Test
    public void createSearchWithInvalidLimit() {
        var invalidLimits = new int[] { 0, -1, -10 };
        for (var invalidLimit : invalidLimits) {
            assertDoesNotThrow(() -> {
                ISearch search = new Search(this.mockrl, keyword, invalidLimit);
                assertEquals(-1, search.limit(), "Search limit must be equals -1");
                assertFalse(search.isLimited(), "Search is not limited because invalid limit was received");
            }, "Invalid limit in search constructor must be create a ilimited search");
        }
    }

    @Test
    public void cannotCreateSearchWithInvalidKeyword() {
        var invalidKeywords = new String[] { "t", " ", "    ", "tes" };
        for (var invalidKeyword : invalidKeywords) {
            Exception e = assertThrows(Exception.class, () -> {
                var search = new Search(this.mockrl, invalidKeyword, 0);
                search.keyword();
            }, "Invalid keyword in search shouldnt create a search");

            assertEquals("Cannot create a search with invalid keyword, keyword must be greater 4 and letter 32 (chars)",
                    e.getMessage());
        }
    }

    @Test
    public void createValidSearch() {
        assertDoesNotThrow(() -> {
            ISearch search = new Search(this.mockrl, keyword, 0);
            assertEquals(-1, search.limit(), "Search limit must be equals -1");
            assertFalse(search.isLimited(), "Search is not limited because invalid limit was received");
        }, "Invalid limit in search constructor must be create a ilimited search");

        assertDoesNotThrow(() -> {
            ISearch search = new Search(this.mockrl, keyword, -10);
            assertEquals(-1, search.limit(), "Search limit must be equals -1");
            assertFalse(search.isLimited(), "Search is not limited because invalid limit was received");
        }, "Invalid limit in search constructor must be create a ilimited search");

        assertDoesNotThrow(() -> {
            ISearch search = new Search(this.mockrl, keyword, 4);
            assertEquals(4, search.limit(), "Search limit must be equals 4");
            assertTrue(search.isLimited(), "Search is limited because limit was received");
        }, "Search constructor must be create a limited search");

    }

    @Test
    public void getUrlOfSearch() throws Exception {
        final Url url = new Url("http://minhaurl.com");
        ISearch search = new Search(url, keyword, -1);
        assertEquals(url, search.url());
    }

    @Test
    public void getLimitOfSearchForLimitedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, 1);
        assertEquals(1, search.limit());
    }

    @Test
    public void getLimitOfSearchForIlimitedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, -1);
        assertEquals(-1, search.limit());
    }

    @Test
    public void getResultsOfSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, 4);
        assertEquals(0, search.results());
        search.incrementResult();
        assertEquals(1, search.results());
    }

    @Test
    public void isLimitedSearchForIlimitedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, -1);
        assertFalse(search.isLimited());
    }

    @Test
    public void isLimitedSearchForLimitedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, 1);
        assertTrue(search.isLimited());
    }

    @Test
    public void isCompletedSearchForIncompletedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, -1);
        assertFalse(search.isCompleted());
        
        search = new Search(this.mockrl, keyword, 1);
        assertFalse(search.isCompleted());
    }

    @Test
    public void isCompletedSearchForCompletedSearch() throws Exception {
        ISearch search = new Search(this.mockrl, keyword, 1);
        search.incrementResult();
        assertFalse(search.isCompleted());
        search.incrementResult();
        assertTrue(search.isCompleted());
    }

    @Test
    public void addQueryToSearch() throws MalformedURLException {
        Url url = new Url("https://minhaurl.com.br");
        Url urlWithHttpProtocol = new Url("http://minhaurl.com.br");
        Url otherUrl = new Url("http://minhaoutraurl.com.br");
        var query = new Query(url);
        var sameQuery = new Query(url);
        var sameQueryWithHttpProtocol = new Query(urlWithHttpProtocol);
        var otherQuery = new Query(otherUrl);

        try {
            var search = new Search(url, keyword, -1);
            assertEquals(1, search.queries().size(), "Search must be has a query on created");
            assertEquals(search.queries().iterator().next().url(), url);

            search.addQuery(query);
            assertEquals(1, search.queries().size());

            search.addQuery(query);
            assertEquals(1, search.queries().size());

            search.addQuery(sameQuery);
            assertEquals(1, search.queries().size());

            search.addQuery(sameQueryWithHttpProtocol);
            assertEquals(1, search.queries().size());

            search.addQuery(otherQuery);
            assertEquals(1, search.queries().size());
        } catch (Exception e) {
            fail("Should add queries in search");
        }

    }
}
