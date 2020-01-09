package micro.repo.muserver.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    public static Properties load(String path) throws IOException {
        try(InputStream is = new FileInputStream(path)){
            return load(is);
        }catch (IOException e){
            throw e;
        }
    }

    public static Properties load(InputStream is) throws IOException {
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }
}
