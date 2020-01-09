package micro.repo.muserver.dto;

import lombok.Data;

@Data
public class HealthDTO {

    private String version;

    private boolean isActive;

    private String startTime;

}
