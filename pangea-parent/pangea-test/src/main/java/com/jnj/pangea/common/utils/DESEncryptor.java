package com.jnj.pangea.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESEncryptor {

    private static final Logger logger = LoggerFactory.getLogger(DESEncryptor.class);
    private static final String DES = "DES";
    private static final String SECRETKEY = "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl";

    public DESEncryptor() {
    }

    public static String encrypt(String text) {
        return encrypt(text, "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl");
    }

    public static String decrypt(String encryptedText) {
        return decrypt(encryptedText, "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl");
    }

    public static String encrypt(String text, String key) {
        try {
            byte[] input = encrypt(text.getBytes(), key.getBytes());
            String secret = (new BASE64Encoder()).encode(input);
            return secret;
        } catch (Exception var4) {
            logger.error("Cannot encrypt", var4);
            return text;
        }
    }

    public static String decrypt(String text, String key) {
        if (text == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] input = decoder.decodeBuffer(text);
                byte[] secret = decrypt(input, key.getBytes());
                return new String(secret);
            } catch (Exception var5) {
                logger.error("Cannot decrypt", var5);
                return text;
            }
        }
    }

    private static byte[] encrypt(byte[] text, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec spec = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, secretKey, random);
        return cipher.doFinal(text);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec spec = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = secretKeyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, secretKey, random);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String text = "1qaz@WSX4";
        if (args.length > 0) {
            text = args[0];
        }

        System.err.println(encrypt(text, "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl"));
        System.err.println(decrypt(encrypt(text, "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl"), "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl"));
        String psw = "KGpeDNizItkA8SM4q+C7yA==";
        System.out.println(decrypt(psw, "LJOjdffjds*9jodfw3792jJJJNldsafasdfnsfeQEJOFdfcxdfewEOJRJJ=%&*25Bl"));
    }
}
