package micro.repo.muserver.rest;

import com.google.gson.Gson;
import io.muserver.rest.ApiResponse;
import io.muserver.rest.Description;
import micro.repo.muserver.config.Config;
import micro.repo.muserver.dto.HealthDTO;
import micro.repo.muserver.utils.PropertiesUtils;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import static micro.repo.muserver.config.Config.loadConfig;

@Path("/health")
@Description(value = "Health check", details = "Health check")
public class HealthResource {

    @GET
    @Produces("application/json")
    @Description("Health check")
    @ApiResponse(code = "200", message = "Success")
    @ApiResponse(code = "404", message = "No found")
    public String get() {
        String fileName = "application-local.properties";
        Gson gson = new Gson();
        final Config config = loadConfig(fileName);
        return gson.toJson(loadHealthEntity(config));
    }

    HealthDTO loadHealthEntity(Config config) {
        HealthDTO entity = new HealthDTO();
        entity.setActive(true);
        entity.setVersion(config.getVersion());
        entity.setStartTime(new Date(System.currentTimeMillis()).toString());
        return entity;
    }


}
