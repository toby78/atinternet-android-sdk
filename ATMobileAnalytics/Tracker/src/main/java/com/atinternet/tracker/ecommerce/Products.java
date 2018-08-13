package com.atinternet.tracker.ecommerce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;

public class Products extends ArrayList<Product> {

    private com.atinternet.tracker.Cart stCart;

    Products(com.atinternet.tracker.Cart c) {
        this.stCart = c;
    }

    @Override
    public boolean add(Product p) {
        boolean result = super.add(p);
        updateCart();
        return result;
    }

    @Override
    public void add(int index, Product element) {
        super.add(index, element);
        updateCart();
    }

    @Override
    public boolean addAll(Collection<? extends Product> c) {
        boolean result = super.addAll(c);
        updateCart();
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Product> c) {
        boolean result = super.addAll(index, c);
        updateCart();
        return result;
    }

    @Override
    public Product remove(int index) {
        Product result = super.remove(index);
        updateCart();
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = super.remove(o);
        updateCart();
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = super.removeAll(c);
        updateCart();
        return result;
    }

    @Override
    public void replaceAll(UnaryOperator<Product> operator) {
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
    public Product set(int index, Product element) {
        Product result = super.set(index, element);
        updateCart();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        updateCart();
    }

    private void updateCart() {
        if (stCart != null) {
            stCart.Products().removeAll();
            for (Product p : this) {
                stCart.Products().add(String.format("%s[%s]", String.valueOf(p.get("s:id")), String.valueOf(p.get("s:name"))),
                        String.valueOf(p.get("s:category1")),
                        String.valueOf(p.get("s:category2")),
                        String.valueOf(p.get("s:category3")),
                        String.valueOf(p.get("s:category4")),
                        String.valueOf(p.get("s:category5")),
                        String.valueOf(p.get("s:category6")))
                        .setQuantity(Tool.parseIntFromString(String.valueOf(p.get("n:quantity"))))
                        .setUnitPriceTaxFree(Tool.parseDoubleFromString(String.valueOf("f:priceTaxFree")))
                        .setUnitPriceTaxIncluded(Tool.parseDoubleFromString(String.valueOf("f:priceTaxIncluded")));
            }
        }
    }
}
