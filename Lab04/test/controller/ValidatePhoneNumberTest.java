package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidatePhoneNumberTest {
	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"0981598568,true",
			"9815985658,false",
			",false",
			"0976454,false",
			"654645,false"
			}
	)
	@Test
	public void test(String phone, boolean expected) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		assertEquals(expected,isValid);
	}

}
