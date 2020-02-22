package swagger.repo.spark;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webjars.WebJarAssetLocator;

import java.io.*;
import java.sql.Date;
import java.util.Properties;

import static spark.Spark.*;

public class SparkHelloWork2 {

    private final static Logger logger = LoggerFactory.getLogger(SparkHelloWork2.class);

    public static void main(String[] args) throws IOException {

        String fileName = "application-local.properties";
        Gson gson = new Gson();
        Config config = loadConfig(fileName);
        port(config.getPort());
        //webjars/swagger-ui/3.24.3
        //META-INF/resources/webjars/swagger-ui/3.24.3/index.html
//        InputStream is = ClassLoader.getSystemResourceAsStream("webjars/swagger-ui/3.24.3/index.html");
//        new BufferedReader(new InputStreamReader(is)).lines().forEach(line -> {
//            System.out.println(line);
//        });
//        staticFiles.location("/test");
        staticFiles.location("/META-INF/resources/webjars/swagger-ui/3.24.3");
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