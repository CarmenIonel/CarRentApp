package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Date;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Main extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Main.class);
    }
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}