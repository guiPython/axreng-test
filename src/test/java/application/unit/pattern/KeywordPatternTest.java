package application.unit.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axreng.backend.application.pattern.KeywordPattern;

public class KeywordPatternTest {
    
    @Test
    public void pattern(){
        var keywordPattern = new KeywordPattern("test");
        assertEquals("test", keywordPattern.pattern().toString());
    }

    @Test
    public void match(){
        var keywordPattern = new KeywordPattern("test");
        assertTrue(keywordPattern.match("test"));
        assertTrue(keywordPattern.match("TEST"));
        assertTrue(keywordPattern.match(" test"));
        assertTrue(keywordPattern.match("test "));
        assertTrue(keywordPattern.match(" test "));
        assertTrue(keywordPattern.match("<a>test<a>"));
        assertTrue(keywordPattern.match("test_"));
        assertTrue(keywordPattern.match("_test"));
        assertTrue(keywordPattern.match("_test_"));
    }
}
