package com.jivecoin.app.dao;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DAOImpl;

import java.util.Collection;

abstract class BaseDao<R extends UpdatableRecord<R>, T> extends DAOImpl<R, R, T> {

    /*
     * DAOs should be able to communicate with each other and they can do that
     * through the shared Dao resource.
     */
    DaoResource dao;

    BaseDao(DaoResource dao, Table<R> table) {
        super(table, table.getDataType().getType(), dao.jooq.configuration());
        this.dao = dao;
    }

    public DSLContext jooq() {
        return dao.jooq;
    }

    /**
     * Creates a new record and attaches the jOOQ configuration to it.
     */
    R newRecord() {
        R record = jooq().newRecord(getTable());
        record.attach(dao.jooq.configuration());
        return record;
    }

    @Override
    public void insert(Collection<R> records) {
        records.forEach(UpdatableRecord::insert);
    }

    @Override
    public void update(Collection<R> records) {
        records.forEach(UpdatableRecord::update);
    }

    @Override
    public void delete(Collection<R> records) {
        records.forEach(UpdatableRecord::delete);
    }

    @Override
    public void deleteById(Collection<T> ids) {
        Field<?> pk = getTable().getPrimaryKey().getFieldsArray()[0];
        jooq()
            .delete(getTable())
            .where(pk.in(ids))
            .execute();
    }
}
