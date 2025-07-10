package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {
	private static Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
