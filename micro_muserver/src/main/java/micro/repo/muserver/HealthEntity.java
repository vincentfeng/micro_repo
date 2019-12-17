package micro.repo.muserver;

import lombok.Data;

@Data
public class HealthEntity {

    private String version;

    private boolean isActive;

    private String startTime;

}
