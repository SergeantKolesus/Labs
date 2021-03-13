package Hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class MultTest {
    private Calculator calculator;

    @BeforeTest
    public void CreateTestCalculator()
    {
        calculator = new Calculator();
    }

    @Test
    public void LongMultTest()
    {
        for(long i = -25; i < 25; i++)
            for(long j = -25; j < 25; j++)
            {
                Assert.assertEquals(i * j, calculator.mult(i, j));
            }
    }

    @Test
    public void DoubleMultTest()
    {
        for(double i = -25; i < 25; i += 0.5)
            for(double j = -25; j < 25; j += 0.5)
            {
                Assert.assertEquals(Math.floor(i * j), calculator.mult(i, j));
            }
    }
}
