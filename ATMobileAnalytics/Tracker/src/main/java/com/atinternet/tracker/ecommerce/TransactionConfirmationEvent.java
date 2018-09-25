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

import com.atinternet.tracker.Order;
import com.atinternet.tracker.Tracker;

import java.util.List;
import java.util.Map;

public class TransactionConfirmationEvent extends CheckoutEvent {

    private Tracker tracker;
    private PromotionalCodes promotionalCodes;
    private Transaction transaction;
    private Payment payment;
    private Customer customer;

    TransactionConfirmationEvent(Tracker tracker, String action, com.atinternet.tracker.Cart stCart, Order stOrder) {
        super(tracker.Events(), action, stCart, stOrder);
        this.tracker = tracker;
        promotionalCodes = new PromotionalCodes(stOrder);
        transaction = new Transaction(stOrder);
        payment = new Payment();
        customer = new Customer(stOrder);
    }


    public List<String> Discount() {
        return promotionalCodes;
    }

    public Transaction Transaction() {
        return transaction;
    }

    public Payment Payment() {
        return payment;
    }

    public Customer Customer() {
        return customer;
    }

    @Override
    public void send() {
        tracker.dispatch();
    }

    @Override
    protected List<Map<String, Object>> getDataObjectList() {
        List<Map<String, Object>> eventDataObjectList = super.getDataObjectList();
        for (Map<String, Object> data : eventDataObjectList) {
            data.put("a:s:promotionalCode", promotionalCodes);
            data.put("transaction", transaction);
            data.put("customer", customer);
            data.put("payment", payment);
        }

        return eventDataObjectList;
    }
}
