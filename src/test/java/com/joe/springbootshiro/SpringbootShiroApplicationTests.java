package com.joe.springbootshiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {

    @Test
    public void contextLoads() {
        String hashAlgorithnName="MD5";
        Object credentials = "1";
        Object salt = ByteSource.Util.bytes("1"+"张三");
        int hashIterations = 1;
        Object result = new SimpleHash(hashAlgorithnName,credentials,salt,hashIterations);
        System.out.println(result);
    }

}
