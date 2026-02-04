package com.pro.api;

import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/sse")
public class SseApi {

	@GetMapping("/sse-emiter")
	public SseEmitter stream() {
		SseEmitter emitter = new SseEmitter();
		Executors.newSingleThreadExecutor().execute(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					emitter.send("Evento " + i);
					Thread.sleep(1000);
				}
				emitter.complete();
			} catch (Exception e) {
				emitter.completeWithError(e);
			}
		});
		return emitter;
	}
}