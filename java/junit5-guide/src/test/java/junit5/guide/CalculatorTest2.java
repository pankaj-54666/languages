package junit5.guide;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest2 {
    Calculator cal;

    @BeforeAll
    static void setup(){
        System.out.println("BeforeAll");

    }

    @AfterAll
    static void cleanup(){
        //ex: clean resources, close db connection etc
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("BeforeEach");
        cal = new Calculator();
        //initialize object before each test, add row to database

    }

    @AfterEach
    void afterEach(){
        //delete the added row from database

    }


    @DisplayName("3+5=8")
    @Test
    void testAddition(){
        assertEquals(cal.add(3,5),8,"addition 3,5 does not reproduce 8");

    }

    /* One benfit is that below test will be listed in the test report as skipped*/
    @Disabled("TODO: still need to fix it")
    @DisplayName("use environment variable to test object")
    @Test
    void test2(){
        fail();
    }


}