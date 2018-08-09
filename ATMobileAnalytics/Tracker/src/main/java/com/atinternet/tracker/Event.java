package com.atinternet.tracker;

import java.util.List;
import java.util.Map;

public abstract class Event {

    private EventList el;
    private String action;

    protected String getAction() {
        return action;
    }

    protected abstract List<Map<String, Object>> getDataObjectList();

    public void send() {
        el.send(this);
    }

    protected Event(EventList el, String action) {
        this.action = action;
        this.el = el;
        el.add(this);
    }
}
