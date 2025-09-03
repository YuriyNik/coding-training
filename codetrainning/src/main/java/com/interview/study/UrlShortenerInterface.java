package com.interview.study;

public interface UrlShortenerInterface {

    // Создать сокращение по заданному ключевому слову
    String shortenWithKeyword(String longUrl, String keyword, String passcode);

    // Создать сокращение со случайным кодом
    String shortenRandom(String longUrl, String passcode);

    // Получить оригинальный URL по ключевому слову
    String getByKeyword(String keyword, String passcode);

    // (опционально) Получить оригинальный URL по сгенерированному shortCode
    String getByCode(String code, String passcode);

}
