package com.flat.wallet.rest;

import com.flat.wallet.app.Application;
import com.flat.wallet.app.token.TokenAuthenticationService;
import com.flat.wallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations = "classpath:test.properties")
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DefaultSpringRestTest {
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected TokenAuthenticationService tokenAuthenticationService;

	@Value("${local.server.port}")
	protected int port;
}
