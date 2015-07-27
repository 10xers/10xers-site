package org.tenxers.site.core.models;

/**
 * site / Ed
 * 26/07/2015 17:20
 */
public class Password {

    public enum HashType {
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

    private void setType(HashType type) {
        if (type==null)
            throw new IllegalArgumentException("Hash type cannot be null");

        this.type = type;
    }

    private void setHash(String hash) {
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
