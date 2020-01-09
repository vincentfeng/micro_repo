package micro.repo.muserver;

import com.google.gson.Gson;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.openapi.OpenAPIObjectBuilder;
import io.muserver.rest.RestHandlerBuilder;
import micro.repo.muserver.config.Config;
import micro.repo.muserver.rest.HealthResource;
import micro.repo.muserver.rest.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.muserver.openapi.InfoObjectBuilder.infoObject;
import static io.muserver.rest.CORSConfigBuilder.corsConfig;
import static micro.repo.muserver.config.Config.loadConfig;
import static micro.repo.muserver.rest.ResourcesRepository.createRestHandler;

public class MuServerHelloWork {

    private final static Logger logger = LoggerFactory.getLogger(MuServerHelloWork.class);

    public static void main(String[] args) {
        logger.info("Server is starting");
        String fileName = "application-local.properties";
        Gson gson = new Gson();
        final Config config = loadConfig(fileName);
        MuServer server = MuServerBuilder.httpServer()
                .addHandler(createRestHandler())
                .withHttpPort(config.getPort())
                .start();
        logger.info("Started server at {}", server.uri());
        logger.info("API HTML: {}" , server.uri().resolve("/api.html"));
        logger.info("API JSON: {}" , server.uri().resolve("/openapi.json"));
    }
}
