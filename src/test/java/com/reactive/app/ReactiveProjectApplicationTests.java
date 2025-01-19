package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactiveProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void workingWithMono(){

		//Mono ---> publisher that have 0...1 items
		Mono<String> m1 = Mono.just("Amit work in google")
				.log();

		/*
		m1.subscribe(data -> {
			System.out.println("data is ---> : "+ data);
		});
		 */

		m1.subscribe(System.out::println);
	}

}
