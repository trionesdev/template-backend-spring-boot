package com.ms.core.conf.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;

public class InstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
        if (StringUtils.isNoneBlank(p.getText()) && p.getLongValue() > 0) {
            return Instant.ofEpochSecond(p.getLongValue());
        } else {
            return null;
        }
    }
}
