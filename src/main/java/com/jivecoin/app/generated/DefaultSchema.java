/*
 * This file is generated by jOOQ.
*/
package com.jivecoin.app.generated;


import com.jivecoin.app.generated.tables.BlockTable;
import com.jivecoin.app.generated.tables.TxTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = -318749968;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>block</code>.
     */
    public final BlockTable BLOCK = com.jivecoin.app.generated.tables.BlockTable.BLOCK;

    /**
     * The table <code>tx</code>.
     */
    public final TxTable TX = com.jivecoin.app.generated.tables.TxTable.TX;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            BlockTable.BLOCK,
            TxTable.TX);
    }
}
