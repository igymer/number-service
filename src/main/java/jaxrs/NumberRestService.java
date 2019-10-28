package jaxrs;

import org.apache.commons.lang3.math.NumberUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.math.BigInteger;
import java.util.Arrays;

import static org.apache.commons.lang3.math.NumberUtils.*;

@Path("/number")
public class NumberRestService {

    @GET
    @Path("/add/{num1}/{num2}")
    public String add(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).add(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).add(createBigDecimal(num2))));
    }

    @GET
    @Path("/subtract/{num1}/{num2}")
    public String subtract(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).subtract(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).subtract(createBigDecimal(num2))));
    }

    @GET
    @Path("/multiply/{num1}/{num2}")
    public String multiply(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        return (isConvertableToLong(num1, num2) ?
                String.valueOf(createBigInteger(num1).multiply(createBigInteger(num2))) :
                String.valueOf(createBigDecimal(num1).multiply(createBigDecimal(num2))));
    }

    @GET
    @Path("/divide/{num1}/{num2}")
    public String divide(@PathParam("num1") String num1, @PathParam("num2") String num2) {
        validateNumberFormat(num1, num2);
        if (isZero(num2)) {
            throw new ArithmeticException();
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
            throw new NumberFormatException();
        });
    }

    private boolean isConvertableToLong(String... nums) {
        return Arrays.stream(nums).filter(NumberUtils::isDigits).count() == nums.length;
    }

    private boolean isZero(String num) {
        return ("0".equals(num) || "0.0".equals(num));
    }
}
