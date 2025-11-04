package com.itbaizhan;

import com.itbaizhan.util.MailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TravelApplicationTests {

    @Autowired
    private MailUtils mailUtils;

    @Test
    void contextLoads() {
        mailUtils.sendMail("3113942885@qq.com","这是一封测试邮件","测试");
    }

}
