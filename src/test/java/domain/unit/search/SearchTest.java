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
    void CreateSearchWithInvalidLimit() {
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
    void CannotCreateSearchWithInvalidKeyword() {
        var invalidKeywords = new String[] { "t", " ", "    ", "tes" };
        for (var invalidKeyword : invalidKeywords) {
            Exception e = assertThrows(Exception.class, () -> {
                new Search(this.mockrl, invalidKeyword, 0);
            }, "Invalid keyword in search shouldnt create a search");

            assertEquals("Cannot create a search with invalid keyword, keyword must be greater 4 and letter 32 (chars)",
                    e.getMessage());
        }
    }

    @Test
    void CreateValidSearch() {
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
    void GetUrlOfSearch() {

    }

    @Test
    void GetLimitOfSearch() {

    }

    @Test
    void GetResultsOfSearch() {

    }

    @Test
    void IsLimitedSearch() {

    }

    @Test
    void IsCompletedSearch() {

    }

    @Test
    void AddQueryToSearch() throws MalformedURLException {
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
