import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.DepositController;

class ValidateSecurityCode {

    private DepositController depositController;
    @BeforeEach
    void setUp() throws Exception {
        depositController = new DepositController();
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "'123jkf4354', false",
            "'# rrew34gr', false",
            "'BUmOFfT3E2Q=', true"
    })


    void test(String code, boolean expected) {
        // when
        boolean isValid = depositController.validateSecurityCode(code);
        //then
        assertEquals(expected, isValid);
    }

}