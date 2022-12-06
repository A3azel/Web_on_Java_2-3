package another;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import security.SecurityHelperMethods;

public class HashingPasswordsTests {

    private static final String CORRECT_HASH = "WXfH+2Foy+89QMRWB0iPnaHHrlj0kLDPui1ZbeO/87w=$ZY6s2D3HiSJxm1W4oL/AHCq+9LRp5pY0M4Ic0d2KxAQ=";
    private static final String INCORRECT_HASH = "q5VFoGweFUoQ/p4GzIfaFT6oKzSQ7E+E23nYQwqeqNo=$4IM6ELiteT0slKm+dub6KTbPl/Nzmx8LjpFiNuCqPuw=";


    @Test
    public void hashPassword() throws Exception {
        Assertions.assertTrue(SecurityHelperMethods.check("testpassword",CORRECT_HASH));
    }

    @Test
    public void incorrectPassword() throws Exception {
        Assertions.assertFalse(SecurityHelperMethods.check("testpassword",INCORRECT_HASH));
    }

    @Test
    public void testNull() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> SecurityHelperMethods.check(null,INCORRECT_HASH));
    }
}
