package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidateRushDeliveryDayTest {
    private PlaceRushOrderController placeRushOrderController;
    @BeforeEach
    void setUp() throws Exception {
        placeRushOrderController = new PlaceRushOrderController();
    }

    @ParameterizedTest
    @CsvSource({
            "20,true",
            "0,false",
            ",false",
            "1e,false",
    }
    )
    @Test
    public void test(String rushDeliveryDay, boolean expected) {
        boolean isValid = placeRushOrderController.validateRushDeliveryDay(rushDeliveryDay);
        assertEquals(expected,isValid);
    }
}