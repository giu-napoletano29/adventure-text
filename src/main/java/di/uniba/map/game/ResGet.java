package di.uniba.map.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ResGet {
    public static Properties getProp() throws InvalidPropertiesFormatException, IOException {
        Properties prop = new Properties();
        prop.loadFromXML(new FileInputStream("strings.xml"));

        return prop;
    }
}
