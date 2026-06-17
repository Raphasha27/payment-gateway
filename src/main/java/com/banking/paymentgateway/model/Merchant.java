package com.banking.paymentgateway.model;

import jakarta.persistence.*;

@Entity @Table(name = "merchants")
public class Merchant {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String apiKey;
    private String callbackUrl;
    private boolean active = true;

    public Merchant() {}
    public Merchant(String name, String apiKey, String callbackUrl) {
        this.name = name; this.apiKey = apiKey; this.callbackUrl = callbackUrl;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getApiKey() { return apiKey; }
    public String getCallbackUrl() { return callbackUrl; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }
}
