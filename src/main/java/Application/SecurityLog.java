package Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityLog extends WebSecurityConfigurerAdapter
{

    @Autowired
    private UserService userData;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf().disable();
      //  httpSecurity.cors().disable();
        httpSecurity.authorizeRequests().antMatchers("/","/register","/resources/**","/templates/**", "/static/**",
                "/css/**").permitAll()
                .antMatchers("/addCar","/deleteCar","/updateCar","/getReport").hasRole("ADMIN")
                .antMatchers("/addRent","/addReview","/deleteUser","/delRent","/updateRent","/viewRent").hasRole("USER")
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/index")
                .loginPage("/login").usernameParameter("user").passwordParameter("pass").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/denied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception
    {
        auth.userDetailsService(this.userData);
    }

}