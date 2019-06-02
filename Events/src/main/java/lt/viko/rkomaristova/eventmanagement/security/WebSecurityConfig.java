package lt.viko.rkomaristova.eventmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.viko.rkomaristova.eventmanagement.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	public UserService userService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthentificationSuccessHandler successHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/events").hasAuthority("PARTICIPANT")
		.antMatchers("/tickets").hasAuthority("PARTICIPANT")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.formLogin()
		.successHandler(successHandler)
		//.defaultSuccessUrl("/events", true)
		.and()
        .logout()
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/events");
		//.loginProcessingUrl("/users/login")
		//.loginPage("/index.html");
	}
}
