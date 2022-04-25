package com.kollus.thirdparty.pallycon.v2.token.policy.common;

public enum ResponseFormat {
    ORIGINAL("original"),
    CUSTOM("custom");

    private String value;

    ResponseFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
