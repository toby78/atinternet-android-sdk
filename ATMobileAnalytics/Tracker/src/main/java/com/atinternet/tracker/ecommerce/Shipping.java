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
package com.atinternet.tracker.ecommerce;

import com.atinternet.tracker.EventDataObject;
import com.atinternet.tracker.Order;

import java.util.Map;

public class Shipping extends EventDataObject {

    private Order stOrder;

    Shipping(Order stOrder) {
        super();
        /// STRING
        propertiesPrefixMap.put("delivery", "s");

        /// FLOAT
        propertiesPrefixMap.put("costTaxIncluded", "f");
        propertiesPrefixMap.put("costTaxFree", "f");
        this.stOrder = stOrder;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        super.putAll(m);
        updateOrder();
    }

    @Override
    public Object put(String key, Object value) {
        Object result = super.put(key, value);
        updateOrder();
        return result;
    }

    @Override
    public void set(Map<String, Object> obj) {
        super.set(obj);
        updateOrder();
    }

    @Override
    public boolean remove(Object key, Object value) {
        boolean result = super.remove(key, value);
        updateOrder();
        return result;
    }

    @Override
    public Object remove(Object key) {
        Object result = super.remove(key);
        updateOrder();
        return result;
    }

    private void updateOrder() {
        if (stOrder != null) {
            stOrder.Delivery().set(Tool.parseDoubleFromString(String.valueOf(get("f:costTaxFree"))), Tool.parseDoubleFromString(String.valueOf(get("f:costTaxIncluded"))), String.valueOf(get("s:delivery")));
        }
    }
}
