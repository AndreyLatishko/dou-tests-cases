package com.tests.api;

public class SuccessRegister {
    private Integer id;
    private String token;


    public SuccessRegister(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public SuccessRegister() {
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
