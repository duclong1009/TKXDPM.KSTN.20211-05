import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.BarcodeController;

class ValidateBarcode {

    private BarcodeController barcodeController;
    @BeforeEach
    void setUp() throws Exception {
        barcodeController = new BarcodeController();
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "'', false",
            "'# rrew34gr', false",
            "'BUmOFfT3E2Q-123', true"
    })


    void test(String code, boolean expected) {
        // when
        boolean isValid = barcodeController.validateBarcode(code);
        //then
        assertEquals(expected, isValid);
    }

}