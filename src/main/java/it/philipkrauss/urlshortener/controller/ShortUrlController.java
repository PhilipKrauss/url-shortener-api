package it.philipkrauss.urlshortener.controller;

import it.philipkrauss.urlshortener.entry.ShortUrl;
import it.philipkrauss.urlshortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/u/{key}")
    public ResponseEntity<String> getUrl(@PathVariable String key) {
        String originalUrl = shortUrlService.getOriginalUrlByKey(key);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping("/r/{key}")
    public ResponseEntity<String> redirectToUrl(@PathVariable String key) {
        String originalUrl = shortUrlService.getOriginalUrlByKey(key);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).headers(headers).body(originalUrl);
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortUrl> shortenUrl(@RequestParam String url) {
        ShortUrl shortUrl = shortUrlService.shortenUrl(url);
        return ResponseEntity.ok(shortUrl);
    }

}
