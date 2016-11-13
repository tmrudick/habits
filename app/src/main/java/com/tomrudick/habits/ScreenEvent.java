package com.tomrudick.habits;

/**
 * Represents a screen on/off event
 */
public class ScreenEvent {
    private final String state;
    private final long timestamp;

    public ScreenEvent(String state) {
        this.state = state;
        this.timestamp = System.currentTimeMillis();
    }

    public String getState() {
        return state;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
