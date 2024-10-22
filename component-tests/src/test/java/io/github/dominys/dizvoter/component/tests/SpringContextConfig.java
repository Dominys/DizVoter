package io.github.dominys.dizvoter.component.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.github.dominys.dizvoter.client.DizVoterPollsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"io.github.dominys.dizvoter.component.tests"})
public class SpringContextConfig {

  @Value("${dizvoter.url}")
  private String url;

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().findAndRegisterModules();
  }

  @Bean
  public DizVoterPollsClient pollsClient() {
    return createNewClient(DizVoterPollsClient.class);
  }

  private <T> T createNewClient(Class<T> clazz) {
    return Feign.builder()
        .encoder(new JacksonEncoder(objectMapper()))
        .decoder(new JacksonDecoder(objectMapper()))
        .target(clazz, url);
  }

}
