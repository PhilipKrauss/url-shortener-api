package it.philipkrauss.urlshortener.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.philipkrauss.urlshortener.entry.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTemplate<String, ShortUrl> redisTemplate() {
        RedisTemplate<String, ShortUrl> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<ShortUrl> valueSerializer = new Jackson2JsonRedisSerializer<>(ShortUrl.class);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }

}
