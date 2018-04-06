package com.jivecoin.app.dao;

import org.jooq.DSLContext;

public class DaoResource {

    // DAOs
    public BlockDao block;
    public TxDao tx;

    // jOOQ context
    public final DSLContext jooq;

    public DaoResource(DSLContext jooq) {
        this.jooq = jooq;
    }
}
