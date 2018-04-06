package com.jivecoin.app.dao;

import org.jooq.DSLContext;
import org.jooq.Queries;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.TableImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
abstract class DaoTest {

    static DaoResource dao;

    @BeforeAll
    void setUpOnce() {
        try {
            // register JDBC driver
            Class.forName("org.h2.Driver");

            // get a connection to an in-memory instance of H2 DB
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

            // connect jooq
            DefaultConfiguration jooqConfig = new DefaultConfiguration();
            jooqConfig.setSQLDialect(SQLDialect.H2);
            jooqConfig.setConnection(conn);
            DSLContext jooq = DSL.using(jooqConfig);

            // set up Dao Resource
            dao = new DaoResource(jooq);

            // set up tables
            testTables().stream()
                .map(jooq::ddl)
                .flatMap(Queries::queryStream)
                .forEach(jooq::execute);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @AfterEach
    public void tearDown() {
        testTables().forEach(t -> dao.jooq.truncate(t).execute());
    }

    @AfterAll
    public void cleanUp() {
        testTables().forEach(t -> dao.jooq.dropTable(t).execute());
        dao.jooq.close();
    }

    /**
     * @return the list of tables needed for this test class
     */
    abstract List<TableImpl<? extends Record>> testTables();

}
