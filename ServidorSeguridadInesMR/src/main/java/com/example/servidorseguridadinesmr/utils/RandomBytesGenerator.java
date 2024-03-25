package com.example.servidorseguridadinesmr.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomBytesGenerator {

    public String randomBytes() {
        SecureRandom sr = new SecureRandom();
        byte[] bits = new byte[32];
        sr.nextBytes(bits);
        return Base64.getUrlEncoder().encodeToString(bits);
    }

    public String randomNewPassword() {
        SecureRandom sr = new SecureRandom();
        byte[] bits = new byte[8];
        sr.nextBytes(bits);
        return Base64.getUrlEncoder().encodeToString(bits);
    }
}
