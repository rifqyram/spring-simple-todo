package com.enigma.simpletodo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String convertEpochTimeToDate(long epochTime) {
        Date date = new Date(epochTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
