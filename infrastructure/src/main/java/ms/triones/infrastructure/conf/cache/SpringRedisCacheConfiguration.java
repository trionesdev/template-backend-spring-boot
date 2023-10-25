package ms.triones.infrastructure.conf.cache;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableConfigurationProperties({SpringCacheExtProperties.class})
public class SpringRedisCacheConfiguration {
    private final CacheProperties cacheProperties;
    private final SpringCacheExtProperties cacheExtProperties;
    private final ObjectMapper objectMapper;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        ObjectMapper redisObjMapper = redisObjectMapper();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjMapper));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(redisObjMapper));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(
            RedisTemplate<String, Object> redisTemplate,
            StringRedisTemplate stringRedisTemplate
    ) {
        return (builder) -> {
            builder.cacheDefaults(defaultRedisCacheConfiguration());
            if (MapUtils.isNotEmpty(cacheExtProperties.getCacheNames())) {
                cacheExtProperties.getCacheNames().forEach((k, v) -> {
                    RedisCacheConfiguration cacheConfiguration;
                    switch (v.getValueSerializer()) {
                        case JSON:
                            cacheConfiguration = buildCacheConfiguration(redisTemplate, v);
                            break;
                        case STRING:
                        default:
                            cacheConfiguration = buildCacheConfiguration(stringRedisTemplate, v);
                            break;
                    }
                    builder.withCacheConfiguration(k, cacheConfiguration);
                });

            }
        };
    }

    private <T> RedisCacheConfiguration buildCacheConfiguration(RedisTemplate<String, T> redisTemplate, SpringCacheExtProperties.CacheItem item) {
        RedisCacheConfiguration redisCacheConfiguration = defaultRedisCacheConfiguration();
        redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getStringSerializer()));
        redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        if (Objects.nonNull(item.getTimeToLeave())) {
            redisCacheConfiguration.entryTtl(Duration.ofSeconds(item.getTimeToLeave()));
        }
        return redisCacheConfiguration;
    }

    private RedisCacheConfiguration defaultRedisCacheConfiguration() {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(redisObjectMapper())));
        if (StrUtil.isNotBlank(redisProperties.getKeyPrefix())) {
            redisCacheConfiguration = redisCacheConfiguration.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (Objects.nonNull(redisProperties.getTimeToLive())) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(redisProperties.getTimeToLive());
        }
        if (!redisProperties.isUseKeyPrefix()) {
            redisCacheConfiguration = redisCacheConfiguration.disableKeyPrefix();
        }
        if (!redisProperties.isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }
        return redisCacheConfiguration;
    }

    private ObjectMapper redisObjectMapper() {
        ObjectMapper objMapper = objectMapper.copy();
        objMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objMapper.activateDefaultTyping(objMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        return objMapper;
    }


}
