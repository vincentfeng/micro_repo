package micro.repo.muserver;

import lombok.Data;

import java.io.Serializable;

@Data
public class Config implements Serializable {

    private String context;
    private int port;
    private String version;
    private String env;

}
