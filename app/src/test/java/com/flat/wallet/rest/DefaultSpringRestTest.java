package com.flat.wallet.rest;

import com.flat.wallet.app.Application;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations = "classpath:test.properties")
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class DefaultSpringRestTest {
}
