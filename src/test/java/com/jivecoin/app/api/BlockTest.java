package com.jivecoin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BlockTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private String blockFixture = fixture("fixtures/block.json");

    @Test
    void testBlock() throws IOException {
        Block block = MAPPER.readValue(blockFixture, Block.class);

        // assertion block headers
        assertEquals(BigInteger.ZERO, block.index);
        assertEquals("14aa6364081adf2f1b2a603eeff6d1670db4b32d7c1a60a2a4c540511067fb8f75d42bce3a4a145249e06783ac536f20a35034fc73efca293e7cbbdeae3000b7", block.hash);
        assertEquals("0", block.previousHash);
        assertEquals(1465154705, block.timestamp);
        assertEquals(BigInteger.ZERO, block.nonce);

        // assert tx's
        assertEquals(1, block.transactions.size());
        Transaction tx = block.transactions.get(0);
        assertEquals("3d8d656b7be850ced8160f6cbb172ea8bc2c3d9a0cbca0b9e7d2a18ce70efb8f629855ae1c2f9093ad2a1854e515d13976d40f90af0aedaed8bfc003e3a0b53b", tx.id);
        assertEquals("26f612ff0b658125661e6af45c92ac20214e76f801c86240bf863309e9cf4b5521a21aae45a41376a6fec5c5b60525d12583d73111f93d6372bbce21151d6f2b", tx.hash);
        assertEquals(TxType.REGULAR, tx.type);
        assertTrue(tx.data.inputs.isEmpty());
        assertTrue(tx.data.outputs.isEmpty());
    }

}
