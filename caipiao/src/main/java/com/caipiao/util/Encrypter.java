package com.caipiao.util;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Encrypter {
private static final Logger LOG  = LoggerFactory.getLogger(Encrypter.class);
    
    private static byte[] salt = {
    	(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
    	(byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    
    private static int iterationCount = 19;
    private Cipher ecipher;
    private Cipher dcipher;

    public Encrypter() {
    	String passPhrase = "!@#$%^&*()_&^$%^$%$#@KJOIJ*&W&^T$%$#W@%*&(U)(JUOIJJIieohfiuehgtru";
    	try {
    		KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Failed to initialize cookie encoder", e);
        }
    }
    
    /**
     * 加密
     * @param plaintext
     * @return
     */
    public byte[] encrypt(byte[] plaintext) {
        try {
            return ecipher.doFinal(plaintext);
        } catch (Exception e) {
        	LOG.error("Failed to encrypt object" + ArrayUtils.toString(plaintext), e);
        }

        return null;
    }
    
    /**
     * 解密
     * @param cryptotext
     * @return
     */
    public byte[] decrypt(byte[] cryptotext) {
        try {
            return dcipher.doFinal(cryptotext);
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        }

        return null;
    }
}
