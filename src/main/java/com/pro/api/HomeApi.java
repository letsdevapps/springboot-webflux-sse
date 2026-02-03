package com.pro.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.dto.MessageDTO;
import com.pro.model.PriceUpdate;
import com.pro.service.FakeCryptoService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping({ "/api", "/api/home" })
public class HomeApi {

	private final FakeCryptoService cryptoService;

	public HomeApi(FakeCryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}

	@RequestMapping({ "", "/", "/index" })
	public ResponseEntity<MessageDTO> index() {
		MessageDTO message = new MessageDTO();
		message.setId("message");
		message.setMessage("----- Springboot Webflux | Main -----");
		return ResponseEntity.ok(message);
	}

	@GetMapping(value = "/crypto/prices", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<PriceUpdate>> streamPrices() {
		return cryptoService.streamPrices().map(price -> ServerSentEvent.builder(price).build());
	}
}