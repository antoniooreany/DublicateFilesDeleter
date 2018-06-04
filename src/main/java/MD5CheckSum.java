import java.security.MessageDigest;

/**
 * Created by User on 01.06.2018.
 */
public class MD5CheckSum {
    public static String getHash(byte[] bytes) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] dataBytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
