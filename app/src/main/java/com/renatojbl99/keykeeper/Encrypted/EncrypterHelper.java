package com.renatojbl99.keykeeper.Encrypted;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncrypterHelper {

    public String encrypt(String data, String password) throws Exception{
        SecretKeySpec secretKeySpec = generateKey(password);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedDataOnBytes = cipher.doFinal(data.getBytes());
        String encryptedDataOnString = Base64.encodeToString(encryptedDataOnBytes, Base64.DEFAULT);
        return encryptedDataOnString;

    }

    private SecretKeySpec generateKey(String password) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("Sha-256");
        byte[] key = password.getBytes("UTF-8");
        key = sha.digest(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

     public String decrypt(String data, String password) throws Exception{
         SecretKeySpec secretKeySpec = generateKey(password);
         Cipher cipher = Cipher.getInstance("AES");
         cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
         byte[] decryptedData = Base64.decode(data, Base64.DEFAULT);
         byte[] decryptedDataOnByte = cipher.doFinal(decryptedData);
         String decryptedDataOnString = new String(decryptedDataOnByte);
         return decryptedDataOnString;
     }
}
