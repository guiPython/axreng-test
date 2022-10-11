package application.unit.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axreng.backend.application.pattern.UrlPattern;

public class UrlPatternTest {
    @Test
    public void pattern(){
        var urlPattern = new UrlPattern();
        assertEquals("\\b((http|https)://)(www.)?"
        + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
        + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)", urlPattern.pattern().toString());
    }

    @Test
    public void match(){
        var urlPattern = new UrlPattern();
        assertTrue(urlPattern.match("http://www.test.com.br"));
        assertTrue(urlPattern.match("https://test.com.br"));
        assertTrue(urlPattern.match(" http://ssdwdd.com.br"));
        assertTrue(urlPattern.match(" http://www.ssdwdd.com.br"));
        assertTrue(urlPattern.match("https://hdbwuhdb.com.br "));
        assertTrue(urlPattern.match("https://hdbwuhdb.com.br/test.html"));
        assertTrue(urlPattern.match(" http://testtest.com.br "));
        assertTrue(urlPattern.match("<a href=http://testtest.com.br>qbyuydsw<a>"));
        assertTrue(urlPattern.match("<a href=\"http://testtest.com.br\">qbyuydsw<a>"));
        assertFalse(urlPattern.match("http://ssdwdd"));
        assertFalse(urlPattern.match("http//www.ssdwdd.com"));
        assertFalse(urlPattern.match("http:/www.ssdwdd"));
        assertFalse(urlPattern.match("https::///www.ssdwdd"));
        assertFalse(urlPattern.match("http://invisible/-island.net/xterm/xterm.log.html"));
    }
}
