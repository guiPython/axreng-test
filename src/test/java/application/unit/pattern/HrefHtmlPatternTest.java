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
        assertFalse(hrefHtmlPattern.match("http://www.test.com.br"));
        assertFalse(hrefHtmlPattern.match("https://test.com.br"));
        assertFalse(hrefHtmlPattern.match(" http://ssdwdd.com.br"));
        assertFalse(hrefHtmlPattern.match(" http://www.ssdwdd.com.br"));
        assertFalse(hrefHtmlPattern.match("https://hdbwuhdb.com.br "));
        assertFalse(hrefHtmlPattern.match("https://hdbwuhdb.com.br/test.html"));
        assertFalse(hrefHtmlPattern.match(" http://testtest.com.br "));
        assertFalse(hrefHtmlPattern.match("<a href=http://testtest.com.br>qbyuydsw<a>"));
        assertFalse(hrefHtmlPattern.match("<a href=\"http://testtest.com.br\">qbyuydsw<a>"));
        assertFalse(hrefHtmlPattern.match("<a href=http://testtest.com.br\\index.html>qbyuydsw<a>"));
        assertTrue(hrefHtmlPattern.match("<a href=\"http://testtest.com.br\\test.html\">qbyuydsw<a>"));
        assertFalse(hrefHtmlPattern.match("http://ssdwdd"));
        assertFalse(hrefHtmlPattern.match("http//www.ssdwdd.com"));
        assertFalse(hrefHtmlPattern.match("http:/www.ssdwdd"));
        assertFalse(hrefHtmlPattern.match("https::///www.ssdwdd"));
        assertFalse(hrefHtmlPattern.match("http://invisible/-island.net/xterm/xterm.log.html"));
    }
}
