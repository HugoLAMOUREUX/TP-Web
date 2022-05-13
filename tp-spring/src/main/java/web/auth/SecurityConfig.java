package web.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			//.antMatchers("/api/**").permitAll()
				//en faisant ça, les routes vers /api/public sont autorisées mais plus celles vers api tout cours autre que public.
				//Il y a plus de permit all
				.antMatchers("/api/public/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsManager()).passwordEncoder(encoder());
	}
}
