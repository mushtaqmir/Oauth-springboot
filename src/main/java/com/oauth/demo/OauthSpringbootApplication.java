package com.oauth.demo;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//extends WebSecurityConfigurerAdapter
@SpringBootApplication
@RestController
public class OauthSpringbootApplication {

//	@GetMapping("/user")
//	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//		return Collections.singletonMap("name", principal.getAttribute("name"));
//	}
	@GetMapping("/")
	public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		model.addAttribute("userAttributes", oauth2User.getAttributes());
		return oauth2User.getAttribute("name");
	}
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	    	// @formatter:off
//	        http
//	            .authorizeRequests(a -> a
//	                .antMatchers("/", "/error", "/webjars/**").permitAll()
//	                .anyRequest().authenticated()
//	            ).logout(l -> l
//	                    .logoutSuccessUrl("/").permitAll()
//	                    ).csrf(c -> c
//	                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//	                            )
//	            .exceptionHandling(e -> e
//	                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//	            )
//	            .oauth2Login();
//	        // @formatter:on
//	    }

	
	public static void main(String[] args) {
		SpringApplication.run(OauthSpringbootApplication.class, args);
	}

}
