package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@ClassRule
  	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "topic");

	@Test
	public void contextLoads() {
		Assertions.assertThat(embeddedKafka).isNotNull();
	}

}
