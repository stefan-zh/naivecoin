package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Transaction {

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 1 byte = 2 hex characters, 64 bytes
    public String id; // random id

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 64 bytes
    public String hash; // hash taken from the contents of the transaction: sha256 (id + data)

    @JsonProperty
    @NotNull
    public TxType type; // transaction type (regular, fee, reward)

    @JsonProperty
    @NotNull
    @Valid
    public TxData data;

}
