package micro.repo.spring.boot;

import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={JettyApp.class})// 指定启动类
public class HealthTest {

    private Logger logger = LoggerFactory.getLogger(HealthTest.class);

    @Autowired
    private Health health;
    @Test
    public void helloWorldTest(){
        System.out.println(health.health().getVersion());
    }
}
