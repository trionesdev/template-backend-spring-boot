package ms.triones.backend.core.provider.middleware.cache.impl;

import lombok.RequiredArgsConstructor;
import ms.triones.backend.core.provider.middleware.cache.CacheProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisCacheProvider<K, V> implements CacheProvider<K, V> {
    private final RedisTemplate<K, V> redisTemplate;


    @Override
    public void setValue(K key, V value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public V getValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }
}
