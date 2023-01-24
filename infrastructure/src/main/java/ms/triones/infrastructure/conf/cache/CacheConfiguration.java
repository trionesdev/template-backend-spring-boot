package ms.triones.infrastructure.conf.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableConfigurationProperties({CacheProperties.class})
public class CacheConfiguration {
    private final CacheProperties cacheProperties;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager.RedisCacheManagerBuilder cacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(Objects.requireNonNull(redisTemplate.getConnectionFactory()))
                .cacheDefaults(buildCacheConfiguration(redisTemplate, cacheProperties.getDefaultTtl()))
                .transactionAware();
        if (MapUtils.isNotEmpty(cacheProperties.getCacheNames())) {
            cacheProperties.getCacheNames().forEach((k, v) -> {
                RedisCacheConfiguration cacheConfiguration;
                switch (v.getValueSerializer()) {
                    case JSON:
                        cacheConfiguration = buildCacheConfiguration(redisTemplate, v.getTtl());
                        break;
                    case STRING:
                    default:
                        cacheConfiguration = buildCacheConfiguration(stringRedisTemplate, v.getTtl());
                        break;
                }
                cacheManagerBuilder.withCacheConfiguration(k, cacheConfiguration);
            });
        }
        return cacheManagerBuilder.build();
    }

    private <T> RedisCacheConfiguration buildCacheConfiguration(RedisTemplate<String, T> redisTemplate, Long seconds) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(seconds));
    }

}
