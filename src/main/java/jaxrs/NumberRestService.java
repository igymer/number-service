package jaxrs;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.math.BigInteger;
import java.util.Arrays;

import static org.apache.commons.lang3.math.NumberUtils.*;

@Path("/number")
public class NumberRestService {

    private static final Logger logger = LoggerFactory.getLogger(NumberRestService.class);

    /**
     * Get sum of numbers
     * @param num1 first number
     * @param num2 second number
     * @return sum
     */
    @GET
    @Path("/add/{num1}/{num2}")
    public String add(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).add(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).add(createBigDecimal(num2))));
    }

    /**
     * Get difference of numbers
     * @param num1 first number
     * @param num2 second number
     * @return difference
     */
    @GET
    @Path("/subtract/{num1}/{num2}")
    public String subtract(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).subtract(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).subtract(createBigDecimal(num2))));
    }

    /**
     * Get product of numbers
     * @param num1 first number
     * @param num2 second number
     * @return product
     */
    @GET
    @Path("/multiply/{num1}/{num2}")
    public String multiply(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).multiply(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).multiply(createBigDecimal(num2))));
    }

    /**
     * Get quotient of numbers
     * @param num1 first number
     * @param num2 second number
     * @return quotient
     */
    @GET
    @Path("/divide/{num1}/{num2}")
    public String divide(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        if (isZero(num2)) {
            logger.warn("Division by zero is not allowed");
            throw new ArithmeticException("Division by zero is not allowed");
        }
        if (isConvertableToLong(num1, num2)) {
            BigInteger number1 = createBigInteger(num1);
            BigInteger number2 = createBigInteger(num2);
            if (number1.mod(number2).equals(BigInteger.ZERO)) {
                return String.valueOf(number1.divide(number2));
            }
        }
        return String.valueOf(createBigDecimal(num1).divide(createBigDecimal(num2)));
    }

    private void validateNumberFormat(String... nums) {
        Arrays.stream(nums).filter(num -> !NumberUtils.isCreatable(num)).forEach(num -> {
            logger.warn("Not a number");
            throw new NumberFormatException("Not a number");
        });
    }

    private boolean isConvertableToLong(String... nums) {
        return Arrays.stream(nums).filter(NumberUtils::isDigits).count() == nums.length;
    }

    private boolean isZero(String num) {
        return ("0".equals(num) || "0.0".equals(num));
    }
}
