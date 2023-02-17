package utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
    public static Properties properties = new Properties();

    public static String getProperty(String value) {
        try {
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(value);
    }
}
