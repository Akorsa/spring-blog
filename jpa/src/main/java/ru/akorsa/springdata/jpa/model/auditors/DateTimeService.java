package ru.akorsa.springdata.jpa.model.auditors;

import java.time.ZonedDateTime;

public interface DateTimeService {

    ZonedDateTime getCurrentDateAndTime();
}
