# ⚡ URL-Shortener-API
___
This repository contains a simple implementation of an url-shortener. It's developed in [Java](https://github.com/topics/java) with [Spring-Boot](https://spring.io/projects/spring-boot) and [Redis](https://redis.io/) as cache.
___
### What does this api?

Well, this api is a service to convert a long url into a short key, that can be used to access the original url.

#### Solution

The service will take any (long) url, receive it via http (`POST /shorten?url={url}`) and generate a key based on the hashed url.
That key is stored in a Redis-Cache for a configurable amount of seconds.  

After that, the server will respond with json data, containing the `key` and the `original_url` values.
For retrieving the original url, the server receives your request (`GET /u/{key}`) and responds with the `original_url`.  

If you want to be redirected use (`GET /r/{key}`) which should respond with `Location`-Header that should automatically redirect to the original url.
___
### Configuration

The server address and port are configurable, as well as the redis cache credentials and the cache-expiration-time. Here is an example for the `application.properties`:

```
# == Server configuration
server.address=localhost
server.port=8080
# == Redis configuration
redis.host=localhost
redis.port=6379
redis.username=default
redis.password=your-password
# set to 0 to disable expiration (time in seconds)
redis.cache.expiration=42600
```
___
### Endpoints

As written before, the endpoints of this api are:  
`POST /shorten?url={url}`, `GET /u/{key}` and `GET /r/{key}`
  
So an example would look like this:
##### 1. Shorten your url
- Request: `POST http://localhost:8080/shorten?url=https://github.com/PhilipKrauss?tab=repositories`  
- Response: `{"key": "08cef149","original_url": "https://github.com/PhilipKrauss?tab=repositories"}`
##### 2. Retrieve the original url
- Request: `GET http://localhost:8080/u/08cef149`
- Response: `{"original_url": "https://github.com/PhilipKrauss?tab=repositories"}`
##### 3. Redirect to the original url
- Request: `GET http://localhost:8080/r/08cef149`
- Response: `Header { Location: 'https://github.com/PhilipKrauss?tab=repositories' }`
___