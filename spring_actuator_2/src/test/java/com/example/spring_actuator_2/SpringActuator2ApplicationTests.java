package com.example.spring_actuator_2;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringActuator2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenGlobalRegistry_whenIncrementAnywhere_thenCounted() {
		class CountedObject {
			private CountedObject() {
				Metrics.counter("objects.instance").increment(1.0);
			}
		}
		Metrics.addRegistry(new SimpleMeterRegistry());

		Metrics.counter("objects.instance").increment();
		new CountedObject();

		Optional<Counter> counterOptional = Optional.ofNullable(Metrics.globalRegistry
				.find("objects.instance").counter());
		assertTrue(counterOptional.isPresent());
		assertTrue(counterOptional.get().count() == 2.0);
	}

}
