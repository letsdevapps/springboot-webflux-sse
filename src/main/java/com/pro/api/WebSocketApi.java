package com.pro.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketApi {

	@MessageMapping("/index")
	@SendTo("/topic/greetings")
	public String greeting(String message) {
	    return "Olá " + message;
	}
}