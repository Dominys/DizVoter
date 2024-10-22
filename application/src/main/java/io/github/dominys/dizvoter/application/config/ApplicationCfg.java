package io.github.dominys.dizvoter.application.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationCfg {

  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }

  @Bean
  public RouterFunction<ServerResponse> customRouter(@Value("classpath:/static/index.html") Resource html) {
    return RouterFunctions
        .route(GET("/ui/**"), request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).body(html));
  }

}
