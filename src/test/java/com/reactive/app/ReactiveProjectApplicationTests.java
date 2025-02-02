package com.reactive.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

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

		//Error mono
		Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));

		/*
		m1.subscribe(data -> {
			System.out.println("data is ---> : "+ data);
		});
		 */

		//consume mono by subscribing
		m1.subscribe(System.out::println);
	}

	@Test
	public void combineMono(){
		Mono<String> m1 = Mono.just("Amit is working in google");
		Mono<String> m2 = Mono.just("Salary package is 1.2 crore");
		Mono<String> m3 = Mono.just("when Amit work in india if Amit working USA salary 5 crore ");

		Mono<Tuple2<String,String>> combinedMono =  Mono.zip(m1,m2);

		Mono<Tuple3<String,String,String>> combinedMono3= Mono.zip(m1,m2,m3);

		combinedMono.subscribe(data -> {
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});

		combinedMono3.subscribe(data -> {
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
		});
	}

	@Test
	public void mapMono(){
		Mono<String> m1 = Mono.just("Amit is working in google");
		Mono<String> m2 = Mono.just("Salary package is 1.2 crore");
		Mono<String> m3 = Mono.just("when Amit work in india if Amit working USA salary 5 crore ");

		Mono<String> resultMono =  m1.map(String::toUpperCase);

		resultMono.subscribe(System.out::println);
	}

	@Test
	public void flatMapMono(){
		Mono<String> m1 = Mono.just("Amit is working in google");
		Mono<String[]> resultFlatMono = m1.flatMap(value1 -> Mono.just(value1.split(" ")));
		resultFlatMono.subscribe(data -> {
			for(String s : data){
				System.out.println(s);
			}
		});
	}

	@Test
	public void stringFlux(){
		Mono<String> m1 = Mono.just("Amit is working in google");
		Flux<String> stringFlux = m1.flatMapMany(valuem1 -> Flux.just(valuem1.split(" ")));
		stringFlux.subscribe(data -> {
			System.out.println(data);
		});
	}

}
