package jaxrs;

import org.junit.*;
import org.mockito.*;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class NumberRestServiceTest {

    @Spy
    private NumberRestService service;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void addIntValues() {
        String result = service.add("1", "2");
        assertEquals("3", result);
    }

    @Test
    public void addDifferentValues() {
        String result = service.add("1", "2.72");
        assertEquals("3.72", result);
    }

    @Test
    public void addBothFractValues() {
        String result = service.add("7.2", "3.14");
        assertEquals("10.34", result);
    }

    @Test(expected = NumberFormatException.class)
    public void addNonNumberValues() {
        service.add("7.2", "Pi");
    }

    @Test
    public void subtractIntValues() {
        String result = service.subtract("7", "12");
        assertEquals("-5", result);
    }

    @Test
    public void subtractDifferentValues() {
        String result = service.subtract("4.5", "3");
        assertEquals("1.5", result);
    }

    @Test
    public void subtractBothFractValues() {
        String result = service.subtract("7.3", "2.2");
        assertEquals("5.1", result);
    }

    @Test(expected = NumberFormatException.class)
    public void subtractNonNumberValues() {
        service.subtract("e", "f");
    }

    @Test
    public void multiplyIntValues() {
        String result = service.multiply("3", "5");
        assertEquals("15", result);
    }

    @Test
    public void multiplyDifferentValues() {
        String result = service.multiply("3.1", "5");
        assertEquals("15.5", result);
    }

    @Test
    public void multiplyBothFractValues() {
        String result = service.multiply("1.4", "1.4");
        assertEquals("1.96", result);
    }

    @Test(expected = NumberFormatException.class)
    public void multiplyNonNumberValues() {
        service.multiply("N", "M");
    }

    @Test
    public void divideAllIntValues() {
        String result = service.divide("8", "2");
        assertEquals("4", result);
    }

    @Test
    public void divideIntWithMod() {
        String result = service.divide("8", "5");
        assertEquals("1.6", result);
    }

    @Test
    public void divideFractValues() {
        String result = service.divide("10", "1.6");
        assertEquals("6.25", result);
    }

    @Test(expected = NumberFormatException.class)
    public void divideNonNumberValues() {
        service.divide("8e", "2");
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        service.divide("8", "0");
    }

}