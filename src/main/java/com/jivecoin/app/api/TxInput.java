package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TxInput {

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 1 byte = 2 hex characters, 64 bytes
    public String transaction; // transaction hash taken from a previous unspent transaction output

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 64 bytes
    public String index; // index of the transaction taken from a previous unspent transaction output

    @JsonProperty
    @NotNull
    @Min(0)
    public BigDecimal amount;

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 64 bytes
    public String address; // from address

    @JsonProperty
    @NotBlank
    @Length(min = 256, max = 256) // 128 bytes
    public String signature; // transaction input hash: sha256 (transaction + index + amount + address) signed with owner address's secret key

}
