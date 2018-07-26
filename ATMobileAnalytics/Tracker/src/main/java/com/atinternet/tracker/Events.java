/*
This SDK is licensed under the MIT license (MIT)
Copyright (c) 2015- Applied Technologies Internet SAS (registration number B 403 261 258 - Trade and Companies Register of Bordeaux – France)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.atinternet.tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Events extends BusinessObject {

    private final List<Event> events;

    Events(Tracker tracker) {
        super(tracker);
        events = new ArrayList<>();
    }

    int count() {
        return events.size();
    }

    List<Event> getByAction(String action) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.getAction().equals(action)) {
                result.add(e);
            }
        }

        return result;
    }

    Event getByIndex(int index) {
        return events.get(index);
    }

    List<Event> removeByAction(String action) {
        List<Event> result = new ArrayList<>();
        List<Event> newEvts = new ArrayList<>();
        for (Event e : events) {
            if (e.getAction().equals(action)) {
                result.add(e);
            } else {
                newEvts.add(e);
            }
        }
        events.clear();
        events.addAll(newEvts);

        tracker.getBusinessObjects().put(getId(), this);

        return result;
    }

    Event removeByIndex(int index) {
        if (index <= 0 || index >= events.size()) {
            return null;
        }
        Event e = events.remove(index);

        tracker.getBusinessObjects().put(getId(), this);
        return e;
    }

    Event add(String action, Map<String, Object> data) {
        Event e = new Event(action, data);
        events.add(e);

        tracker.getBusinessObjects().put(getId(), this);
        return e;
    }

    void send() {
        tracker.getDispatcher().dispatch(this);
    }

    @Override
    void setParams() {
        tracker.setParam("col", "2");
        try {
            JSONArray eventsArray = new JSONArray();
            for (Event e : events) {
                JSONObject event = new JSONObject();
                event.put("action", e.getAction())
                        .put("data", new JSONObject(e.getData()));
                eventsArray.put(event);
            }
            tracker.setParam("events", eventsArray.toString(), new ParamOption().setEncode(true));

        } catch (JSONException e1) {
            Tool.executeCallback(tracker.getListener(), Tool.CallbackType.BUILD, "error on create events list : " + e1, TrackerListener.HitStatus.Failed);
            tracker.setParam("events", String.valueOf(events), new ParamOption().setEncode(true));
        }

        events.clear();
    }
}
