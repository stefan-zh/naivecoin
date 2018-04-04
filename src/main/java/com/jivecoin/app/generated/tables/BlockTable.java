/*
 * This file is generated by jOOQ.
*/
package com.jivecoin.app.generated.tables;


import com.jivecoin.app.generated.DefaultSchema;
import com.jivecoin.app.generated.Indexes;
import com.jivecoin.app.generated.Keys;
import com.jivecoin.app.generated.tables.records.BlockRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class BlockTable extends TableImpl<BlockRecord> {

    private static final long serialVersionUID = 100816382;

    /**
     * The reference instance of <code>block</code>
     */
    public static final BlockTable BLOCK = new BlockTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BlockRecord> getRecordType() {
        return BlockRecord.class;
    }

    /**
     * The column <code>block.index</code>.
     */
    public final TableField<BlockRecord, Integer> INDEX = createField("index", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>block.hash</code>.
     */
    public final TableField<BlockRecord, String> HASH = createField("hash", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>block.previous_hash</code>.
     */
    public final TableField<BlockRecord, String> PREVIOUS_HASH = createField("previous_hash", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>block.timestamp</code>.
     */
    public final TableField<BlockRecord, BigDecimal> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>block.nonce</code>.
     */
    public final TableField<BlockRecord, Integer> NONCE = createField("nonce", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>block</code> table reference
     */
    public BlockTable() {
        this(DSL.name("block"), null);
    }

    /**
     * Create an aliased <code>block</code> table reference
     */
    public BlockTable(String alias) {
        this(DSL.name(alias), BLOCK);
    }

    /**
     * Create an aliased <code>block</code> table reference
     */
    public BlockTable(Name alias) {
        this(alias, BLOCK);
    }

    private BlockTable(Name alias, Table<BlockRecord> aliased) {
        this(alias, aliased, null);
    }

    private BlockTable(Name alias, Table<BlockRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SQLITE_AUTOINDEX_BLOCK_1, Indexes.SQLITE_AUTOINDEX_BLOCK_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<BlockRecord, Integer> getIdentity() {
        return Keys.IDENTITY_BLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BlockRecord> getPrimaryKey() {
        return Keys.PK_BLOCK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BlockRecord>> getKeys() {
        return Arrays.<UniqueKey<BlockRecord>>asList(Keys.PK_BLOCK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<BlockRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<BlockRecord, ?>>asList(Keys.FK_BLOCK_BLOCK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockTable as(String alias) {
        return new BlockTable(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockTable as(Name alias) {
        return new BlockTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BlockTable rename(String name) {
        return new BlockTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BlockTable rename(Name name) {
        return new BlockTable(name, null);
    }
}