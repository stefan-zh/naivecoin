package com.jivecoin.app.dao;

import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.impl.UpdatableRecordImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BaseDaoTest extends DaoTest {

    private TestDao testDao;

    @BeforeEach
    void setUp() {
        testDao = new TestDao(dao);
    }

    @Test
    @DisplayName("insert")
    void test() {
        TestRecord testRecord = testDao.newRecord();
        testRecord.setId(1);
        testRecord.setData("data");
        testDao.insert(testRecord);

        List<TestRecord> all = testDao.findAll();
        assertEquals(1, all.size());
    }

    @Override
    List<TableImpl<? extends Record>> testTables() {
        return Collections.singletonList(TestTable.TEST);
    }

    // Test Dao
    class TestDao extends BaseDao<TestRecord, Integer> {
        TestDao(DaoResource dao) {
            super(dao, TestTable.TEST);
        }

        @Override
        protected Integer getId(TestRecord testRecord) {
            return testRecord.getId();
        }
    }

    // Test Table
    static class TestTable extends TableImpl<TestRecord> {
        static final TestTable TEST = new TestTable();

        // test_table.id
        final TableField<TestRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false).identity(true), this, "");

        // test_table.data
        final TableField<TestRecord, String> DATA = createField("data", SQLDataType.VARCHAR.nullable(false), this, "");

        TestTable() {
            super(DSL.name("test"), null);
        }

        @Override
        public Class<TestRecord> getRecordType() {
            return TestRecord.class;
        }
    }

    // Test Record
    static class TestRecord extends UpdatableRecordImpl<TestRecord> implements Record2<Integer, String> {

        TestRecord() {
            super(TestTable.TEST);
        }

        @Override
        public TestRecord values(Integer id, String data) {
            value1(id);
            value2(data);
            return this;
        }

        void setId(Integer id) {
            set(0, id);
        }

        Integer getId() {
            return (Integer) get(0);
        }

        void setData(String data) {
            set(1, data);
        }

        String getData() {
            return (String) get(1);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Row2<Integer, String> fieldsRow() {
            return (Row2) super.fieldsRow();
        }

        @Override
        @SuppressWarnings("unchecked")
        public Row2<Integer, String> valuesRow() {
            return (Row2) super.valuesRow();
        }

        @Override
        public Field<Integer> field1() {
            return TestTable.TEST.ID;
        }

        @Override
        public Field<String> field2() {
            return TestTable.TEST.DATA;
        }

        @Override
        public Integer value1() {
            return getId();
        }

        @Override
        public String value2() {
            return getData();
        }

        @Override
        public TestRecord value1(Integer value) {
            setId(value);
            return this;
        }

        @Override
        public TestRecord value2(String value) {
            setData(value);
            return this;
        }

        @Override
        public Integer component1() {
            return getId();
        }

        @Override
        public String component2() {
            return getData();
        }
    }

}
