package peng.ziliang.soho;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import peng.ziliang.soho.repository.UserRepo;

@SpringBootTest
class SohoApplicationTests {

    @Autowired
    UserRepo userDao;

    @Test
    void contextLoads() {

        //System.out.println(userDao.queryCustom());
        //System.out.println(userDao.findById(100L));
        System.out.println(userDao.findUserByEmail("u1@t.com"));

    }

}
