package Hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class SqrtTest {
    private Calculator calculator;

    @BeforeTest
    public void CreateTestCalculator()
    {
        calculator = new Calculator();
    }


    @Test
    public void SqrtTest()
    {
        for(double i = -25; i < 25; i += 0.5)
            Assert.assertEquals(Math.sqrt(Math.abs(i)), calculator.sqrt(i));
    }
}
