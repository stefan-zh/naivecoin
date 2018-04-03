package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TxOutput {

    @JsonProperty
    @NotNull
    @Min(0)
    public BigDecimal amount;

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 1 byte = 2 hex characters, 64 bytes
    public String address; // to address or change address

}
