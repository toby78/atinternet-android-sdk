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

import java.util.Map;

public class Product extends EventDataObject {

    public Product(Map<String, Object> obj) {
        super();
        /// STRING
        propertiesPrefixMap.put("id", "s");
        propertiesPrefixMap.put("name", "s");
        propertiesPrefixMap.put("brand", "s");
        propertiesPrefixMap.put("currency", "s");
        propertiesPrefixMap.put("category1", "s");
        propertiesPrefixMap.put("category2", "s");
        propertiesPrefixMap.put("category3", "s");
        propertiesPrefixMap.put("category4", "s");
        propertiesPrefixMap.put("category5", "s");
        propertiesPrefixMap.put("category6", "s");
        propertiesPrefixMap.put("list", "s");
        propertiesPrefixMap.put("list_origin", "s");

        /// BOOLEAN
        propertiesPrefixMap.put("discount", "b");
        propertiesPrefixMap.put("stock", "b");
        propertiesPrefixMap.put("cart.creation", "b");

        /// FLOAT
        propertiesPrefixMap.put("priceTaxIncluded", "f");
        propertiesPrefixMap.put("priceTaxFree", "f");

        /// LONG
        propertiesPrefixMap.put("position", "n");
        propertiesPrefixMap.put("quantity", "n");
        for (Entry<String, Object> entry : obj.entrySet()) {
            super.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public void set(Map<String, Object> obj) {
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }
}
