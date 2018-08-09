package com.atinternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.atinternet.tracker.ATInternet;
import com.atinternet.tracker.Tracker;
import com.atinternet.tracker.ecommerce.Product;
import com.atinternet.tracker.ecommerce.ProductEvent;

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
        Map<String, Object> productData1 = new HashMap<>();
        productData1.put("id", "1");
        productData1.put("test", true);
        Map<String, Object> productData2 = new HashMap<>();
        productData2.put("id", "2");
        productData2.put("toto", 46);

        Map<String, Object> productData3 = new HashMap<>();
        productData3.put("id", "3");
        productData3.put("tata", 3.6);


        switch (v.getId()) {
            case R.id.displayProduct:
                /// Add two display product not sent
                tracker.ECommerce().DisplayProducts().add().Products().add(new Product(productData3));
                tracker.ECommerce().DisplayProducts().add();

                /// New display product with two product sent
                ProductEvent pe = tracker.ECommerce().DisplayProducts().add();
                pe.Products().add(new Product(productData1));
                pe.Products().add(new Product(productData2));
                pe.send();

                tracker.dispatch();
                break;
            case R.id.clickProduct:
                break;
            case R.id.displayPageProduct:
                break;
            case R.id.addProduct:
                break;
            case R.id.removeProduct:
                break;
            case R.id.displayCart:
                break;
            case R.id.updateCart:
                break;
            case R.id.deliveryCheckout:
                break;
            case R.id.paymentCheckout:
                break;
            case R.id.transactionConfirmation:
                break;
        }
    }
}
