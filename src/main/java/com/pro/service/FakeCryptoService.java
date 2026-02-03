package com.pro.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.pro.model.PriceUpdate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class FakeCryptoService {

    private final Sinks.Many<PriceUpdate> sink = Sinks.many().multicast().onBackpressureBuffer();

    public FakeCryptoService() {
        // gera atualizações a cada 1 segundo
        Flux.interval(Duration.ofSeconds(1))
                .map(i -> new PriceUpdate("bitcoin", 40000 + Math.random() * 1000, System.currentTimeMillis()))
                .doOnNext(sink::tryEmitNext)
                .subscribe();
    }

    public Flux<PriceUpdate> streamPrices() {
        return sink.asFlux();
    }
}