package ru.akorsa.springdata.jpa.model.auditors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

public class CurrentTimeDateTimeService implements DateTimeService {

    public static final Logger logger = LoggerFactory.getLogger(CurrentTimeDateTimeService.class);

    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        ZonedDateTime currentDateAndTime = ZonedDateTime.now();

        logger.info("Returning current date and time: {}", currentDateAndTime);
        return currentDateAndTime;
    }
}
