package com.itbaizhan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TravelApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword); // 生成加密后的密码
        System.out.println("加密后：" + encodedPassword);

        // 验证（返回 true）
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("验证结果：" + matches);
    }

}
