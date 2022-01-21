package com.epamtc.airline.dao;

import com.epamtc.airline.dao.connection.ConnectionPool;
import com.epamtc.airline.dao.impl.CrewDaoImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class QueryExecutorTest {
    CrewDao crewDao = new CrewDaoImpl();

    @BeforeAll
    static void setUp() {
        ConnectionPool.getInstance().init();
    }

    @AfterAll
    static void tearDow() {
        ConnectionPool.getInstance().terminate();
    }

    /*@Test
    void buildEntityListTest() throws DaoException {
        List<CrewDto> actual = crewDao.findAllCrews();
        CrewDto crew21 = actual.get(0);
        CrewDto crew22 = actual.get(1);
        for (CrewDto dto :
                actual) {
            System.out.println(dto);
        }
        assertEquals(crew21.getMembers().get(0).getID(), 2L);
        assertEquals(crew22.getMembers().get(0).getID(), 1L);

    }*/
}
