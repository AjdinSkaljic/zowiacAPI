package com.zowiac.commons;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Time;

public class SqlTimeDesrialize extends JsonDeserializer<Time> {

    @Override
    public Time deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Time.valueOf(jsonParser.getValueAsString() + ":00");
    }
}
