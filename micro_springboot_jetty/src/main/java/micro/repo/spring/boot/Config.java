package micro.repo.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author vincent.fung
 */
@Configuration
public class Config implements Serializable{

    @Value("${server.version}")
    private String version;

    @Value("${server.env}")
    private String env;

    public Config() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}