package application.unit.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axreng.backend.application.pattern.HrefHtmlPattern;

public class HrefHtmlPatternTest {
    @Test
    public void pattern(){
        var hrefHtmlPattern = new HrefHtmlPattern();
        assertEquals("\\s*(?i)href\\s*=\\s*(\\\"([^\\\"]*\\.html\\\")|'[^']*'|([^'\\\">\\\\s\\.html]+))", hrefHtmlPattern.pattern().toString());
    }

    @Test
    public void match(){
        var hrefHtmlPattern = new HrefHtmlPattern();
        assertTrue(hrefHtmlPattern.match("http://www.test.com.br"));
        assertTrue(hrefHtmlPattern.match("https://test.com.br"));
        assertTrue(hrefHtmlPattern.match(" http://ssdwdd.com.br"));
        assertTrue(hrefHtmlPattern.match(" http://www.ssdwdd.com.br"));
        assertTrue(hrefHtmlPattern.match("https://hdbwuhdb.com.br "));
        assertTrue(hrefHtmlPattern.match("https://hdbwuhdb.com.br/test.html"));
        assertTrue(hrefHtmlPattern.match(" http://testtest.com.br "));
        assertTrue(hrefHtmlPattern.match("<a href=http://testtest.com.br>qbyuydsw<a>"));
        assertTrue(hrefHtmlPattern.match("<a href=\"http://testtest.com.br\">qbyuydsw<a>"));
        assertFalse(hrefHtmlPattern.match("http://ssdwdd"));
        assertFalse(hrefHtmlPattern.match("http//www.ssdwdd.com"));
        assertFalse(hrefHtmlPattern.match("http:/www.ssdwdd"));
        assertFalse(hrefHtmlPattern.match("https::///www.ssdwdd"));
        assertFalse(hrefHtmlPattern.match("http://invisible/-island.net/xterm/xterm.log.html"));
    }
}
