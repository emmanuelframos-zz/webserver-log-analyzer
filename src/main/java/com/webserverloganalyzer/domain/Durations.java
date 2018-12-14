package com.webserverloganalyzer.domain;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;

public enum Durations {

    HOURLY {
        @Override
        public Date getEndDate(Date startDate) {
            return Durations.calculate(startDate, HOUR);
        }
    },
    DAILY {
        @Override
        public Date getEndDate(Date startDate) {
            return Durations.calculate(startDate, DAY_OF_MONTH);
        }
    };

    public abstract Date getEndDate(Date startDate);

    private static Date calculate(Date startDate, Integer constant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(constant, 1);
        return calendar.getTime();
    }

}