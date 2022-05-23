package com.choo.blog.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerifyCodeUtil {
    public static String generateToken(){
        Random random = new Random();
        String key = "";
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(25) + 65;
            key += (char) index;
        }
        int numIndex = random.nextInt(9999) + 1000;
        key += numIndex;
        return key;
    }
}
