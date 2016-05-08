package com.flat.wallet.user;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@RequestMapping("/current/id")
	public String getIdOfCurrentUser(Principal principal) {
		return (String) ((OAuth2Authentication) principal).getPrincipal();
	}
}
