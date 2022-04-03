package com.zowiac.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class SqlTimeSerialize extends JsonSerializer<Time> {

    @Override
    public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        jsonGenerator.writeString(formatter.format(time));

    }
}
