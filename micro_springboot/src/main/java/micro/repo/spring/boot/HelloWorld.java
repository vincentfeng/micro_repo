package micro.repo.spring.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @ResponseBody
    @GetMapping(value = "/helloWorld",params = "name")
    public String helloWorld(String name){
        return "hello "+name;
    }

}
