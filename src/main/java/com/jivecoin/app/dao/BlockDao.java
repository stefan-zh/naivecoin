package com.jivecoin.app.dao;

import com.jivecoin.app.generated.tables.BlockTable;
import com.jivecoin.app.generated.tables.records.BlockRecord;

public class BlockDao extends BaseDao<BlockRecord, Integer> {

    protected BlockDao(DaoResource dao) {
        super(dao, BlockTable.BLOCK);
    }

    @Override
    protected Integer getId(BlockRecord block) {
        return block.getIndex();
    }
}
