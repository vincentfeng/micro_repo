package micro.repo.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class Health {

    @Autowired
    private Config config;

    @ResponseBody
    @GetMapping("/health")
    public HealthEntity health(){
        HealthEntity entity = new HealthEntity();
        entity.setActive(true);
        entity.setVersion(config.getVersion());
        entity.setEnv(config.getEnv());
        return entity;
    }

    public class HealthEntity implements Serializable{
        private String version;

        private boolean isActive = false;

        private String env;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getEnv() {
            return env;
        }

        public void setEnv(String env) {
            this.env = env;
        }
    }
}
