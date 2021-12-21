import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.DepositController;

class ValidateIssuingBank {

    private DepositController depositController;
    @BeforeEach
    void setUp() throws Exception {
        depositController = new DepositController();
    }

    @ParameterizedTest
    @CsvSource({
            ", false",
            "'', false",
            "'# Vietinbank', false",
            "'Vietinbank', true"
    })


    void test(String bank, boolean expected) {
        // when
        boolean isValid = depositController.validateIssuingBank(bank);
        //then
        assertEquals(expected, isValid);
    }

}