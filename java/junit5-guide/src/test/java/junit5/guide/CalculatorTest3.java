package junit5.guide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/* parameterized test*/
class CalculatorTest3 {

    Calculator cal;
    @BeforeEach
    void beforeEach(){
        cal = new Calculator();
    }

    /* Example1: simple use case & syntax*/
    /* use of @Test with @ParameterizedTest will cause error, as only 1 of them can be applied at a time*/
    @DisplayName("Parameterized test demo with @MethodeSource")
    @ParameterizedTest
    @MethodSource("parameterizedTestExample1Input")
    void parameterizedTestExample1(int a,int b,int expectedResult){

        int actualResult = cal.add(a,b);
        assertEquals(expectedResult, actualResult, () -> {
            String message = """
                ${a} + ${b} = ${expectedResult} failed
                """;
            return message;
        });
        /* ()->{ return x} can be shorten to () -> x*/
    }

    private static Stream<Arguments> parameterizedTestExample1Input(){
        return Stream.of(
                Arguments.of(2,5,7),
                Arguments.of(-1,4,3),
                Arguments.of(6,0,6)
        );
    }

    /* Example2: when the methodeSource is same as testFuncion, then we can skip the name inside methode source*/
    @DisplayName("Parameterized test demo with @MethodeSource(void)")
    @ParameterizedTest
    @MethodSource()
    void parameterizedTestExample2(int a,int b,int expectedResult){

        int actualResult = cal.add(a,b);
        assertEquals(expectedResult, actualResult, () -> {
            String message = """
                ${a} + ${b} = ${expectedResult} failed
                """;
            return message;
        });
        /* ()->{ return x} can be shorten to () -> x*/
    }

    static Stream<Arguments> parameterizedTestExample2(){
        return Stream.of(
                Arguments.of(2,5,7),
                Arguments.of(-1,4,3),
                Arguments.of(6,0,6)
        );
    }

    /* Example3: @CsvSource: pass string separated by comma */
    @ParameterizedTest
    @CsvSource({
            "33,1,34",
            "3,5,8"
    })
    void parameterizedTestExample3(int a,int b,int expectedResult){

        assertEquals(cal.add(a,b),expectedResult);
    }

    /* Example34 @CsvFileSource: provide csv source file */
    @ParameterizedTest
    @CsvFileSource(resources = "/addition-test.csv")
    void parameterizedTestExample4(int a,int b,int expectedResult){

        assertEquals(cal.add(a,b),expectedResult);
    }




}