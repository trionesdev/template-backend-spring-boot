package com.ms.core.conf.jackson;

import com.ms.core.conf.jackson.deserializer.InstantDeserializer;
import com.ms.core.conf.jackson.serializer.InstantSerializer;
import com.ms.core.conf.jackson.serializer.LongSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.time.Instant;

@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(BigInteger.class, LongSerializer.instance);
            builder.serializerByType(Long.class, LongSerializer.instance);
            builder.serializerByType(Long.TYPE, LongSerializer.instance);
            builder.serializerByType(Instant.class, new InstantSerializer());
            builder.deserializerByType(Instant.class, new InstantDeserializer());
        };
    }

}
