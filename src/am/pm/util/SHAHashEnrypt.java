package am.pm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Artur on 4/2/2016.
 */
public class SHAHashEnrypt {
    public static String getMD5SecurePassword(String passwordToHash) throws EncryptException {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(passwordToHash.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(e);
        }


    }

}
