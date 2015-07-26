package org.tenxers.site.core;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * site / Ed
 * 26/07/2015 17:55
 */
public class PasswordMaker {

    private static byte[] generateRandomSalt() {
        byte[] saltBytes = new byte[20];
        new SecureRandom().nextBytes(saltBytes);

        return saltBytes;
    }

    private static String convertBytesToHex(byte[] bytes)
    {
        StringBuilder builder = new StringBuilder();

        for (byte b : bytes)
            builder.append(String.format("%02x", b));

        return builder.toString();
    }

    public static Password make(String plainText) {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("cannot get instance of sha-256 digest creator");
        }

        byte[] saltBytes = generateRandomSalt();
        String salt = convertBytesToHex(saltBytes);

        byte plainTextBytes[] = plainText.getBytes(Charset.forName("UTF-8"));


        byte digestBytes[] = new byte[plainTextBytes.length + saltBytes.length];

        System.arraycopy(saltBytes, 0, digestBytes, 0, saltBytes.length);
        System.arraycopy(plainTextBytes, 0, digestBytes, saltBytes.length, plainTextBytes.length);

        byte hashedPasswordBytes[] = md.digest(digestBytes);
        String hashedPassword = convertBytesToHex(hashedPasswordBytes);

        return new Password(salt, hashedPassword, Password.HashType.SHA_256);
    }
}
