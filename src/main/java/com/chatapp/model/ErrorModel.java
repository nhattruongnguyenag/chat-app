package com.chatapp.model;

public class ErrorModel {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorModel() {
    }

    public ErrorModel(String code) {
        this.code = code;
    }
}
