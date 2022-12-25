package it.philipkrauss.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@SpringBootApplication
public class URLShortenerApiApplication {

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(URLShortenerApiApplication.class, args);
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(
				environment.getProperty("redis.host", "localhost"),
				environment.getProperty("redis.port", Integer.class, 6379));
		configuration.setUsername(environment.getProperty("redis.username", "default"));
		configuration.setPassword(RedisPassword.of(environment.getProperty("redis.password", "")));
		return new LettuceConnectionFactory(configuration);
	}

}
