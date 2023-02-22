package com.zowiac.commons;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SqlDateDesrialize extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String data = jsonParser.getValueAsString();
            Date date = null;
            if (data.contains("-"))
                date  =  simpleDateFormat1.parse(jsonParser.getValueAsString());
            else
                date = simpleDateFormat.parse(jsonParser.getValueAsString());

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTimeZone(TimeZone.getTimeZone("CEST"));
            cal.setTime(date);
            cal.add(GregorianCalendar.HOUR_OF_DAY,2);
            return cal.getTime();
        } catch (ParseException e) {
            return null;
        }
    }
}

