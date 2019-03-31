package server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * Configures the authentication routes
     * All routes Besides the routes listed require full basic authentication
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http)
        throws Exception {
        http.headers().frameOptions().disable();

        http.authorizeRequests()
            //Example whitelist route
            .antMatchers(GET, "/").permitAll()
            .antMatchers(POST, "/").permitAll()
            .antMatchers(POST, "/auth/register").permitAll()
            .anyRequest().fullyAuthenticated()
            .and().httpBasic()
            .and().csrf().disable();

    }


    /**
     * Specifies to use a custom authentication provider in order to authenticate for users in the database
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Specifies the authentication provider and the userDetailsService to handle the authentication
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    /**
     * Specifies the encryption to be used in the authentication provider.
     * @return
     */
    @Bean
    PasswordEncoder encoder() {
        return  new BCryptPasswordEncoder(11);
    }
}
