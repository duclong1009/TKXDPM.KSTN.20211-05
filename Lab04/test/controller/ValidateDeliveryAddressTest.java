package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateDeliveryAddressTest {
    private PlaceRushOrderController placeRushOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeRushOrderController = new PlaceRushOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "Hà Nội,true",
            "0,false",
            ",false",
            "1e,false",
    }
    )
    @Test
    public void test(String address, boolean expected) {
        boolean isValid = placeRushOrderController.validateDeliveryAddress(address);
        assertEquals(expected,isValid);
    }
}