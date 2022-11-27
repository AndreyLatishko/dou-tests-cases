package com.tests.api;

public class UnSuccessRegister {
    private String error;

    public UnSuccessRegister() {
    }

    public UnSuccessRegister(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
