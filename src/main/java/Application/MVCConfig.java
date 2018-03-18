package Application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter
{
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    public void addViewControllers(ViewControllerRegistry reg)
    {
        //login
        reg.addViewController("/login").setViewName("/login");
        reg.addViewController("/").setViewName("/login");

        //pass
        reg.addViewController("/pass").setViewName("/pass");
        reg.addViewController("/denied").setViewName("/denied");

        //error
        reg.addViewController("/error").setViewName("/error");

        //meniu-done
        reg.addViewController("/index").setViewName("/index");


        //user-done
        reg.addViewController("/register").setViewName("/register");
        reg.addViewController("/updateUser").setViewName("/updateUser");
        reg.addViewController("/deleteUser").setViewName("/deleteUser");
        reg.addViewController("/viewUser").setViewName("/viewUser");


        reg.addViewController("/addRent").setViewName("/addRent");
        reg.addViewController("/updateRent").setViewName("/updateRent");
        reg.addViewController("/viewRent").setViewName("/viewRent");
        reg.addViewController("/delRent").setViewName("/delRent");


        reg.addViewController("/deleteCar").setViewName("/deleteCar");
        reg.addViewController("/addCar").setViewName("/addCar");
        reg.addViewController("/viewCar").setViewName("/viewCar");
        reg.addViewController("/updateCar").setViewName("/updateCar");


        reg.addViewController("/searchM").setViewName("/searchM");
        reg.addViewController("/searchP").setViewName("/searchP");
        reg.addViewController("/tabel").setViewName("/tabel");
        reg.addViewController("/tabel2").setViewName("/tabel2");
    }
}