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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Event {

    private List<Map<String, Object>> dataObjectList;

    protected String action;

    protected Events events;

    protected Event(Events events, String action) {
        this.action = action;
        this.events = events;
        dataObjectList = new ArrayList<>();
    }

    public Event setAction(String action) {
        this.action = action;
        return this;
    }

    public Event setDataObject(Map<String, Object> dataObject) {
        return setDataObjectList(Collections.singletonList(dataObject));
    }

    protected Event setDataObjectList(List<Map<String, Object>> dataObjectList) {
        this.dataObjectList = dataObjectList;
        return this;
    }

    /***
     * Send current event only
     */
    public void send() {
        events.sendSingle(this);
    }

    /***
     * Get action label from current event
     * @return String
     */
    public String getAction() {
        return action;
    }

    protected List<Map<String, Object>> getDataObjectList() {
        return dataObjectList;
    }
}
