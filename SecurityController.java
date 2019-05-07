package com.jbhunt.infrastructure.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * This class is used to handle authorization failures. When a user has no
 * access to a resource he will be redirected to this controller and we will
 * return a message as a JSON object.
 */
@RestController
@RequestMapping("/securepages")
public class SecurityController {
	/**
	 * secureMessage method is used to get authorization message details This
	 * method is returning the status information to the UI as json response
	 * object.
	 * 
	 * @return String
	 */
	@RequestMapping(produces = "application/json", value = "/notauthorized", method = RequestMethod.GET)
	public String secureMessage() {
		return "{\"message\":\"USER NOT AUTHORIZED\"}";

	}
}
