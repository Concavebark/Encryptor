import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class getSaltedHashTest {

    //this is not testable, but we will use this to check outputs for now.
    @Test
    public void saltedHashTest() {
        String input = "testingtestingonetwothree";

        String hashNoSalt = "";
        hashNoSalt = sha256.getHashedString(input);
        HashMap<String, byte[]> actual = sha256.getSaltedHash(input);

        assert actual != null;
        for (Map.Entry<String, byte[]> entry: actual.entrySet()) {
            System.out.println("\nHash w/o Salt: " + hashNoSalt);
            System.out.println("Salted Hash: " + entry.getKey());
            System.out.println("Salt: " + Arrays.toString(entry.getValue()));
        }
    }
}
