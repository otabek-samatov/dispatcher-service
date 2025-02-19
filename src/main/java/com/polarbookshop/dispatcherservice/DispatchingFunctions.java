package com.polarbookshop.dispatcherservice;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DispatchingFunctions {

	private static final Logger log = LoggerFactory.getLogger(DispatchingFunctions.class);

	@Bean
	public Function<OrderAcceptedMessage, Long> pack() {
		return orderAcceptedMessage -> {
			log.info("The order with id {} is packed.", orderAcceptedMessage.orderId());
			return orderAcceptedMessage.orderId();
		};
	}

	@Bean
	public Function<Long, OrderDispatchedMessage> label() {
		return orderId -> {
			log.info("The order with id {} is labeled.", orderId);
			return new OrderDispatchedMessage(orderId);
		};
	}

	@Bean
	public Function<OrderAcceptedMessage, OrderDispatchedMessage> packAndLabel() {
		return pack().andThen(label());
	}

}
