package domain.unit.commons;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.axreng.backend.domain.commons.url.Url;

public class UrlTest {
    
    @Test
    public void containsPathOrUrl() throws MalformedURLException{
        var baseUrl = new Url("http://test.com");
        var baseUrlWithHttps = new Url("https://test.com");
        var otherUrlWithBaseUrlAndFile = new Url("http://test.com/outrocaminho/index.html");
        var otherUrlWithBaseUrlAndFileAndHttps = new Url("https://test.com/outrocaminho/index.html");
        var otherUrlWithBaseUrl = new Url("http://test.com/outrocaminhooooo/");
        var otherUrlWithBaseUrlAndHttps = new Url("https://test.com/outrocaminhooooo/");
        var otherUrl = new Url("http://tst.com");

        assertTrue(baseUrl.containsPathOfUrl(baseUrl));
        assertTrue(baseUrl.containsPathOfUrl(baseUrlWithHttps));
        assertTrue(baseUrlWithHttps.containsPathOfUrl(baseUrl));
        assertTrue(otherUrlWithBaseUrl.containsPathOfUrl(baseUrl));
        assertTrue(otherUrlWithBaseUrl.containsPathOfUrl(baseUrlWithHttps));
        assertTrue(otherUrlWithBaseUrlAndHttps.containsPathOfUrl(baseUrl));
        assertTrue(otherUrlWithBaseUrlAndHttps.containsPathOfUrl(baseUrlWithHttps));
        assertTrue(otherUrlWithBaseUrlAndFile.containsPathOfUrl(baseUrl));
        assertTrue(otherUrlWithBaseUrlAndFileAndHttps.containsPathOfUrl(baseUrlWithHttps));
        assertFalse(baseUrl.containsPathOfUrl(otherUrl));
        assertFalse(otherUrl.containsPathOfUrl(baseUrl));
    }
}
