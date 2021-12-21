import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.DepositController;

class ValidateExpirationDate {

    private DepositController depositController;
    @BeforeEach
    void setUp() throws Exception {
        depositController = new DepositController();
    }

    @ParameterizedTest
    @CsvSource({
            "'12345', false",
            "'122a', false",
            "'123', false",
            "'', false",
            ", false",
            "'012ắ2', false",
            "'0123', true"
    })


    public void test(String date, boolean expected) {
        // when
        boolean isValid = depositController.validateExpirationDate(date);
        //then
        assertEquals(expected, isValid);
    }

}
