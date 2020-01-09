package micro.repo.muserver.rest;

import io.muserver.MuRequest;
import io.muserver.MuResponse;
import io.muserver.rest.ApiResponse;
import io.muserver.rest.Description;
import io.muserver.rest.Required;
import org.eclipse.jetty.client.HttpRequest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Path("/users")
@Description(value = "A human user", details = "Use this API to get and create users")
public class UserResource {

    private final static Logger logger = LoggerFactory.getLogger(UserResource.class);

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Description("Gets a single user")
    @ApiResponse(code = "200", message = "Success")
    @ApiResponse(code = "404", message = "No user with that ID found")
    public String get(
            @Description("The ID of the user")
            @PathParam("id") int id) {
        return new JSONObject()
                .put("id", id)
                .toString(4);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Description("Creates a new user")
    @ApiResponse(code = "201", message = "The user was created")
    @ApiResponse(code = "400", message = "The ID or name was not specified")
    public Response create(
//            @Description("A unique ID for the new user")
//            @Required @FormParam("id") int id,
//            @Description("The name of the user")
//            @FormParam("name") String name,
            MuRequest request) throws IOException {
        Optional<InputStream> inputStreamOption = request.inputStream();
        if (inputStreamOption.isPresent()) {
            InputStream inputStream = inputStreamOption.get();
            Properties p = new Properties();
            p.load(inputStream);
            System.out.println(p.getProperty("name"));
            // read the stream
        }
        return Response.status(201).build();
    }

}