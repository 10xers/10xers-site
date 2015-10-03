package org.tenxers.site.core;

import org.tenxers.site.core.models.Password;

import javax.xml.bind.DatatypeConverter;
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
        return DatatypeConverter.printHexBinary(bytes);
    }


    private static byte[] convertHexToBytes(String hex)
    {
        return DatatypeConverter.parseHexBinary(hex);
    }

    public static Password make(String plainText)
    {
        return make(plainText, convertBytesToHex(generateRandomSalt()));
    }

    public static Password make(String plainText, String salt) {

        MessageDigest md;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("cannot get instance of sha-256 digest creator");
        }

        byte plainTextBytes[] = plainText.getBytes(Charset.forName("UTF-8"));
        byte saltBytes[] = convertHexToBytes(salt);

        byte digestBytes[] = new byte[plainTextBytes.length + saltBytes.length];

        System.arraycopy(saltBytes, 0, digestBytes, 0, saltBytes.length);
        System.arraycopy(plainTextBytes, 0, digestBytes, saltBytes.length, plainTextBytes.length);

        byte hashedPasswordBytes[] = md.digest(digestBytes);
        String hashedPassword = convertBytesToHex(hashedPasswordBytes);

        return new Password(salt, hashedPassword, Password.HashType.SHA_256);
    }
}
