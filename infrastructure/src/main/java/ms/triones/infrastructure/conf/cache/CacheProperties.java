package ms.triones.infrastructure.conf.cache;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.cache")
public class CacheProperties {
    private Long defaultTtl = 3600L;
    private Map<String, CacheItem> cacheNames;

    @Data
    @RequiredArgsConstructor
    public static class CacheItem {
        private Long ttl;
        private ValueSerializerEnum valueSerializer;
    }

    public enum ValueSerializerEnum {
        STRING,
        JSON
    }
}