package server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().anyRequest().authenticated();

        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }

    /**
     * Bean for creating an password encoder.
     *
     * @return Returns an encrypted password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures a few (temporary) user accounts for authorization.
     *
     * @param auth Parameter for an authenticator
     * @throws Exception Throws exception  if the authenticator is invalid
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        String pass = "123";

        String encPass = this.passwordEncoder().encode(pass);

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> memoryConfig;
        memoryConfig = auth.inMemoryAuthentication();

        UserDetails u1 = User.withUsername("tom").password(encPass).roles("USER").build();
        UserDetails u2 = User.withUsername("jerry").password(encPass).roles("USER").build();

        memoryConfig.withUser(u1);
        memoryConfig.withUser(u2);
    }
}
