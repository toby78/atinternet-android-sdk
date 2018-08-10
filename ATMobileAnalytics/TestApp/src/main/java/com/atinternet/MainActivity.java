package com.atinternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.atinternet.tracker.ATInternet;
import com.atinternet.tracker.Tracker;
import com.atinternet.tracker.ecommerce.CartEvent;
import com.atinternet.tracker.ecommerce.CheckoutEvent;
import com.atinternet.tracker.ecommerce.Product;
import com.atinternet.tracker.ecommerce.ProductEvent;
import com.atinternet.tracker.ecommerce.TransactionConfirmationEvent;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.displayProduct).setOnClickListener(this);
        findViewById(R.id.clickProduct).setOnClickListener(this);
        findViewById(R.id.displayPageProduct).setOnClickListener(this);
        findViewById(R.id.addProduct).setOnClickListener(this);
        findViewById(R.id.removeProduct).setOnClickListener(this);
        findViewById(R.id.displayCart).setOnClickListener(this);
        findViewById(R.id.updateCart).setOnClickListener(this);
        findViewById(R.id.deliveryCheckout).setOnClickListener(this);
        findViewById(R.id.paymentCheckout).setOnClickListener(this);
        findViewById(R.id.transactionConfirmation).setOnClickListener(this);

        tracker = ATInternet.getInstance().getDefaultTracker();
        tracker.setDefaultListener();
        tracker.setSiteId(552987, null, true);
        tracker.setLog("logp", null, true);
        tracker.setSecuredLog("logs", null, true);
        //tracker.setSecureModeEnabled(true, null, true);
    }

    @Override
    public void onClick(View v) {
        Map<String, Object> shippingData = new HashMap<>();
        shippingData.put("delivery", "pigeon voyageur");

        Map<String, Object> cartData = new HashMap<>();
        cartData.put("id", "cartID");


        Map<String, Object> productData2 = new HashMap<>();
        productData2.put("id", "2");

        Map<String, Object> productData3 = new HashMap<>();
        productData3.put("id", "3");
        productData2.put("key", "value");

        Map<String, Object> productData1 = new HashMap<>();
        productData1.put("id", "1");
        productData2.put("key", "value");


        switch (v.getId()) {
            case R.id.displayProduct:
                Product p1 = new Product(productData3);
                p1.put("brand", "adadas");
                p1.put("key", "value");
                tracker.ECommerce().DisplayProducts().add().Products().add(p1);
                tracker.ECommerce().DisplayProducts().add();

                ProductEvent pe1 = tracker.ECommerce().DisplayProducts().add();
                pe1.Products().add(new Product(productData1));
                pe1.Products().add(new Product(productData2));
                pe1.send();

                tracker.dispatch();
                break;
            case R.id.clickProduct:
                Product p2 = new Product(productData3);
                p2.put("brand", "adadas");
                p2.put("key", "value");
                tracker.ECommerce().ClickProducts().add().Products().add(p2);
                tracker.ECommerce().ClickProducts().add();

                ProductEvent pe2 = tracker.ECommerce().ClickProducts().add();
                pe2.Products().add(new Product(productData1));
                pe2.Products().add(new Product(productData2));
                pe2.send();

                tracker.dispatch();
                break;
            case R.id.displayPageProduct:
                Product p3 = new Product(productData3);
                p3.put("brand", "adadas");
                p3.put("key", "value");
                tracker.ECommerce().DisplayPageProducts().add().Products().add(p3);
                tracker.ECommerce().DisplayPageProducts().add();

                ProductEvent pe3 = tracker.ECommerce().DisplayPageProducts().add();
                pe3.Products().add(new Product(productData1));
                pe3.Products().add(new Product(productData2));
                pe3.send();

                tracker.dispatch();
                break;
            case R.id.addProduct:
                Product p4 = new Product(productData3);
                p4.put("brand", "adadas");
                p4.put("key", "value");
                tracker.ECommerce().AddProducts().add().Products().add(p4);
                tracker.ECommerce().AddProducts().add();

                CartEvent ce1 = tracker.ECommerce().AddProducts().add();
                ce1.Products().add(new Product(productData1));
                ce1.Products().add(new Product(productData2));

                ce1.Cart().set(cartData);
                ce1.Cart().put("quantity", 3);
                ce1.Cart().put("key", "value");

                ce1.send();

                tracker.dispatch();
                break;
            case R.id.removeProduct:
                Product p5 = new Product(productData3);
                p5.put("brand", "adadas");
                p5.put("key", "value");
                tracker.ECommerce().RemoveProducts().add().Products().add(p5);
                tracker.ECommerce().RemoveProducts().add();

                CartEvent ce2 = tracker.ECommerce().RemoveProducts().add();
                ce2.Products().add(new Product(productData1));
                ce2.Products().add(new Product(productData2));

                ce2.Cart().set(cartData);
                ce2.Cart().put("quantity", 3);
                ce2.Cart().put("key", "value");

                ce2.send();

                tracker.dispatch();
                break;
            case R.id.displayCart:
                Product p6 = new Product(productData3);
                p6.put("brand", "adadas");
                p6.put("key", "value");
                tracker.ECommerce().DisplayCarts().add().Products().add(p6);
                tracker.ECommerce().DisplayCarts().add();

                CartEvent ce3 = tracker.ECommerce().DisplayCarts().add();
                ce3.Products().add(new Product(productData1));
                ce3.Products().add(new Product(productData2));

                ce3.Cart().set(cartData);
                ce3.Cart().put("quantity", 3);
                ce3.Cart().put("key", "value");

                ce3.send();

                tracker.dispatch();
                break;
            case R.id.updateCart:
                Product p7 = new Product(productData3);
                p7.put("brand", "adadas");
                p7.put("key", "value");
                tracker.ECommerce().UpdateCarts().add().Products().add(p7);
                tracker.ECommerce().UpdateCarts().add();

                CartEvent ce4 = tracker.ECommerce().UpdateCarts().add();
                ce4.Products().add(new Product(productData1));
                ce4.Products().add(new Product(productData2));

                ce4.Cart().set(cartData);
                ce4.Cart().put("quantity", 3);
                ce4.Cart().put("key", "value");

                ce4.send();

                tracker.dispatch();
                break;
            case R.id.deliveryCheckout:
                Product p8 = new Product(productData3);
                p8.put("brand", "adadas");
                p8.put("key", "value");
                tracker.ECommerce().DeliveryCheckouts().add().Products().add(p8);
                tracker.ECommerce().DeliveryCheckouts().add();

                CheckoutEvent che1 = tracker.ECommerce().DeliveryCheckouts().add();
                che1.Products().add(new Product(productData1));
                che1.Products().add(new Product(productData2));

                che1.Cart().set(cartData);
                che1.Cart().put("quantity", 3);
                che1.Cart().put("key", "value");

                che1.Shipping().set(shippingData);
                che1.Shipping().put("costTaxIncluded", 234.56);
                che1.Shipping().put("key", "value");

                che1.send();

                tracker.dispatch();
                break;
            case R.id.paymentCheckout:
                Product p9 = new Product(productData3);
                p9.put("brand", "adadas");
                p9.put("key", "value");
                tracker.ECommerce().PaymentCheckouts().add().Products().add(p9);
                tracker.ECommerce().PaymentCheckouts().add();

                CheckoutEvent che2 = tracker.ECommerce().PaymentCheckouts().add();
                che2.Products().add(new Product(productData1));
                che2.Products().add(new Product(productData2));

                che2.Cart().set(cartData);
                che2.Cart().put("quantity", 3);
                che2.Cart().put("key", "value");

                che2.Shipping().set(shippingData);
                che2.Shipping().put("costTaxIncluded", 234.56);
                che2.Shipping().put("key", "value");

                che2.send();

                tracker.dispatch();
                break;
            case R.id.transactionConfirmation:
                TransactionConfirmationEvent tce = tracker.ECommerce().TransactionConfirmations().add();
                tce.Products().add(new Product(productData1));
                tce.Products().add(new Product(productData2));

                tce.Cart().set(cartData);
                tce.Cart().put("quantity", 3);
                tce.Cart().put("key", "value");

                tce.Shipping().set(shippingData);
                tce.Shipping().put("costTaxIncluded", 234.56);
                tce.Shipping().put("key", "value");

                tce.Customer().put("new", true);
                tce.Customer().put("key", "value");

                tce.Discount().add("une");
                tce.Discount().add("liste");
                tce.Discount().add("de");
                tce.Discount().add("codes");
                tce.Discount().add("promotionnels");
                tce.Discount().add("suffisamment");
                tce.Discount().add("longue");
                tce.Discount().add("je");
                tce.Discount().add("l'espere");
                tce.Discount().add("pour");
                tce.Discount().add("faire");
                tce.Discount().add("un");
                tce.Discount().add("multi");
                tce.Discount().add("hit");

                tce.Payment().put("mode", "troc");
                tce.Payment().put("key", "value");

                tce.Transaction().put("id", "id");
                tce.Transaction().put("key", "value");

                tce.send();
                break;
        }
    }
}
