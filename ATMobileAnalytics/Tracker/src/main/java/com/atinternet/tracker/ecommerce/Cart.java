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

public class Cart extends EventDataObject {

    private com.atinternet.tracker.Cart stCart;
    private Order stOrder;

    Cart(com.atinternet.tracker.Cart stCart, Order stOrder) {
        super();
        /// STRING
        propertiesPrefixMap.put("id", "s");
        propertiesPrefixMap.put("currency", "s");
        propertiesPrefixMap.put("creation", "s");

        /// FLOAT
        propertiesPrefixMap.put("turnoverTaxIncluded", "f");
        propertiesPrefixMap.put("turnoverTaxFree", "f");

        /// LONG
        propertiesPrefixMap.put("quantity", "n");
        this.stCart = stCart;
        this.stOrder = stOrder;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        super.putAll(m);
        updateOrderAndCart();
    }

    @Override
    public Object put(String key, Object value) {
        Object result = super.put(key, value);
        updateOrderAndCart();
        return result;
    }

    @Override
    public void set(Map<String, Object> obj) {
        super.set(obj);
        updateOrderAndCart();
    }

    @Override
    public boolean remove(Object key, Object value) {
        boolean result = super.remove(key, value);
        updateOrderAndCart();
        return result;
    }

    @Override
    public Object remove(Object key) {
        Object result = super.remove(key);
        updateOrderAndCart();
        return result;
    }

    private void updateOrderAndCart() {
        if (stCart != null) {
            stCart.set(String.valueOf(get("s:id")));
        }

        if (stOrder != null) {
            double turnoverTaxIncluded = Tool.parseDoubleFromString(String.valueOf(get("f:turnoverTaxIncluded"))),
                    amountTaxFree = Tool.parseDoubleFromString(String.valueOf(get("f:turnoverTaxFree")));
            stOrder.setTurnover(turnoverTaxIncluded)
                    .Amount().set(amountTaxFree, turnoverTaxIncluded, turnoverTaxIncluded - amountTaxFree);
        }
    }
}
