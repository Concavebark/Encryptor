import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class testing {
    public static void main(String[] args) {
        String input = "sugman nutz lmoa gottem";
        HashMap<String, byte[]> map = sha256.getSaltedHash(input);
        String hash = null;
        byte[] salt = new byte[0];

        for (Map.Entry<String, byte[]> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            hash = entry.getKey();
            System.out.println(Arrays.toString(entry.getValue()));
            salt = entry.getValue();
        }
        File store = new File("log");
        try (PrintWriter pw = new PrintWriter(store)) {
            pw.println(hash + "\n" + Arrays.toString(salt));
        } catch (FileNotFoundException e) {
            System.err.println("Cry.");
        }
    }
}
