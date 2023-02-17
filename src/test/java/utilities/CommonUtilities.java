package utilities;

import org.testng.log4testng.Logger;

import java.util.Base64;

public class CommonUtilities {
    static Logger logger = Logger.getLogger(CommonUtilities.class);

    public CommonUtilities() {

    }

    public static String decrypt(String encryptString) {
        Base64.Decoder decoder = null;
        try {
            decoder = Base64.getDecoder();

        } catch (Exception e) {

        }
        return new String(decoder.decode(encryptString));
    }

}
