import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.DepositController;

public class ValidateCardHolderName {

    private DepositController depositController;
    @BeforeEach
    void setUp() throws Exception {
        depositController = new DepositController();
    }

    @ParameterizedTest
    @CsvSource({
            "'', false",
            ", false",
            "'Doan Ngoc Kh@nh', false",
            "'3 Khanh', false",
            "'Doan Ngoc Khanh', true",
            "'Đoàn Ngọc Khánh', false"
    })


    public void test(String name, boolean expected) {
        // when
        boolean isValid = depositController.validateCardHolderName(name);
        //then
        assertEquals(expected, isValid);
    }

}
