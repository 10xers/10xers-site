package org.tenxers.site.core;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.tenxers.site.core.models.Password;
import testcats.FastTests;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 17:56
 */
@Category(FastTests.class)
public class PasswordMakerTest {

    @Test
    public void testMakeWithSalt() throws Exception {
        Password sample = PasswordMaker.make("helloworld");
        Password check = PasswordMaker.make("helloworld", sample.getSalt());
        assertEquals(sample, check);
    }


    @Test
    public void testMake() throws Exception {
        Password sample = PasswordMaker.make("plaintext");
        assertNotNull(sample);

        assertEquals(Password.HashType.SHA_256, sample.getHashType());
        assertEquals(40, sample.getSalt().length());
        assertEquals(64, sample.getHash().length());
        assertNotSame(sample.getHash(), "plaintext");

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte salt[] = hexStringToByteArray(sample.getSalt());
        assertEquals(20, salt.length);
        byte seed[] = "plaintext".getBytes(Charset.forName("UTF-8"));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(salt);
        stream.write(seed);

        byte[] toHash = stream.toByteArray();

        byte expectedDigest[] = md.digest(toHash);

        byte hashAsBytes[] = hexStringToByteArray(sample.getHash());

        assertEquals(expectedDigest.length, hashAsBytes.length);
        assertArrayEquals(expectedDigest, hashAsBytes);
    }

    private static byte[] hexStringToByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}