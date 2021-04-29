package com.potatoes_are_great.utill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    @Test
    void getTimeFromPattern() {
        assertNull(Time.getTimeFromPattern("ggg", Patterns.TIME_PATTERN));

        Time test = new Time(1,1,1);
        assertEquals(test,Time.getTimeFromPattern(" 1:01:01",Patterns.TIME_PATTERN));
        assertNull(Time.getTimeFromPattern(" 1:1:01", Patterns.TIME_PATTERN));
        assertNull(Time.getTimeFromPattern(" 1:01:1", Patterns.TIME_PATTERN));
        assertNull(Time.getTimeFromPattern(" 1:1:1", Patterns.TIME_PATTERN));
    }



    @Test
    void testToString() {
        Time test = new Time(1,1,1);
        assertEquals("1:1:1", test.toString());
        test = new Time(23,59,59);
        assertEquals("23:59:59",test.toString());
        test = new Time(24,59,59);
        assertEquals("0:59:59",test.toString());
    }

    @Test
    void getHours() {
        Time test = new Time(1,1,1);
        assertEquals(1, test.getHours());
        test = new Time(23,59,59);
        assertEquals(23,test.getHours());
        test = new Time(24,59,59);
        assertEquals(24,test.getHours());
    }

    @Test
    void getMinutes() {
        Time test = new Time(1,1,1);
        assertEquals(1, test.getMinutes());
        test = new Time(23,59,59);
        assertEquals(59,test.getMinutes());
    }

    @Test
    void getSeconds() {
        Time test = new Time(1,1,1);
        assertEquals(1, test.getSeconds());
        test = new Time(23,59,59);
        assertEquals(59,test.getSeconds());
    }

    @Test
    void add() {
        Time test = new Time(23,59,59);
        assertEquals("23:59:59",test.toString());
        test.add(new Time(0,0,1));
        assertEquals("0:0:0",test.toString());
        assertEquals(24, test.getHours());
        test.dayFilter();
        assertEquals(0, test.getHours());
    }

    @Test
    void sub() {
        Time test = new Time(0,0,0);
        assertEquals("0:0:0",test.toString());
        test.sub(new Time(0,0,1));
        assertEquals("-1:59:59",test.toString());
        test.dayFilter();
        assertEquals("23:59:59",test.toString());
    }

    @Test
    void cmp(){
        Time test = new Time(0,0,0);
        Time tes2 = new Time(0,0,0);
        Time test3 = new Time(0,1,0);
        Object o = new Object();

        assertEquals(test, tes2);
        assertNotEquals(test, test3);
        assertNotEquals(test,o);
    }
}