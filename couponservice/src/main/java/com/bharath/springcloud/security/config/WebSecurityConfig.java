package com.bharath.springcloud.security.config;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bharath.springcloud.security.UserDetailsServiceimpl;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceimpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests() .mvcMatchers(HttpMethod.GET,
		 * "/couponapi/coupons/{code:^[A-Z]*$}", "/index", "/showGetCoupon",
		 * "/getCoupon", "/couponDetails") .hasAnyRole("USER", "ADMIN")
		 * .mvcMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon",
		 * "/createResponse").hasRole("ADMIN") .mvcMatchers(HttpMethod.POST,
		 * "/getCoupon").hasAnyRole("USER", "ADMIN") .mvcMatchers(HttpMethod.POST,
		 * "/couponapi/coupons", "/saveCoupon", "/getCoupon").hasRole("ADMIN")
		 * .mvcMatchers("/", "/login", "/logout", "/showReg",
		 * "/registerUser").permitAll().anyRequest().denyAll()
		 * .and().csrf().disable().logout().logoutSuccessUrl("/");
		 */
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
