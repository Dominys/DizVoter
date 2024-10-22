package io.github.dominys.dizvoter.businesslogic.impl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("io.github.dominys.dizvoter.businesslogic.impl")
@EnableTransactionManagement
public class BusinessLogicCfg {

}
