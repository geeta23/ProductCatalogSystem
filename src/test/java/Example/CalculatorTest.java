package Example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    // Naming convention of test function should be Test_<What test is performed>_<what it returns>
    //@Test annotation to make it stand alone test
    @Test
    public void Test_AddTwoIntegers_ReturnsInteger(){
        //Arrange
        Calculator cal = new Calculator();

        // Act
        int result = cal.add(2, 3);

        //Assert
        assert(result==5);
    }

    @Test
    public void Test_DivideByZero_ThrowsException(){
        //Arrange
        Calculator cal = new Calculator();

        //int result = cal.divide(1,0);

        // () -> executable i.e an exception is thrown when we call divide function and we are
        // asking to check if arithmetic exception is thrown
        assertThrows(ArithmeticException.class, () -> cal.divide(1,0));
    }
}