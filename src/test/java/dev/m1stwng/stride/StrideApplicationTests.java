package dev.m1stwng.stride;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class StrideApplicationTests {

	@Test
	void contextLoads() {
	}

}
