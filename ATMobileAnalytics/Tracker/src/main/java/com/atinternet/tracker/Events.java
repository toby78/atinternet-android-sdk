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
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Events extends BusinessObject {

    private Event singleEvent;
    private final List<Event> eventLists;

    Events(Tracker tracker) {
        super(tracker);
        eventLists = new ArrayList<>();
    }

    void sendSingle(Event e) {
        singleEvent = e;
        tracker.getDispatcher().dispatch(this);

        /// Reinsertion de l'instance dans les business objects si la liste des events a envoyer n'est pas vide pour le dispatch
        if (eventLists.size() > 0) {
            tracker.getBusinessObjects().put(getId(), this);
        }
    }

    /***
     * Add an event
     * @param action String
     * @param dataObject Map
     * @return Event
     */
    public Event add(String action, Map<String, Object> dataObject) {
        return add(action, Collections.singletonList(dataObject));
    }

    /***
     *
     * Add an event
     * @param action String
     * @param dataObjectList List
     * @return Event
     */
    public Event add(String action, List<Map<String, Object>> dataObjectList) {
        return add(new Event(this, action).setDataObjectList(dataObjectList));
    }

    /***
     * Add en event
     * @param e Event
     * @return Event
     */
    public Event add(Event e) {
        eventLists.add(e);
        tracker.getBusinessObjects().put(getId(), this);
        return e;
    }

    /**
     * Send all events stored
     */
    public void send() {
        tracker.getDispatcher().dispatch(this);
    }

    @Override
    void setParams() {
        tracker.setParam("col", "2");

        try {
            JSONArray eventsArray = new JSONArray();

            if (singleEvent != null) {
                String action = singleEvent.getAction();
                List<Map<String, Object>> eventObjectDataList = singleEvent.getDataObjectList();

                if (eventObjectDataList.size() == 0) {
                    eventsArray.put(new JSONObject()
                            .put("action", action)
                            .put("data", new JSONObject()));
                } else {
                    for (Map<String, Object> eventObjectData : eventObjectDataList) {
                        eventsArray.put(new JSONObject()
                                .put("action", action)
                                .put("data", new JSONObject(eventObjectData)));
                    }
                }
                eventLists.remove(singleEvent);
                singleEvent = null;
                
            } else {

                for (Event e : eventLists) {

                    String action = e.getAction();
                    List<Map<String, Object>> eventObjectDataList = e.getDataObjectList();

                    if (eventObjectDataList.size() == 0) {
                        eventsArray.put(new JSONObject()
                                .put("action", action)
                                .put("data", new JSONObject()));
                    } else {
                        for (Map<String, Object> eventObjectData : eventObjectDataList) {
                            eventsArray.put(new JSONObject()
                                    .put("action", action)
                                    .put("data", new JSONObject(eventObjectData)));
                        }
                    }
                }

                eventLists.clear();
            }

            tracker.setParam("events", eventsArray.toString(), new ParamOption().setEncode(true));

        } catch (JSONException e1) {
            Tool.executeCallback(tracker.getListener(), Tool.CallbackType.BUILD, "error on create events list : " + e1, TrackerListener.HitStatus.Failed);
            tracker.setParam("events", String.valueOf(eventLists), new ParamOption().setEncode(true));
            if (singleEvent != null) {
                eventLists.remove(singleEvent);
                singleEvent = null;
            } else {
                eventLists.clear();
            }
        }
    }
}
