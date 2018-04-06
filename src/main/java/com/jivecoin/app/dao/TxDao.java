package com.jivecoin.app.dao;

import com.jivecoin.app.generated.tables.TxTable;
import com.jivecoin.app.generated.tables.records.TxRecord;

public class TxDao extends BaseDao<TxRecord, String> {

    public TxDao(DaoResource dao) {
        super(dao, TxTable.TX);
    }

    @Override
    protected String getId(TxRecord tx) {
        dao.tx.getId(tx);
        return tx.getId();
    }
}
