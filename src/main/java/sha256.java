import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class sha256 {

    protected static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        //Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /*
            digest() method called
            to calculated message digest of an input
            and return array of byte
         */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    protected static HashMap<String, byte[]> toSaltedSHA(String input, byte[] salt) {
        String saltedHash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            saltedHash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error occurred while salting a hash, please try again: " + e);
        }
        //Using hashmap so that user of this script can store the salt in any way they wish.
        HashMap<String, byte[]> info = new HashMap<>();
        info.put(saltedHash, salt);
        return info;
    }

    protected static String toHexString(byte[] hash){
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        //Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String getHashedString(String input) {
        String hash = null;
        try {
            hash = toHexString(getSHA(input));
            return hash;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error while hashing input, try again: " + e);
        }
        return "HASHING ERR";
    }

    public static HashMap<String, byte[]> getSaltedHash(String input) {
        byte[] salt = null;
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error occurred while getting salt for hash, try again: " + e);
        }
        if (salt == null) {
            return null;
        } else {
            return toSaltedSHA(input, salt);
        }
    }
}
