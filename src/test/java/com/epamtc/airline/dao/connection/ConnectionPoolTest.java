package com.epamtc.airline.dao.connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionPoolTest {

    @BeforeAll
    static void setUp() {
        ConnectionPool.getInstance().init();
    }

    @AfterAll
    static void tearDow() {
        ConnectionPool.getInstance().terminate();
    }

    @Test
    void takeConnectionTest() throws SQLException {
        assertTrue(ConnectionPool.getInstance().takeConnection().isValid(10));
    }

}
