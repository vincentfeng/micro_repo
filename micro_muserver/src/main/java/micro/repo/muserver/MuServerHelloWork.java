package micro.repo.muserver;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import com.google.gson.Gson;
import io.muserver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MuServerHelloWork {

    private final static Logger logger = LoggerFactory.getLogger(MuServerHelloWork.class);

    public static void main(String[] args) {
        logger.info("Server is starting");
        String fileName = "application-local.properties";
        Gson gson = new Gson();
        final Config config = loadConfig(fileName);
        MuServer server = MuServerBuilder.httpServer()
                .addHandler(Method.GET, "/hello", (request, response, pathParams) -> {
                    response.write("Hello, world");
                })
                .addHandler(Method.GET, "/health", (request, response, pathParams) -> {
                    response.write(gson.toJson(loadHealthEntity(config)));
                })
                .withHttpPort(config.getPort())
                .start();
        logger.info("Started server at {}" , server.uri());
    }

    public static HealthEntity loadHealthEntity(Config config){
        HealthEntity entity = new HealthEntity();
        entity.setActive(true);
        entity.setVersion(config.getVersion());
        entity.setStartTime(new Date(System.currentTimeMillis()).toString());
        return entity;
    }

    public static Config loadConfig(String fileName){
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
