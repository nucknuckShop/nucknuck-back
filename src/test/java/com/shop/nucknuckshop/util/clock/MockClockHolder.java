package com.shop.nucknuckshop.util.clock;

import lombok.AllArgsConstructor;

import java.time.Clock;

@AllArgsConstructor
public class MockClockHolder implements ClockHolder {

    private final Clock clock;

    @Override
    public long getMillis() {
        return clock.millis();
    }

}
