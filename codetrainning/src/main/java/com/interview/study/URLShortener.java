package com.interview.study;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class URLShortener implements UrlShortenerInterface{
//    Implement a thread-safe URL Shortener in Java.
//    Support two creation modes:
//   * create a short URL from a provided keyword;
//   * generate a random 4-character alphanumeric short code.
//    Inputs: long URL and a passcode (used when building the short URL).
//    Provide retrieval of a short URL by keyword.
//    Must include concurrency considerations.
    Map<String,String> urls = new ConcurrentHashMap<>();
        // храним: passcode:keyword -> longUrl
    private final ConcurrentHashMap<String, String> keywordToUrl = new ConcurrentHashMap<>();
    // храним: passcode:code -> longUrl
    private final ConcurrentHashMap<String, String> codeToUrl = new ConcurrentHashMap<>();

    private final CodeGenerator codeGenerator;

    public URLShortener(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }


    public String getUrl(String keyword) {
        return urls.get(keyword);
    }

    public void createUrl(String longUrl, String keyword) {
        urls.put(keyword, longUrl);
    }

    @Override
    public String shortenWithKeyword(String longUrl, String keyword, String passcode) {
        String mapKey = passcode +":"+keyword;
        if (keywordToUrl.putIfAbsent(mapKey,longUrl)!=null)
        {
            throw  new IllegalArgumentException("Url with "+mapKey+" already exists");
        }
        return mapKey;
    }

    @Override
    public String shortenRandom(String longUrl, String passcode) {
        String keyword;
        String mapKey;
        final int MAX_ATTEMPTS=3;
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
         keyword = codeGenerator.generateCode();
         mapKey = passcode+":"+keyword;
         if (codeToUrl.putIfAbsent(mapKey, longUrl)==null){
             return keyword;
         }
        }
        throw new IllegalStateException("Failed to generate unique code after "+MAX_ATTEMPTS);
    }

    @Override
    public String getByKeyword(String keyword, String passcode) {
        return keywordToUrl.get(passcode+":"+keyword);
    }

    @Override
    public String getByCode(String code, String passcode) {
        return codeToUrl.get(passcode+":"+code);
    }

}
