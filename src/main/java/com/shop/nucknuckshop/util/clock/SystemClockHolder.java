package com.shop.nucknuckshop.util.clock;

import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class SystemClockHolder implements ClockHolder {

    @Override
    public long getMillis() {
        return Clock.systemUTC().millis();
    }
}
