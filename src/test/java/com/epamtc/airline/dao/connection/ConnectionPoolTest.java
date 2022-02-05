package com.epamtc.airline.dao.connection;

import com.epamtc.airline.ConnectionPoolExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ConnectionPoolExtension.class)
public class ConnectionPoolTest {

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
