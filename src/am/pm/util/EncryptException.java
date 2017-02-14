package am.pm.util;

/**
 * Created by Artur on 4/2/2016.
 */
public class EncryptException extends Exception {

    public EncryptException() {
        super();
    }


    public EncryptException(String message) {
        super(message);
    }


    public EncryptException(String message, Throwable cause) {
        super(message, cause);
    }


    public EncryptException(Throwable cause) {
        super(cause);
    }
}
