package clientside.model;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final int DEFAULT_PORT = 8000;
    private static final String DEFAULT_ADDRESS = "127.0.0.1";

    public static Properties readProperties(String fileName) {
        FileInputStream propertiesFileStream = null;
        Properties prop = null;

        try {
            propertiesFileStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(propertiesFileStream);
            propertiesFileStream.close();
        }
        catch (Exception e) {
            prop = new Properties();
            prop.setProperty("address", DEFAULT_ADDRESS);
            prop.setProperty("port", String.valueOf(DEFAULT_PORT));
        }

        return prop;
    }
}
