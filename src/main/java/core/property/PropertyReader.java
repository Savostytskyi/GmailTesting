package core.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type File path handler.
 * @author Anton_Savostytskyi on 16.06.2015.
 *         Class PropertyReader responsible for getting properies
 *         from "gmail.properties" file.
 */

public class PropertyReader {

    public static final String TEST_PROPERTY_FILE = "gmail.properties";
    private static Properties properties;

    private static Properties getInstance() {
        properties = new Properties();
        InputStream is;
        try {
            is = new FileInputStream(TEST_PROPERTY_FILE);
            properties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String property) {
        properties = getInstance();
        return properties.getProperty(property);
    }
}
