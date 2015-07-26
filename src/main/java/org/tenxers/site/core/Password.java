package org.tenxers.site.core;

/**
 * site / Ed
 * 26/07/2015 17:20
 */
public class Password {

    public static enum HashType {
        SHA_256
    }

    private String salt;
    private String hash;
    private HashType type;

    public Password(String salt, String hash, HashType hashType) {
        setSalt(salt);
        setHash(hash);
        setType(hashType);
    }

    public final void setType(HashType type) {
        if (type==null)
            throw new IllegalArgumentException("Hash type cannot be null");

        this.type = type;
    }

    public final void setHash(String hash) {
        if (hash==null)
            throw new IllegalArgumentException("Hash cannot be null");

        if (hash.isEmpty())
            throw new IllegalArgumentException("Hash cannot be empty");

        this.hash = hash;
    }

    private final void setSalt(String salt)
    {
        if (salt==null)
            throw new IllegalArgumentException("Salt cannot be null");

        if (salt.isEmpty())
            throw new IllegalArgumentException("Salt cannot be empty");

        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public HashType getHashType() {
        return type;
    }
}
