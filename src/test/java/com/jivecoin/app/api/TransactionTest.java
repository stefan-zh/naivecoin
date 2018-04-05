package com.jivecoin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private String txFixture = fixture("fixtures/transaction.json");

    @Test
    @DisplayName("test transaction")
    void testTx() throws IOException {
        Transaction tx = MAPPER.readValue(txFixture, Transaction.class);

        // assert headers
        assertEquals("4a5fd0a671c3986ff9f0b154aaefeac62f4d92448de73f9f4841050db7ba21c70870cee8135c734d34ca6f4d1b64e203921d9bfe84b66f02be5e739691ac51a5", tx.id);
        assertEquals("2f635c60a14f92bd3f5dc559cfa3a9513cdd8ec5e84336f6f0ab0b268ba5c82c7ed5e723a82c991eea460309c33b0659a4249a9cadd8035def809c18bc095535", tx.hash);
        assertEquals(TxType.REGULAR, tx.type);

        // assert tx inputs
        assertEquals(1, tx.data.inputs.size());
        TxInput txInput = tx.data.inputs.get(0);
        assertEquals("8965dcf76601e9055b837f860da7684f6db5e1a5b6f88962747b86be4366867147a6d8f6857eb5bee62f66d508f5c340ce5ba48d1d53704b6de9f4457fb463f7", txInput.transaction);
        assertEquals("0", txInput.index);
        assertEquals(0, BigDecimal.valueOf(100).compareTo(txInput.amount));
        assertEquals("d2e1fd874b797bd242c34bee4d0610dae82982856ff69bb0d7863016fd59eab01d3d332e7652f7be92e82d463c6453e045051b9f84e7e3fd4dfb870c40b70e52", txInput.address);
        assertEquals("900461191beaf55c045b7b9436999e62b66c49554cc4ce69d9840f266f3379f4664a7affc79b755f5297f915af41bef5f44379e4155a200d1f7830d0812180f0722eff835c11b82c290ca623ebfc8cc4bc48a73872aab2ad8f3c7e7f48ff01af462d69bb3c988c7489bb267df57c0c3263eacf15eec5f08bc34ca78ebff6a219", txInput.signature);

        // assert tx outputs
        assertEquals(2, tx.data.outputs.size());
        assertTrue(tx.data.outputs.stream().anyMatch(txo ->
            BigDecimal.valueOf(25).compareTo(txo.amount) == 0
            && txo.address.equals("3c309a9ea12db47d008ed08e1237630628db8e1bc57eb264b818c70385dcfbf9934fde6f7d255c1c8fa80cbf65e4607439b75f7f94c355fd1642f6080e1458fc"))
        );
        assertTrue(tx.data.outputs.stream().anyMatch(txo ->
            BigDecimal.valueOf(75).compareTo(txo.amount) == 0
            && txo.address.equals("d2e1fd874b797bd242c34bee4d0610dae82982856ff69bb0d7863016fd59eab01d3d332e7652f7be92e82d463c6453e045051b9f84e7e3fd4dfb870c40b70e52"))
        );
    }

}
