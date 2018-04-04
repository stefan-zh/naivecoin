package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TxType {
    REGULAR,
    FEE,
    REWARD;

    @Override
    @JsonValue
    public String toString() {
        return name().toLowerCase();
    }

    public static TxType fromString(String value) {
        return TxType.valueOf(value.toUpperCase());
    }

}
