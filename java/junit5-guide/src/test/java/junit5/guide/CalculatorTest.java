package junit5.guide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void testAddition(){
        Calculator cal = new Calculator();
        int result = cal.add(3,5);

        assertEquals(result,8);
        assertEquals(result,8,"Additional of 3,5 does not result in 8");
    }


//    TO:add parameterized tst with lazy custom assert message(lazy assert message)
    //disturbing of passing string vs passing lambda function as assert message (hint: lambda function computed only in case of failed case)

    /*  Descriptive naming of unit test : it helps check what test is on testReport
        test<SystemUnderTest>_<ConditionOrStateChange>_<ExpectedResult>
    */
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo(){
        Calculator cal = new Calculator();
        assertEquals(cal.sub(4,2),2);
    }

    /* descriptive naming of unit test : it removed need of long test methode name*/
    @DisplayName("subtraction 6-8=-2")
    @Test
    void testSubstraction2(){
        Calculator cal = new Calculator();
        assertEquals(cal.sub(6,8),-2);
    }

    /* AAA test pattern: Arrange,Act,Assert*/
    @Test
    public void testAAADemo(){

        //Arrange :: Prepare & Initialize variable and object
        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2;
        Calculator cal = new Calculator();

        //Act :: call methode to test
        int actualResult = cal.divide(dividend,divisor);

        //Assert ::  assert the actualResult
        assertEquals(expectedResult,actualResult,"4/2 does not result in 2");

    }

}