package com.interview.study;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class URLShortenerTest {
    @Test
    void createAndGetUrls(){
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        URLShortener urlShortener = new URLShortener(codeGenerator);
        urlShortener.createUrl("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/","744000076771876");
        urlShortener.createUrl("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/1","7440000767718761");
        urlShortener.createUrl("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/2","7440000767718762");
        urlShortener.createUrl("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/3","7440000767718763");
        urlShortener.createUrl("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/4","7440000767718764");
        assertEquals(urlShortener.getUrl("744000076771876"), "https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/");
        assertEquals(urlShortener.getUrl("7440000767718761"), "https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/1");
        assertEquals(urlShortener.getUrl("7440000767718762"), "https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/2");
        assertEquals(urlShortener.getUrl("7440000767718763"), "https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/3");

    }
    @Test
    void playwithURI() throws URISyntaxException, MalformedURLException {
        URI uri = new URI("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/");
        System.out.println(uri.toURL());
        URL url = new URL("https://careers.servicenow.com/jobs/744000076771876/senior-software-engineer/");
        System.out.println(url.toURI().toString());
    }


    @Test
    void shortenWithKeywordShouldStoreAndRetrieve() {

        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        UrlShortenerInterface shortener = new URLShortener(codeGenerator);
        String result = shortener.shortenWithKeyword("https://example.com", "promo", "user1");

        assertNotNull(result);
        assertEquals("https://example.com", shortener.getByKeyword("promo", "user1"));
    }

    @Test
    void shortenWithKeywordShouldThrowOnDuplicateForSamePasscode() {
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        UrlShortenerInterface shortener = new URLShortener(codeGenerator);
        shortener.shortenWithKeyword("https://example.com", "promo", "user1");
        assertThrows(IllegalArgumentException.class, () ->
                shortener.shortenWithKeyword("https://another.com", "promo", "user1"));
    }

    @Test
    void shortenWithKeywordCanReuseKeywordForDifferentPasscode() {
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        UrlShortenerInterface shortener = new URLShortener(codeGenerator);
        shortener.shortenWithKeyword("https://example.com", "promo", "user1");
        shortener.shortenWithKeyword("https://example.com", "promo", "user2"); // ok

        assertEquals("https://example.com", shortener.getByKeyword("promo", "user2"));
    }

    @Test
    void shortenRandomShouldGenerateCodeAndStore() {
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        UrlShortenerInterface shortener = new URLShortener(codeGenerator);
        String shortUrl = shortener.shortenRandom("https://example.com/page", "user1");

        assertNotNull(shortUrl);
        assertEquals("https://example.com/page", shortener.getByCode(shortUrl, "user1"));
    }

    @Test
    void shortenRandomFailsonceMaxAttemtsReached() {
        CodeGenerator codeGenerator = new FixedForTestCodeGenerator();
        UrlShortenerInterface shortener = new URLShortener(codeGenerator);
        shortener.shortenRandom("https://example.com/page", "user1");
        assertThrows(IllegalStateException.class, ()->{
            shortener.shortenRandom("https://example.com/page", "user1");
        });
    }
}
