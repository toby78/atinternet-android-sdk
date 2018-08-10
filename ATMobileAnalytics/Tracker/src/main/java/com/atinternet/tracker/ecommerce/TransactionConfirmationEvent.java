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

import com.atinternet.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionConfirmationEvent extends CheckoutEvent {

    private Tracker tracker;
    private List<String> promotionalCodes;
    private Transaction transaction;
    private Payment payment;
    private Customer customer;

    TransactionConfirmationEvent(Tracker tracker, String action) {
        super(tracker.Events(), action);
        this.tracker = tracker;
        promotionalCodes = new ArrayList<>();
        transaction = new Transaction();
        payment = new Payment();
        customer = new Customer();
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
        /// Sales Tracker
        /// Cart
        com.atinternet.tracker.Cart stCart = tracker.Cart().set(String.valueOf(cart.get("s:id")));
        for (Product p : products) {
            stCart.Products().add(String.format("%s[%s]", String.valueOf(p.get("s:id")), String.valueOf(p.get("s:name"))),
                    String.valueOf(p.get("s:category1")),
                    String.valueOf(p.get("s:category2")),
                    String.valueOf(p.get("s:category3")),
                    String.valueOf(p.get("s:category4")),
                    String.valueOf(p.get("s:category5")),
                    String.valueOf(p.get("s:category6")))
                    .setQuantity(parseIntFromString(String.valueOf(p.get("n:quantity"))))
                    .setUnitPriceTaxFree(parseDoubleFromString(String.valueOf("f:priceTaxFree")))
                    .setUnitPriceTaxIncluded(parseDoubleFromString(String.valueOf("f:priceTaxIncluded")));
        }

        StringBuilder backwardPromotionalCode = new StringBuilder();
        boolean first = true;
        for (String code : promotionalCodes) {
            if (first) {
                first = false;
                backwardPromotionalCode.append(code);
            } else {
                backwardPromotionalCode.append('|').append(code);
            }
        }
        double turnoverTaxIncluded = parseDoubleFromString(String.valueOf(cart.get("f:turnoverTaxIncluded"))),
                amountTaxFree = parseDoubleFromString(String.valueOf(cart.get("f:turnoverTaxFree")));
        tracker.Orders().add(String.valueOf(transaction.get("s:id")), turnoverTaxIncluded)
                .Amount().set(amountTaxFree, turnoverTaxIncluded, turnoverTaxIncluded - amountTaxFree)
                .Delivery().set(parseDoubleFromString(String.valueOf(shipping.get("f:costTaxFree"))), parseDoubleFromString(String.valueOf(shipping.get("f:costTaxIncluded"))), String.valueOf(shipping.get("s:delivery")))
                .setNewCustomer(Boolean.parseBoolean(String.valueOf(customer.get("b:new"))))
                .setStatus(3)
                .setConfirmationRequired(false)
                .setPaymentMethod(0)
                .Discount().setPromotionalCode(backwardPromotionalCode.toString());
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

    private double parseDoubleFromString(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception ignored) {
            return 0.0;
        }
    }

    private int parseIntFromString(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ignored) {
            return 0;
        }
    }
}
