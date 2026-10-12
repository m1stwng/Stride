package dev.m1stwng.stride;

import org.springframework.boot.SpringApplication;

public class TestStrideApplication {

	public static void main(String[] args) {
		SpringApplication.from(StrideApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
