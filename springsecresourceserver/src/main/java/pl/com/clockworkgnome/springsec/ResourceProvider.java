package pl.com.clockworkgnome.springsec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ResourceProvider {

    @RequestMapping("/")
    @CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with"})
    public Message home() {
        return new Message("Hello World");
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceProvider.class, args);
    }
}
