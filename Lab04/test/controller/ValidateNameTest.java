package controller;

import static org.junit.jupiter.api.Assertions.*;
import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateNameTest {
    private PlaceOrderController placeOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeOrderController = new PlaceOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Long,true",
            "Duc Long01,false",
            ",false",
            "Duc Long %,false",
            "12243&,false",
            "11111,false",
            "%$^%^%^,false"
    }
    )
    @Test
    public void test(String name, boolean expected) {
        boolean isValid = placeOrderController.validateName(name);
        assertEquals(expected,isValid);
    }
}