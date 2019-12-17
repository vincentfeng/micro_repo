package micro.repo.spark;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import static spark.Spark.*;

public class SparkHelloWork {
    public static void main(String[] args) {

        String fileName = "application-local.properties";
        Gson gson = new Gson();
        Config config = loadConfig(fileName);
        port(config.getPort());
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