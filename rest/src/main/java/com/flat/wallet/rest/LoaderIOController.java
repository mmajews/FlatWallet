package com.flat.wallet.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoaderIOController {

	@RequestMapping(value = "loaderio-a5e1edf6d286b795bf50aa9ede571ab2")
	public String returnStringToken() {
		return "loaderio-a5e1edf6d286b795bf50aa9ede571ab2";
	}
}
