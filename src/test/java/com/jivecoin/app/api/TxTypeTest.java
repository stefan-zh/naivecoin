package com.jivecoin.app.api;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TxTypeTest {

    @ParameterizedTest
    @EnumSource(TxType.class)
    void testTxType(TxType type) {
        String name = type.toString();
        assertTrue(StringUtils.isAllLowerCase(name));
        assertEquals(type, TxType.fromString(name));
    }

}
