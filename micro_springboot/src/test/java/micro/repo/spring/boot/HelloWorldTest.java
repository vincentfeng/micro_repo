package micro.repo.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={App.class})// 指定启动类
public class HelloWorldTest {

    @Autowired
    private HelloWorld helloWorld;
    @Test
    public void helloWorldTest(){
        System.out.println(helloWorld.helloWorld("vincent"));
    }
}
