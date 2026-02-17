package com.pro.api;

import java.time.Duration;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Flux;

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

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamIndex() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(seq -> "Stream Index" + seq);
    }
	
	@GetMapping(value = "/stream-2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamIndex2(HttpServletResponse response) {
	    response.setHeader("Cache-Control", "no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    return Flux.interval(Duration.ofSeconds(1))
	               .map(seq -> "Stream Index 2 " + seq);
	}
}