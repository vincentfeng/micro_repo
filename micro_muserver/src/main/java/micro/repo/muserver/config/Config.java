package micro.repo.muserver.config;

import lombok.Data;
import micro.repo.muserver.utils.PropertiesUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

@Data
public class Config implements Serializable {

    private String context;
    private int port;
    private String version;
    private String env;

    public static Config loadConfig(String fileName) {
        Properties properties = null;
        try {
            properties = PropertiesUtils.load(ClassLoader.getSystemResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Config config = new Config();
        config.setVersion(properties.getProperty("server.version"));
        config.setPort(Integer.parseInt(properties.getProperty("server.port")));
        config.setContext(properties.getProperty("server.context"));
        config.setEnv(properties.getProperty("env"));
        return config;
    }
}
