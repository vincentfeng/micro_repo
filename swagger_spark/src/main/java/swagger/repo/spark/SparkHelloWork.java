package swagger.repo.spark;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import static spark.Spark.*;

public class SparkHelloWork {

    private final static Logger logger = LoggerFactory.getLogger(SparkHelloWork.class);

    public static void main(String[] args) {

        String fileName = "application-local.properties";
        Gson gson = new Gson();
        Config config = loadConfig(fileName);
        port(config.getPort());
        staticFiles.location("/static");
        get("/hello", (req, res) -> "Hello World");
        get("/health",(req,res)-> gson.toJson(loadHealthEntity(config)));

        System.out.println("http://localhost:"+port());
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
            logger.error("{}",e);
        }
        Config config = new Config();
        config.setVersion(properties.getProperty("server.version"));
        config.setPort(Integer.parseInt(properties.getProperty("server.port")));
        config.setContext(properties.getProperty("server.context"));
        config.setEnv(properties.getProperty("env"));
        return config;
    }
}