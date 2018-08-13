package com.atinternet.tracker.ecommerce;

import com.atinternet.tracker.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class PromotionalCodes extends ArrayList<String> {

    private Order stOrder;

    PromotionalCodes(Order o) {
        this.stOrder = o;
    }

    @Override
    public boolean add(String s) {
        boolean result = super.add(s);
        updateOrder();
        return result;
    }

    @Override
    public void add(int index, String element) {
        super.add(index, element);
        updateOrder();
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        boolean result = super.addAll(c);
        updateOrder();
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        boolean result = super.addAll(index, c);
        updateOrder();
        return result;
    }

    @Override
    public String remove(int index) {
        String result = super.remove(index);
        updateOrder();
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = super.remove(o);
        updateOrder();
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = super.removeAll(c);
        updateOrder();
        return result;
    }

    @Override
    public void replaceAll(UnaryOperator<String> operator) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public void trimToSize() {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public String set(int index, String element) {
        String result = super.set(index, element);
        updateOrder();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        stOrder.Discount().setPromotionalCode(null);
    }

    private void updateOrder() {
        if (stOrder != null) {
            StringBuilder promotionalCodes = new StringBuilder();
            boolean first = true;
            for (String code : this) {
                if (first) {
                    first = false;
                } else {
                    promotionalCodes.append('|');
                }
                promotionalCodes.append(code);
            }
            stOrder.Discount().setPromotionalCode(promotionalCodes.toString());
        }
    }
}
