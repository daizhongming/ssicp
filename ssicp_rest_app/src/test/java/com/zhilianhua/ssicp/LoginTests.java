package com.zhilianhua.ssicp;

import com.zhilianhua.ssicp.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

@Slf4j
public class LoginTests {
    @Test
    public void test(){
        String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
        log.info(hashpw);
    }
    @Test
    public void testJwt() {
//        String token = JwtUtils.generateToken("root");
//        log.debug("token\n{}", token);
//        JwtUtils.validateToken(token);
//        String userName = JwtUtils.getUserName(token);
//        log.debug("name\n{}", userName);
    }
}
