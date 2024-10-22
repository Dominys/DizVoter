package io.github.dominys.dizvoter.component.tests;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest(classes = {SpringContextConfig.class})
@RequiredArgsConstructor
public class CucumberContextConfig {

}
