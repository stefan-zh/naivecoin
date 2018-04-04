package com.jivecoin.app.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

public class Block {

    @JsonProperty
    @NotNull
    @Min(0)
    public BigInteger index; // index (first block: 0)

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 64 bytes
    public String hash; // hash taken from the contents of the block: sha256 (index + previousHash + timestamp + nonce + transactions)

    @JsonProperty
    @NotBlank
    @Length(min = 128, max = 128) // 1 byte = 2 hex characters, 64 bytes
    public String previousHash; // hash of previous block, first block is 0

    @JsonProperty
    public long timestamp; // number of seconds since January 1, 1970

    @JsonProperty
    @NotNull
    @Min(0)
    public BigInteger nonce; // nonce used to identify the proof-of-work step.

    @JsonProperty
    @NotNull
    @Valid
    public List<Transaction> transactions;

}
