package domain.unit.query;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axreng.backend.domain.commons.url.Url;
import com.axreng.backend.domain.query.IQuery;
import com.axreng.backend.domain.query.Query;

public class QueryTest {

    @Test
    public void createQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery query = new Query(url);
        assertEquals(false, query.isCompleted());
        assertEquals(url, query.url());
    }

    @Test
    public void getUrl() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery query = new Query(url);
        assertEquals(url, query.url());
    }

    @Test
    public void isCompletedForIncompletedQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery query = new Query(url);
        assertFalse(query.isCompleted());
    }

    @Test
    public void isCompletedForCompletedQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery query = new Query(url);
        query.complete();
        assertTrue(query.isCompleted());
    }


    @Test
    public void completeQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery query = new Query(url);
        assertFalse(query.isCompleted());
        query.complete();
        assertTrue(query.isCompleted());
    }

    @Test
    public void equalsForTheSameQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        IQuery completedQuery = new Query(url);
        completedQuery.complete();
        IQuery incompletedQuery = new Query(url);
        assertTrue(completedQuery.equals(incompletedQuery));
        assertTrue(completedQuery.equals(completedQuery));
    }

    @Test
    public void equalsForTheOtherQuery() throws MalformedURLException {
        var url = new Url("https://minhaurl.com");
        var otherUrl = new Url("https://minhaoutraurl.com");
        IQuery query = new Query(url);
        IQuery otherQuery = new Query(otherUrl);
        assertFalse(query.equals(otherQuery));
    }
}
