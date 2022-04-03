package com.zowiac.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Convertor {

    public static String convertDateSort(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");
        return simpleDateFormat.format(date);
    }
}
