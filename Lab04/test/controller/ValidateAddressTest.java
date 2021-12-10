package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
class ValidateAddressTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Ha Noi,true",
            "so 1 Ha Noi,true",
            ",false",
            "so 1  %,false",
            "Ha noi &,false",
            "11111,true",
            "%$^%^%^,false"
    }
    )
    @Test
    public void test(String address, boolean expected) {
        boolean isValid = placeOrderController.validateAddress(address);
        assertEquals(expected,isValid);
    }
}