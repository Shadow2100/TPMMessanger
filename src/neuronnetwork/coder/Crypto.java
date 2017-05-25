package neuronnetwork.coder;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

    public static String encrypt(String type, String value, String stringKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec;
        byte[] key;
        if (type.equalsIgnoreCase("DESede/CBC/PKCS5Padding")) {
            key = Arrays.copyOf(stringKey.getBytes(), 24);
            secretKeySpec = new SecretKeySpec(key, "DESede");
        } else if (type.equalsIgnoreCase("Blowfish/CBC/PKCS5Padding")) {
            key = Arrays.copyOf(stringKey.getBytes(), 16);
            secretKeySpec = new SecretKeySpec(key, "Blowfish");
        } else {
            key = Arrays.copyOf(stringKey.getBytes(), 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        }

        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        byte[] encryptedText = cipher.doFinal(value.getBytes());
        byte[] iv = cipher.getIV();
        byte[] encrypted = new byte[iv.length + encryptedText.length];
        System.arraycopy(iv, 0, encrypted, 0, iv.length);
        System.arraycopy(encryptedText, 0, encrypted, iv.length, encryptedText.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String type, String encodedEncryptedTextWithIV, String stringKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        int ivLength;
        SecretKeySpec secretKeySpec;
        byte[] key;
        if (type.equalsIgnoreCase("DESede/CBC/PKCS5Padding")) {
            key = Arrays.copyOf(stringKey.getBytes(), 24);
            secretKeySpec = new SecretKeySpec(key, "DESede");
            ivLength = 8;
        } else if (type.equalsIgnoreCase("Blowfish/CBC/PKCS5Padding")) {
            key = Arrays.copyOf(stringKey.getBytes(), 16);
            ivLength = 8;
            secretKeySpec = new SecretKeySpec(key, "Blowfish");
        } else {
            key = Arrays.copyOf(stringKey.getBytes(), 16);
            ivLength = 16;
            secretKeySpec = new SecretKeySpec(key, "AES");
        }

        byte[] encryptedTextWithIV = Base64.getDecoder().decode(encodedEncryptedTextWithIV);
        byte[] initVector = Arrays.copyOf(encryptedTextWithIV, ivLength);
        byte[] encryptedText = Arrays.copyOfRange(encryptedTextWithIV, ivLength, encryptedTextWithIV.length);
        IvParameterSpec iv = new IvParameterSpec(initVector);
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] original = cipher.doFinal(encryptedText);

        return new String(original);
    }

    public static byte[] sha256Hash(byte[] string) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");

            md.update(string); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            return digest;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] sha256HashSalted(byte[] data) {
        try {
            Random RANDOM = new SecureRandom();
            byte[] salt = new byte[16];
            RANDOM.nextBytes(salt);
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] saltWithData = new byte[salt.length + data.length];
            System.arraycopy(salt, 0, saltWithData, 0, salt.length);
            System.arraycopy(data, 0, saltWithData, salt.length, data.length);
            md.update(saltWithData); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            byte[] saltWithDigest = new byte[salt.length + digest.length];
            System.arraycopy(salt, 0, saltWithDigest, 0, salt.length);
            System.arraycopy(digest, 0, saltWithDigest, salt.length, digest.length);
            return saltWithDigest;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] sha256HashWithCustomSalt(byte[] salt, byte[] data) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] saltWithData = new byte[salt.length + data.length];
            System.arraycopy(salt, 0, saltWithData, 0, salt.length);
            System.arraycopy(data, 0, saltWithData, salt.length, data.length);
            md.update(saltWithData); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            return digest;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean sha256EqualCheck(byte[] data, byte[] saltWithDigest) {
        try {
            byte[] salt = Arrays.copyOf(saltWithDigest, 16);
            byte[] digestForCheck = Arrays.copyOfRange(saltWithDigest, 16, saltWithDigest.length);
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] saltWithData = new byte[salt.length + data.length];
            System.arraycopy(salt, 0, saltWithData, 0, salt.length);
            System.arraycopy(data, 0, saltWithData, salt.length, data.length);
            md.update(saltWithData); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();

            return Arrays.equals(digest, digestForCheck);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
