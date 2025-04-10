package com.ecommerce.backend.Payment;

public class OrderRequest {
    int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public OrderRequest(int amount) {
        this.amount = amount;
    }


    
}
