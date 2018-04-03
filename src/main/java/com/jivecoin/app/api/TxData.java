package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TxData {

    @JsonProperty
    @NotNull
    @Valid
    public List<TxInput> inputs; // Transaction inputs

    @JsonProperty
    @NotNull
    @Valid
    public List<TxOutput> outputs; // Transaction outputs

}
