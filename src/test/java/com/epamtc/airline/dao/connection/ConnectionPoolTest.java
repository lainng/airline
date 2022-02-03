package com.epamtc.airline.dao.connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionPoolTest {

    @BeforeAll
    static void setUp() {
        ConnectionPool.getInstance().init();
    }

    @AfterAll
    static void tearDown() {
        ConnectionPool.getInstance().terminate();
    }

    @Test
    void takeConnectionTest() throws SQLException {
        assertTrue(ConnectionPool.getInstance().takeConnection().isValid(10));
    }

    @Test
    void takeInstanceSingleThreadTest() {
        ConnectionPool firstPool = ConnectionPool.getInstance();
        ConnectionPool secondPool = ConnectionPool.getInstance();

        assertEquals(firstPool, secondPool);
    }

    @Test
    void getInstanceMultiThreadTest() throws InterruptedException {
        ConnectionPool firstPool = ConnectionPool.getInstance();
        Thread otherThread = new Thread(() -> {
            ConnectionPool secondPool = ConnectionPool.getInstance();
            assertEquals(firstPool, secondPool);
        });

        otherThread.start();
        otherThread.join();
    }

}
