package co.simplon.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Component
	public class CustomAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private AuthenticationProvider authProvider;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
	@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin().disable().csrf().disable().logout().disable();
	     //Pour retirer les filtres dont nous n'avons pas besoin
		http.authorizeRequests()
		.antMatchers("/anonymous/**").permitAll() // Pour permettre a un controller d'être visible sans authentiication
		.anyRequest().authenticated().and()
		.httpBasic().and()//http.option X le and permet le chainage d'options, avec le and on revient a une option générale
		.formLogin().disable()
		.csrf().disable()
		.logout().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
}

}
