import org.junit.*;

public class getHashTest {
    // not a lot to test here, just using this to see what the output looks like

    @Test
    public void hashingTest() {
        String expected = "83A380E55F3145D306B7209F1E1AED4784DA1EFFA17D417AD7C2225FB70ACE8C";
        String actual = sha256.getHashedString("testingpersonalhasher");

        Assert.assertEquals(expected, actual.toUpperCase());
    }
}
