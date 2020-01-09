package micro.repo.muserver.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.muserver.openapi.OpenAPIObjectBuilder;
import io.muserver.rest.RestHandlerBuilder;

import static io.muserver.openapi.InfoObjectBuilder.infoObject;
import static io.muserver.rest.CORSConfigBuilder.corsConfig;

public class ResourcesRepository {

    public static RestHandlerBuilder createRestHandler() {
        return RestHandlerBuilder
                .restHandler(new UserResource())
                .addResource(new HealthResource())
                .addCustomWriter(new JacksonJaxbJsonProvider())
                .addCustomReader(new JacksonJaxbJsonProvider())
                .withCORS(corsConfig().withAllowedOriginRegex(".*"))
                .withOpenApiHtmlUrl("/api.html")
                .withOpenApiJsonUrl("/openapi.json")
                .withOpenApiDocument(
                        OpenAPIObjectBuilder.openAPIObject()
                                .withInfo(
                                        infoObject()
                                                .withTitle("User API Documentation")
                                                .withDescription("This is just a demo API that doesn't actually work!")
                                                .withVersion("1.0")
                                                .build())
                );
    }

}
