package com.chatapp.util;

import com.mysql.cj.log.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static String convertToTimestamp(Date date) {
        String dateFormated = null;
        if (date != null) {
            SimpleDateFormat dateOutputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormated = dateOutputFormat.format(date);
        }
        return dateFormated;
    }

    public static Date convertToDate(String timestamp) {
        SimpleDateFormat dateOutputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateFormated = null;

        try {
            dateFormated = dateOutputFormat.parse(timestamp);
        } catch (Exception e) {
            dateFormated = null;
        }

        return dateFormated;
    }
}
