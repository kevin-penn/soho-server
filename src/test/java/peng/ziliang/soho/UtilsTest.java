package peng.ziliang.soho;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilsTest {

    @Test
    public void testBCryptPasswordEncoder() {

        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        String result = cryptPasswordEncoder.encode("123456");
        System.out.printf(result);

    }
}
